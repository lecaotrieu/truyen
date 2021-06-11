package com.truyen.command;

import com.truyenvn.core.command.AbstractCommand;
import com.truyenvn.core.dto.CommentDTO;

public class CommentCommand extends AbstractCommand<CommentDTO> {
    public CommentCommand() {
        this.pojo = new CommentDTO();
    }
    private String bookName;
    private String userName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
