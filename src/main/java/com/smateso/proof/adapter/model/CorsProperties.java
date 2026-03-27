/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@Schema
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private final List<String> methods = new ArrayList<>();
    private final List<String> origins = new ArrayList<>();
    private final List<String> headers = new ArrayList<>();

}