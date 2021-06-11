package com.truyen.command;

import com.truyenvn.core.command.AbstractCommand;
import com.truyenvn.core.dto.RoleDTO;

public class RoleCommand extends AbstractCommand<RoleDTO> {
    public RoleCommand() {
        this.pojo = new RoleDTO();
    }
}
