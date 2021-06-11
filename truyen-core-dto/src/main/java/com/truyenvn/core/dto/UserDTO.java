package com.truyenvn.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class UserDTO implements Serializable {
    private Integer userId;
    private String name;
    private String sex;
    private String password;
    private String fullName;
    private String email;
    private String address;
    private String number;
    private String avatar;
    private Timestamp createdDate;
    private RoleDTO roleDTO;
    private List<CommentDTO> commentDTOS;
    private List<BookDTO> bookDTOS;
    private List<EvaluateDTO> evaluateDTOS;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public List<CommentDTO> getCommentDTOS() {
        return commentDTOS;
    }

    public void setCommentDTOS(List<CommentDTO> commentDTOS) {
        this.commentDTOS = commentDTOS;
    }

    public List<BookDTO> getBookDTOS() {
        return bookDTOS;
    }

    public void setBookDTOS(List<BookDTO> bookDTOS) {
        this.bookDTOS = bookDTOS;
    }

    public List<EvaluateDTO> getEvaluateDTOS() {
        return evaluateDTOS;
    }

    public void setEvaluateDTOS(List<EvaluateDTO> evaluateDTOS) {
        this.evaluateDTOS = evaluateDTOS;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
