package com.truyen.command;

import com.truyenvn.core.command.AbstractCommand;
import com.truyenvn.core.dto.AuthorDTO;

public class AuthorCommand extends AbstractCommand<AuthorDTO> {
    public AuthorCommand() {
        this.pojo = new AuthorDTO();
    }
}
