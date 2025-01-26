package com.smateso.proof.application.utils;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static com.smateso.proof.application.utils.LogMessages.*;
import static java.lang.Class.forName;
import static java.util.Arrays.stream;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.util.StringUtils.capitalize;

@Slf4j
@Builder
@AllArgsConstructor
public class PropertyCopyHelper {

    public static final String SET = "set";
    public static final String GET = "get";
    public static final String LIST = "java.util.List";

    private static HashSet<String> getRelationalFields(@NonNull final Class<?> targetClass, @NonNull final Class<?> sourceClass) {
        final var result = new HashSet<String>();
        Stream
                .concat(stream(sourceClass.getDeclaredFields()), stream(targetClass.getDeclaredFields()))
                .forEach((final var field) -> {
                    final var annotations = field.getDeclaredAnnotations();
                    if (stream(annotations).anyMatch((final var annotation) -> annotation.annotationType().equals(OneToOne.class) || annotation.annotationType().equals(OneToMany.class))) {
                        result.add(field.getName());
                    }
                });
        return result;
    }

    private static Optional<Method> getSourceClassGetter(@NonNull final Class<?> sourceClass, @NonNull final Field relationalField) {
        try {
            final var sourceClassMethods = sourceClass.getDeclaredMethods();
            return stream(sourceClassMethods).filter((final var method) -> method.getName().equals(GET + capitalize(relationalField.getName()))).findFirst();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object getTargetSubClassInstanceForList(@NonNull final Field relationalField) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        final var targetClassType = ((ParameterizedType) relationalField.getGenericType()).getActualTypeArguments()[0];
        return forName(targetClassType.getTypeName()).getDeclaredConstructor().newInstance();
    }

    private static Object getTargetSubClassInstance(@NonNull final Field relationalField) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        final var targetClassType = relationalField.getType();
        return forName(targetClassType.getTypeName()).getDeclaredConstructor().newInstance();
    }

    @NonNull
    public static <S, T> T copyEntityProperties(@NonNull final S source, @NonNull final T target, @NonNull final Boolean ignoreNullProperties) {
        log.debug(COPY_BASIC_PROPERTIES.formatted(source.toString(), target.toString()));
        copyProperties(source, target, ignoreNullProperties ? getNullPropertyNames(source) : new String[0]);
        final var targetClass = target.getClass();
        final var sourceClass = source.getClass();
        final var relationalFieldNames = getRelationalFields(targetClass, sourceClass);
        log.debug(COPY_RELATIONAL_PROPERTIES.formatted(relationalFieldNames.toString(), target.toString()));
        relationalFieldNames.forEach((final var relationalFieldName) -> {
            try {
                final var optionalRelationalField = stream(targetClass.getDeclaredFields()).filter((final Field field) -> field.getName().equals(relationalFieldName)).findFirst();
                if (optionalRelationalField.isPresent()) {
                    final var relationalField = optionalRelationalField.get();
                    final var sourceClassGetter = getSourceClassGetter(sourceClass, relationalField);
                    if (relationalField.getType().getTypeName().equals(LIST)) {
                        final var targetSubClassInstance = getTargetSubClassInstanceForList(relationalField);
                        if (sourceClassGetter.isPresent()) {
                            final var sourceValue = (List<?>) sourceClassGetter.get().invoke(source);
                            final var result = sourceValue.stream().map((final var sourceInstance) -> copyEntityProperties(sourceInstance, targetSubClassInstance, ignoreNullProperties)).toList();
                            targetClass.getDeclaredMethod(SET + capitalize(relationalField.getName()), List.class).invoke(target, result);
                            log.debug(SET_PROPERTY.formatted(relationalFieldName, target.toString(), result.toString()));
                        }
                    } else {
                        final var targetSubClassInstance = getTargetSubClassInstance(relationalField);
                        if (sourceClassGetter.isPresent()) {
                            final var sourceValue = sourceClassGetter.get().invoke(source);
                            final var result = copyEntityProperties(sourceValue, targetSubClassInstance, ignoreNullProperties);
                            targetClass.getDeclaredMethod(SET + capitalize(relationalField.getName()), relationalField.getType()).invoke(target, result);
                            log.debug(SET_PROPERTY.formatted(relationalFieldName, target.toString(), result.toString()));
                        }
                    }
                }
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        });
        return target;
    }

}
