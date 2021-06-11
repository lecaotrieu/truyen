package com.truyenvn.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class BookDTO implements Serializable {
    private Integer bookId;
    private String bookName;
    private String bookFullName;
    private String brief;
    private String poster;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private UserDTO userDTO;
    private AuthorDTO authorDTO;
    private List<String> categoryNames;
    private List<ChapterDTO> chapterDTOS;
    private List<EvaluateDTO> evaluateDTOS;
    private List<CategoryDTO> categoryDTOS;
    private List<CommentDTO> commentDTOS;

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

    public String getBookFullName() {
        return bookFullName;
    }

    public void setBookFullName(String bookFullName) {
        this.bookFullName = bookFullName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public List<ChapterDTO> getChapterDTOS() {
        return chapterDTOS;
    }

    public void setChapterDTOS(List<ChapterDTO> chapterDTOS) {
        this.chapterDTOS = chapterDTOS;
    }

    public List<EvaluateDTO> getEvaluateDTOS() {
        return evaluateDTOS;
    }

    public void setEvaluateDTOS(List<EvaluateDTO> evaluateDTOS) {
        this.evaluateDTOS = evaluateDTOS;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<CategoryDTO> getCategoryDTOS() {
        return categoryDTOS;
    }

    public void setCategoryDTOS(List<CategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }

    public List<CommentDTO> getCommentDTOS() {
        return commentDTOS;
    }

    public void setCommentDTOS(List<CommentDTO> commentDTOS) {
        this.commentDTOS = commentDTOS;
    }
}
