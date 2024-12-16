package com.smateso.proof.application.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class Message implements Serializable {

    private String id;
    private String entity;
    private Action action;
    private String sessionKey;
    private String user;

}
