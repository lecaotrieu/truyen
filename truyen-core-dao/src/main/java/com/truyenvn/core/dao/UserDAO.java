package com.truyenvn.core.dao;

import com.truyenvn.core.data.dao.GenericDAO;
import com.truyenvn.core.persistence.entity.UserEntity;

public interface UserDAO extends GenericDAO<Integer, UserEntity> {
    UserEntity userLogin(String name, String password);
    int updateAvatar(Integer userId ,String avatarLocation);
}
