package com.smateso.proof.application.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static java.lang.Class.forName;
import static java.util.Arrays.stream;
import static java.util.Objects.isNull;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.util.StringUtils.capitalize;

@Builder
@AllArgsConstructor
public class PropertyCopyHelper {

    public static final String SET = "set";
    public static final String GET = "get";
    public static final String LIST = "java.util.List";

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

    public static <S, T> void deepCopyEntityProperties(@Nullable final S source, @Nullable final T target, @NonNull final Boolean ignoreNullProperties, @NonNull final List<String> excludedProperties, @NonNull final List<String> complexFieldNames) {
        if (source != null) {
            if (target != null) {
                copyProperties(source, target, Stream.concat(ignoreNullProperties ? getNullPropertyNames(source) : Stream.of(), excludedProperties.stream()).toArray(String[]::new));
                final var targetClass = target.getClass();
                final var sourceClass = source.getClass();
                complexFieldNames
                        .stream()
                        .filter((final var complexFieldName) -> !excludedProperties.contains(complexFieldName))
                        .forEach((final var complexFieldName) -> {
                            try {
                                final var optionalComplexField = stream(targetClass.getDeclaredFields()).filter((final Field field) -> field.getName().equals(complexFieldName)).findFirst();
                                if (optionalComplexField.isPresent()) {
                                    final var complexField = optionalComplexField.get();
                                    final var sourceClassGetter = getSourceClassGetter(sourceClass, complexField);
                                    if (complexField.getType().getTypeName().equals(LIST)) {
                                        if (sourceClassGetter.isPresent()) {
                                            final var sourceValue = (List<?>) sourceClassGetter.get().invoke(source);
                                            if (!isNull(sourceValue)) {
                                                final var result = new ArrayList<>();
                                                for (Object sourceInstance : sourceValue) {
                                                    final var targetSubClassInstance = getTargetSubClassInstanceForList(complexField);
                                                    deepCopyEntityProperties(sourceInstance, targetSubClassInstance, ignoreNullProperties, excludedProperties, complexFieldNames);
                                                    result.add(targetSubClassInstance);
                                                }
                                                targetClass.getDeclaredMethod(SET + capitalize(complexField.getName()), List.class).invoke(target, result);
                                            }
                                        }
                                    } else {
                                        final var targetSubClassInstance = getTargetSubClassInstance(complexField);
                                        if (sourceClassGetter.isPresent()) {
                                            final var sourceValue = sourceClassGetter.get().invoke(source);
                                            deepCopyEntityProperties(sourceValue, targetSubClassInstance, ignoreNullProperties, excludedProperties, complexFieldNames);
                                            targetClass.getDeclaredMethod(SET + capitalize(complexField.getName()), complexField.getType()).invoke(target, targetSubClassInstance);
                                        }
                                    }
                                }
                            } catch (final Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        }
    }


}
