package com.truyenvn.core.utils;

import com.truyenvn.core.dto.BookDTO;
import com.truyenvn.core.dto.CategoryDTO;
import com.truyenvn.core.persistence.entity.BookEntity;
import com.truyenvn.core.persistence.entity.CategoryEntity;

import java.util.HashSet;
import java.util.Set;

public class CategoryBeanUtil {
    public static CategoryDTO getDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(entity.getCategoryId());
        dto.setCategoryName(entity.getCategoryName());
        dto.setContent(entity.getContent());
        return dto;
    }

    public static CategoryEntity getEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryId(dto.getCategoryId());
        entity.setCategoryName(dto.getCategoryName());
        entity.setContent(dto.getContent());
        return entity;
    }
}
