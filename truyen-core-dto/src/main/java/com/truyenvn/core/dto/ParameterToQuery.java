package com.truyenvn.core.dto;

import java.util.Map;

public class ParameterToQuery {
    private Map<String, Object> properties;
    private String sortExpression;
    private String sortDirection;
    private Integer offset;
    private Integer limit;

    public ParameterToQuery() {
    }

    public ParameterToQuery(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        this.properties = properties;
        this.sortExpression = sortExpression;
        this.sortDirection = sortDirection;
        this.offset = offset;
        this.limit = limit;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public String getSortExpression() {
        return sortExpression;
    }

    public void setSortExpression(String sortExpression) {
        this.sortExpression = sortExpression;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
