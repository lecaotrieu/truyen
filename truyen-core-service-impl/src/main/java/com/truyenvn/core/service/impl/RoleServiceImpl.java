package com.truyenvn.core.service.impl;

import com.truyenvn.core.dto.RoleDTO;
import com.truyenvn.core.persistence.entity.RoleEntity;
import com.truyenvn.core.service.RoleService;
import com.truyenvn.core.service.utils.SingletonDaoUtil;
import com.truyenvn.core.utils.RoleBeanUtil;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoleServiceImpl implements RoleService {
    public Object[] findByProperty(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) throws HibernateException {
        return SingletonDaoUtil.getRoleDAO().findByProperty(properties, sortExpression, sortDirection, offset, limit, null,null, null);
    }

    public List<RoleDTO> findAll() throws HibernateException {
        List<RoleEntity> roleEntities = SingletonDaoUtil.getRoleDAO().findAll();
        List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
        for (RoleEntity entity : roleEntities) {
            roleDTOS.add(RoleBeanUtil.getDTO(entity));
        }
        return roleDTOS;
    }
}
