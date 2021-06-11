package com.truyenvn.core.utils;

import com.truyenvn.core.dto.UserDTO;
import com.truyenvn.core.persistence.entity.UserEntity;

public class UserBeanUtil {
    public static UserDTO getDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setFullName(entity.getFullName());
        dto.setPassword(entity.getPassword());
        dto.setAddress(entity.getAddress());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setEmail(entity.getEmail());
        dto.setAvatar(entity.getAvatar());
        dto.setNumber(entity.getNumber());
        dto.setSex(entity.getSex());
        if (entity.getRoleEntity() != null)
            dto.setRoleDTO(RoleBeanUtil.getDTO(entity.getRoleEntity()));
        return dto;
    }

    public static UserEntity getEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setFullName(dto.getFullName());
        entity.setPassword(dto.getPassword());
        entity.setAddress(dto.getAddress());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setEmail(dto.getEmail());
        entity.setAvatar(dto.getAvatar());
        entity.setNumber(dto.getNumber());
        entity.setSex(dto.getSex());
        if (dto.getRoleDTO() != null)
            entity.setRoleEntity(RoleBeanUtil.getEntity(dto.getRoleDTO()));
        return entity;
    }
}
