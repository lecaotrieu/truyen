package com.truyenvn.core.dao;

import com.truyenvn.core.data.dao.GenericDAO;
import com.truyenvn.core.persistence.entity.CategoryEntity;

import java.util.List;
import java.util.Set;

public interface CategoryDAO extends GenericDAO<Integer, CategoryEntity> {
    List<Object[]> findByBook(Integer bookId);
}
