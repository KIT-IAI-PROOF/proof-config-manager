package com.smateso.proof.adapter.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectClassLoader {

    public static Set<Class<?>> findAllClassesUsingClassLoader(final String packageName) {
        final var stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName("%s.%s".formatted(packageName, className.substring(0, className.lastIndexOf('.'))));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
