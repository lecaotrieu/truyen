package com.truyenvn.core.service.impl;

import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.dto.UserDTO;
import com.truyenvn.core.persistence.entity.UserEntity;
import com.truyenvn.core.service.UserService;
import com.truyenvn.core.service.utils.SingletonDaoUtil;
import com.truyenvn.core.utils.UserBeanUtil;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public UserDTO userLogin(UserDTO userDTO)  throws HibernateException {
        UserEntity entity = SingletonDaoUtil.getUserDAO().userLogin(userDTO.getName(), userDTO.getPassword());
        if (entity != null) {
            userDTO = UserBeanUtil.getDTO(entity);
        }
        return userDTO;
    }

    public Object[] findUserByProperties(ParameterToQuery properties)  throws HibernateException {
        String whereClause = null;
        if (properties.getProperties() != null) {
            whereClause = "  AND (LOWER(name) LIKE '%" + properties.getProperties().get("search") + "%' OR LOWER(fullName) LIKE '%" + properties.getProperties().get("search") + "%'))";
        }
        Object[] objects = SingletonDaoUtil.getUserDAO().findByProperty(null, properties.getSortExpression(), properties.getSortDirection(), properties.getOffset(), properties.getLimit(), null,null, whereClause);
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for (UserEntity entity : (List<UserEntity>) objects[1]) {
            UserDTO dto = UserBeanUtil.getDTO(entity);
            userDTOS.add(dto);
        }
        objects[1] = userDTOS;
        return objects;
    }

    public UserDTO findById(Integer id)  throws HibernateException {
        UserEntity entity = SingletonDaoUtil.getUserDAO().findById(id);
        return UserBeanUtil.getDTO(entity);
    }

    public UserDTO updateUser(UserDTO dto)  throws HibernateException {
        UserEntity entity = UserBeanUtil.getEntity(dto);
        entity = SingletonDaoUtil.getUserDAO().update(entity);
        dto = UserBeanUtil.getDTO(entity);
        return dto;
    }

    public void insertUser(UserDTO dto)  throws HibernateException {
        dto.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        dto.setAvatar("userAvatar\\avatar_default.jpg");
        UserEntity entity = UserBeanUtil.getEntity(dto);
        SingletonDaoUtil.getUserDAO().save(entity);
    }

    public Integer updateAvatar(UserDTO dto) {
        return SingletonDaoUtil.getUserDAO().updateAvatar(dto.getUserId(), dto.getAvatar());
    }

    public Integer deleteUser(List<Integer> ids)  throws HibernateException {
        Integer result = -1;
        result = SingletonDaoUtil.getUserDAO().delete(ids);
        return result;
    }
}
