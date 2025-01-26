package com.smateso.proof.adapter.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.lang.NonNull;

import java.beans.FeatureDescriptor;
import java.util.Collection;
import java.util.stream.Stream;

public class NullAwareUtil {

    public static Stream<String> getNullPropertyNames(@NonNull final Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter((final String propertyName) -> {
                    final var value = wrappedSource.getPropertyValue(propertyName);
                    if (value instanceof Collection<?>) {
                        return ((Collection<?>) value).isEmpty();
                    } else {
                        return value == null;
                    }
                });
    }

}