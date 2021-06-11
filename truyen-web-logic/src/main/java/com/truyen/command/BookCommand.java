package com.truyen.command;

import com.truyenvn.core.command.AbstractCommand;
import com.truyenvn.core.dto.AuthorDTO;
import com.truyenvn.core.dto.BookDTO;
import com.truyenvn.core.dto.CategoryDTO;
import com.truyenvn.core.dto.ChapterDTO;

import java.util.List;

public class BookCommand extends AbstractCommand<BookDTO> {
    public BookCommand() {
        this.pojo = new BookDTO();
    }
    private List<CategoryDTO> categoryS;
    private List<AuthorDTO> authorS;
    private  List<ChapterDTO> chapterS;
    private Integer totalComment;

    public List<CategoryDTO> getCategoryS() {
        return categoryS;
    }

    public void setCategoryS(List<CategoryDTO> categoryS) {
        this.categoryS = categoryS;
    }

    public List<AuthorDTO> getAuthorS() {
        return authorS;
    }

    public void setAuthorS(List<AuthorDTO> authorS) {
        this.authorS = authorS;
    }

    public List<ChapterDTO> getChapterS() {
        return chapterS;
    }

    public void setChapterS(List<ChapterDTO> chapterS) {
        this.chapterS = chapterS;
    }

    public Integer getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(Integer totalComment) {
        this.totalComment = totalComment;
    }
}
