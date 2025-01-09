package com.smateso.proof.adapter.config;

import io.swagger.v3.core.converter.AnnotatedType;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.smateso.proof.adapter.utils.ReflectClassLoader.findAllClassesUsingClassLoader;
import static io.swagger.v3.core.converter.ModelConverters.getInstance;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenApiCustomizer schemaCustomizer() {
        return (final var openApi) -> {
            final var classes = findAllClassesUsingClassLoader("com.smateso.proof.adapter.model");
            classes.forEach((final Class<?> schemaClass) -> {
                if (!schemaClass.isEnum()) {
                    final var resolvedSchema = getInstance().resolveAsResolvedSchema(new AnnotatedType(schemaClass));
                    openApi.schema(resolvedSchema.schema.getName(), resolvedSchema.schema);
                }
            });
        };
    }

}
