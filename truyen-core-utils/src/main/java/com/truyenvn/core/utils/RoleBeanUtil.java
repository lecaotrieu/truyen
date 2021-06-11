package com.truyenvn.core.utils;

import com.truyenvn.core.dto.RoleDTO;
import com.truyenvn.core.dto.UserDTO;
import com.truyenvn.core.persistence.entity.RoleEntity;
import com.truyenvn.core.persistence.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;

public class RoleBeanUtil {
    public static RoleDTO getDTO(RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setName(entity.getName());
        return dto;
    }

    public static RoleEntity getEntity(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(dto.getRoleId());
        entity.setName(dto.getName());
        Set<UserEntity> userEntities = new HashSet<UserEntity>();
        return entity;
    }
}
