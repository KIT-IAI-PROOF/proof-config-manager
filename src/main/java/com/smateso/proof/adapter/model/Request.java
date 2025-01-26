package com.smateso.proof.adapter.model;

import lombok.Data;

import java.util.List;

@Data
public class Request {

    private List<Sort> sort;
    private Pagination pagination;
    private Filter filter;

}
