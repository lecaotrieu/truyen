package com.truyenvn.core.service;

import com.truyenvn.core.dto.RoleDTO;

import java.util.List;
import java.util.Map;

public interface RoleService {
    Object[] findByProperty(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
    List<RoleDTO> findAll();
}
