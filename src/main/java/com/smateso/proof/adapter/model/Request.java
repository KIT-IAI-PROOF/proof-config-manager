package com.smateso.proof.adapter.model;

import lombok.Data;

import java.util.List;

@Data
public class Request {

    private List<Sort> sort;
    private Pagination pagination;
    private Filter filter;

    @Data
    public static class Sort {
        private String field;
        private String sort;
    }

    @Data
    public static class Pagination {
        private int page;
        private int pageSize;
    }

    @Data
    public static class Filter {
        private List<FilterItem> items;
    }

    @Data
    public static class FilterItem {
        private String field;
        private String value;
    }

}
