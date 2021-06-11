package com.truyenvn.core.service.impl;

import com.truyenvn.core.dto.AuthorDTO;
import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.persistence.entity.AuthorEntity;
import com.truyenvn.core.service.AuthorService;
import com.truyenvn.core.service.utils.SingletonDaoUtil;
import com.truyenvn.core.utils.AuthorBeanUtil;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    public List<AuthorDTO> findAll() throws HibernateException {
        String whereClause = " AND authorId != 0 ";
        Object[] objects = SingletonDaoUtil.getAuthorDAO().findByProperty(null, null, null, null, null, null,null, whereClause);
        List<AuthorDTO> authorDTOS = new ArrayList<AuthorDTO>();
        for (AuthorEntity entity : (List<AuthorEntity>) objects[1]) {
            AuthorDTO dto = AuthorBeanUtil.getDTO(entity);
            authorDTOS.add(dto);
        }
        return authorDTOS;
    }

    public Integer insertAuthor(AuthorDTO authorDTO) throws HibernateException {
        AuthorEntity entity = AuthorBeanUtil.getEntity(authorDTO);
        return SingletonDaoUtil.getAuthorDAO().save(entity);
    }

    public Object[] findAuthorByProperties(ParameterToQuery properties) throws HibernateException {
        String whereClause = " AND authorId != 0 ";
        Object[] objects = SingletonDaoUtil.getAuthorDAO().findByProperty(properties.getProperties(), properties.getSortExpression(), properties.getSortDirection(), properties.getOffset(), properties.getLimit(),null, null, whereClause);
        List<AuthorDTO> authorDTOS = new ArrayList<AuthorDTO>();
        for (AuthorEntity entity : (List<AuthorEntity>) objects[1]) {
            AuthorDTO dto = AuthorBeanUtil.getDTO(entity);
            authorDTOS.add(dto);
        }
        objects[1] = authorDTOS;
        return objects;
    }

    public Integer deleteAuthor(List<Integer> idS) throws HibernateException {
        for (Integer id : idS) {
            AuthorEntity authorEntity = SingletonDaoUtil.getAuthorDAO().findById(id);
        }
        Integer result = SingletonDaoUtil.getAuthorDAO().delete(idS);
        return result;
    }

    public AuthorDTO updateAuthor(AuthorDTO authorDTO) throws HibernateException {
        AuthorEntity authorEntity = AuthorBeanUtil.getEntity(authorDTO);
        authorEntity = SingletonDaoUtil.getAuthorDAO().update(authorEntity);
        authorDTO = AuthorBeanUtil.getDTO(authorEntity);
        return authorDTO;
    }
}
