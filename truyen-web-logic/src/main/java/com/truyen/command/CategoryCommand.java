package com.truyen.command;

import com.truyenvn.core.command.AbstractCommand;
import com.truyenvn.core.dto.CategoryDTO;

public class CategoryCommand extends AbstractCommand<CategoryDTO> {
    public CategoryCommand() {
        this.pojo = new CategoryDTO();
    }
}
