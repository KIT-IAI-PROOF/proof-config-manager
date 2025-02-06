package com.smateso.proof.application.utils;

import edu.kit.iai.webis.proofmodels.utils.Relational;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static com.smateso.proof.application.utils.LogMessages.COPY_BASIC_PROPERTIES;
import static com.smateso.proof.application.utils.LogMessages.COPY_RELATIONAL_PROPERTIES;
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

    private static HashSet<String> getRelationalFields(@NonNull final Class<?> targetClass) {
        final var result = new HashSet<String>();
        stream(targetClass.getDeclaredFields())
                .forEach((final var field) -> {
                    final var annotations = field.getDeclaredAnnotations();
                    if (stream(annotations).anyMatch((final var annotation) -> annotation.annotationType().equals(Relational.class))) {
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

    public static <S, T> T copyEntityProperties(@Nullable final S source, @Nullable final T target, @NonNull final Boolean ignoreNullProperties, @NonNull final List<String> excludedProperties) {
        if (source != null) {
            if (target != null) {
                log.debug(COPY_BASIC_PROPERTIES.formatted(source.toString(), target.toString()));
                copyProperties(source, target, Stream.concat(ignoreNullProperties ? getNullPropertyNames(source) : Stream.of(), excludedProperties.stream()).toArray(String[]::new));
                final var targetClass = target.getClass();
                final var sourceClass = source.getClass();
                final var relationalFieldNames = getRelationalFields(targetClass);
                log.debug(COPY_RELATIONAL_PROPERTIES.formatted(relationalFieldNames.toString(), target.toString()));
                relationalFieldNames
                        .stream()
                        .filter((final var relationalFieldName) -> !excludedProperties.contains(relationalFieldName))
                        .forEach((final var relationalFieldName) -> {
                            try {
                                final var optionalRelationalField = stream(targetClass.getDeclaredFields()).filter((final Field field) -> field.getName().equals(relationalFieldName)).findFirst();
                                if (optionalRelationalField.isPresent()) {
                                    final var relationalField = optionalRelationalField.get();
                                    final var sourceClassGetter = getSourceClassGetter(sourceClass, relationalField);
                                    if (relationalField.getType().getTypeName().equals(LIST)) {
                                        if (sourceClassGetter.isPresent()) {
                                            final var sourceValue = (List<?>) sourceClassGetter.get().invoke(source);
                                            final var result = new ArrayList<>();
                                            for (Object sourceInstance : sourceValue) {
                                                final var targetSubClassInstance = getTargetSubClassInstanceForList(relationalField);
                                                Object o = copyEntityProperties(sourceInstance, targetSubClassInstance, ignoreNullProperties, relationalFieldNames.stream().toList());
                                                result.add(o);
                                            }
                                            targetClass.getDeclaredMethod(SET + capitalize(relationalField.getName()), List.class).invoke(target, result);
                                        }
                                    } else {
                                        final var targetSubClassInstance = getTargetSubClassInstance(relationalField);
                                        if (sourceClassGetter.isPresent()) {
                                            final var sourceValue = sourceClassGetter.get().invoke(source);
                                            final var result = copyEntityProperties(sourceValue, targetSubClassInstance, ignoreNullProperties, relationalFieldNames.stream().toList());
                                            targetClass.getDeclaredMethod(SET + capitalize(relationalField.getName()), relationalField.getType()).invoke(target, result);
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
        return null;
    }

}
