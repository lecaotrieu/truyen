package com.truyenvn.core.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDTO implements Serializable {
    private Integer roleId;
    private String name;
    private List<UserDTO> userDTOS;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDTO> getUserDTOS() {
        return userDTOS;
    }

    public void setUserDTOS(List<UserDTO> userDTOS) {
        this.userDTOS = userDTOS;
    }
}
