package com.truyen.command;

import com.truyenvn.core.command.AbstractCommand;
import com.truyenvn.core.dto.RoleDTO;
import com.truyenvn.core.dto.UserDTO;

import java.util.List;

public class UserCommand extends AbstractCommand<UserDTO> {
    public UserCommand() {
        this.pojo = new UserDTO();
    }
    private List<RoleDTO> roles;
    private Integer roleId;
    private boolean checkLogin;

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public boolean isCheckLogin() {
        return checkLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        this.checkLogin = checkLogin;
    }
}
