package com.truyen.command;

import com.truyenvn.core.command.AbstractCommand;
import com.truyenvn.core.dto.EvaluateDTO;

public class EvaluateCommand extends AbstractCommand<EvaluateDTO> {
    public EvaluateCommand() {
        this.pojo = new EvaluateDTO();
    }
}
