package com.truyenvn.core.service.impl;

import com.truyenvn.core.dto.CategoryDTO;
import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.persistence.entity.BookEntity;
import com.truyenvn.core.persistence.entity.CategoryEntity;
import com.truyenvn.core.service.CategoryService;
import com.truyenvn.core.service.utils.SingletonDaoUtil;
import com.truyenvn.core.utils.CategoryBeanUtil;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    public List<CategoryDTO> findAll() throws HibernateException {
        List<CategoryEntity> entityList = SingletonDaoUtil.getCategoryDAO().findAll();
        List<CategoryDTO> dtoS = new ArrayList<CategoryDTO>();
        for (CategoryEntity entity : entityList) {
            dtoS.add(CategoryBeanUtil.getDTO(entity));
        }
        return dtoS;
    }

    public CategoryDTO findById(Integer ID) throws HibernateException {
        CategoryEntity entity = SingletonDaoUtil.getCategoryDAO().findById(ID);
        CategoryDTO dto = CategoryBeanUtil.getDTO(entity);
        return dto;
    }

    public Object[] findCategoryByProperties(ParameterToQuery properties) throws HibernateException {
        Object[] objects = SingletonDaoUtil.getCategoryDAO().findByProperty(properties.getProperties(), properties.getSortExpression(), properties.getSortDirection(), properties.getOffset(), properties.getLimit(), null,null, null);
        List<CategoryDTO> categoryDTOS = new ArrayList<CategoryDTO>();
        for (CategoryEntity entity : (List<CategoryEntity>) objects[1]) {
            CategoryDTO dto = CategoryBeanUtil.getDTO(entity);
            categoryDTOS.add(dto);
        }
        objects[1] = categoryDTOS;
        return objects;
    }

    public Integer deleteCategory(List<Integer> idS) throws HibernateException {
        for (Integer id : idS) {
            CategoryEntity categoryEntity = SingletonDaoUtil.getCategoryDAO().findById(id);
            List<BookEntity> bookEntities = new ArrayList<BookEntity>();
            categoryEntity.setBookEntities(bookEntities);
            SingletonDaoUtil.getCategoryDAO().update(categoryEntity);
        }
        Integer result = SingletonDaoUtil.getCategoryDAO().delete(idS);
        return result;
    }

    public Integer insertCategory(CategoryDTO categoryDTO) throws HibernateException {
        CategoryEntity categoryEntity = CategoryBeanUtil.getEntity(categoryDTO);
        Integer result = SingletonDaoUtil.getCategoryDAO().save(categoryEntity);
        return result;
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) throws HibernateException {
        CategoryEntity categoryEntity = CategoryBeanUtil.getEntity(categoryDTO);
        categoryEntity = SingletonDaoUtil.getCategoryDAO().update(categoryEntity);
        categoryDTO = CategoryBeanUtil.getDTO(categoryEntity);
        return categoryDTO;
    }
}
