package com.truyenvn.core.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<ID extends Serializable, T> {
    T update(T entity);
    ID save(T entity);
    Object[] findByProperty(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit,String selectClause, String joinClause, String whereClause);
    T findById(ID id);
    Integer delete(List<ID> ids);
    Integer deleteByParameters(Map<String,Object> parameters);
    T findEqualUnique(String property, Object value);
    T findByParameter(Map<String,Object> parameters);
    List<T> findAll();
    Integer countByParameter(Map<String,Object> parameters);
}
