package com.truyenvn.core.service;

import com.truyenvn.core.dto.CategoryDTO;
import com.truyenvn.core.dto.ParameterToQuery;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Integer ID);
    Object[] findCategoryByProperties(ParameterToQuery parameterToQuery);
    Integer deleteCategory(List<Integer> idS);
    Integer insertCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO);
}
