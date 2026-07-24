/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@ToString
@Schema
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private String id;
    private String entity;
    private Action action;
    private String sessionKey;
    private String user;

}
