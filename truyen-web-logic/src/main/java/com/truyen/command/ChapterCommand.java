package com.truyen.command;

import com.truyenvn.core.command.AbstractCommand;
import com.truyenvn.core.dto.ChapterDTO;

public class ChapterCommand extends AbstractCommand<ChapterDTO> {
    public ChapterCommand() {
        this.pojo = new ChapterDTO();
    }
    private Integer bookId;
    private String bookName;
    private Integer lastChapter;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(Integer lastChapter) {
        this.lastChapter = lastChapter;
    }
}
