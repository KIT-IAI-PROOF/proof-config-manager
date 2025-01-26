package com.smateso.proof.adapter.model;

import lombok.Data;

@Data
public class FilterItem {
    private String field;
    private String value;
    private EOperator operator;
}
