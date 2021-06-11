package com.truyenvn.core.service.impl;

import com.truyenvn.core.dto.BookDTO;
import com.truyenvn.core.dto.CategoryDTO;
import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.persistence.entity.BookEntity;
import com.truyenvn.core.persistence.entity.CategoryEntity;
import com.truyenvn.core.service.BookService;
import com.truyenvn.core.service.utils.SingletonDaoUtil;
import com.truyenvn.core.service.utils.StringGlobalUtils;
import com.truyenvn.core.utils.BookBeanUtil;
import com.truyenvn.core.utils.CategoryBeanUtil;
import com.truyenvn.core.utils.UserBeanUtil;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.*;

public class BookServiceImpl implements BookService {
    public Object[] findBookByProperties(ParameterToQuery properties) throws HibernateException {
        Object[] objects = SingletonDaoUtil.getBookDAO().findByProperty(properties.getProperties(), properties.getSortExpression(), properties.getSortDirection(), properties.getOffset(), properties.getLimit(), null, null, null);
        List<BookDTO> bookDTOS = new ArrayList<BookDTO>();
        for (BookEntity entity : (List<BookEntity>) objects[1]) {
            BookDTO dto = BookBeanUtil.getDTO(entity);
            List<String> categoryNames = new ArrayList<String>();
            List<CategoryDTO> categoryDTOS = new ArrayList<CategoryDTO>();
            for (CategoryEntity categoryEntity : entity.getCategoryEntities()) {
                CategoryDTO categoryDTO = CategoryBeanUtil.getDTO(categoryEntity);
                categoryDTOS.add(categoryDTO);
                categoryNames.add(categoryDTO.getCategoryName());
            }
            dto.setCategoryDTOS(categoryDTOS);
            dto.setCategoryNames(categoryNames);
            bookDTOS.add(dto);
        }
        objects[1] = bookDTOS;
        return objects;
    }

    public void insertBook(BookDTO dto) throws HibernateException {
        if (dto.getBookFullName() != null) {
            String bookName = StringGlobalUtils.covertToString(dto.getBookFullName());
            dto.setBookName(bookName);
        }
        dto.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        BookEntity entity = BookBeanUtil.getEntity(dto);
        if (dto.getCategoryDTOS() != null) {
            List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
            for (CategoryDTO categoryDTO : dto.getCategoryDTOS()) {
                CategoryEntity categoryEntity = CategoryBeanUtil.getEntity(categoryDTO);
                categoryEntities.add(categoryEntity);
            }
            entity.setCategoryEntities(categoryEntities);
        }
        SingletonDaoUtil.getBookDAO().save(entity);
    }

    public BookDTO updateBook(BookDTO dto) throws HibernateException {
        if (dto.getBookFullName() != null) {
            String bookName = StringGlobalUtils.covertToString(dto.getBookFullName());
            dto.setBookName(bookName);
        }
        dto.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        BookEntity entity = SingletonDaoUtil.getBookDAO().findById(dto.getBookId());
        if (dto.getUserDTO().getRoleDTO().getName().equals("USER")) {
            if (!dto.getUserDTO().getUserId().equals(entity.getUserEntity().getUserId())) {
                return null;
            }
        }
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUserDTO(UserBeanUtil.getDTO(entity.getUserEntity()));
        if (dto.getPoster() == null) {
            dto.setPoster(entity.getPoster());
        }
        entity = BookBeanUtil.getEntity(dto);
        if (dto.getEvaluateDTOS()!=null) {

        }
        if (dto.getCategoryDTOS() != null) {
            List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
            for (CategoryDTO categoryDTO : dto.getCategoryDTOS()) {
                CategoryEntity categoryEntity = SingletonDaoUtil.getCategoryDAO().findById(categoryDTO.getCategoryId());
                categoryEntities.add(categoryEntity);
            }
            entity.setCategoryEntities(categoryEntities);
        }
        entity = SingletonDaoUtil.getBookDAO().update(entity);
        dto = BookBeanUtil.getDTO(entity);
        return dto;
    }

    public BookDTO findById(Integer iD) throws HibernateException {
        BookEntity entity = SingletonDaoUtil.getBookDAO().findById(iD);
        BookDTO dto = BookBeanUtil.getDTO(entity);
        List<CategoryDTO> categoryDTOS = new ArrayList<CategoryDTO>();
        for (CategoryEntity categoryEntity: entity.getCategoryEntities()) {
            CategoryDTO categoryDTO = CategoryBeanUtil.getDTO((categoryEntity) );
            categoryDTOS.add(categoryDTO);
        }
        dto.setCategoryDTOS(categoryDTOS);
        return dto;
    }

    public BookDTO findUniqueBook(String property, Object value) {
        BookEntity bookEntity = SingletonDaoUtil.getBookDAO().findEqualUnique(property, value);
        BookDTO dto = null;
        if (bookEntity != null) {
            dto = BookBeanUtil.getDTO(bookEntity);

        }
        return dto;
    }

    public BookDTO findBookByParameters(Map<String, Object> parameters) {
        BookEntity entity = SingletonDaoUtil.getBookDAO().findByParameter(parameters);
        BookDTO dto = BookBeanUtil.getDTO(entity);
        List<Object[]> objects = SingletonDaoUtil.getCategoryDAO().findByBook(entity.getBookId());
        List<CategoryDTO> categoryDTOS = new ArrayList<CategoryDTO>();
        for (Object[] o : objects) {
            CategoryDTO categoryDTO = CategoryBeanUtil.getDTO((CategoryEntity) o[0]);
            categoryDTOS.add(categoryDTO);
        }
        dto.setCategoryDTOS(categoryDTOS);
        return dto;
    }

    public Integer deleteBook(List<Integer> idS) throws HibernateException {
        for (Integer id : idS) {
            BookEntity bookEntity = SingletonDaoUtil.getBookDAO().findById(id);
            bookEntity.setCategoryEntities(null);
            SingletonDaoUtil.getBookDAO().update(bookEntity);
        }
        Integer result = SingletonDaoUtil.getBookDAO().delete(idS);
        return result;
    }
}
