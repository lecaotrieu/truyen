package com.truyenvn.core.utils;

import com.truyenvn.core.dto.CommentDTO;
import com.truyenvn.core.persistence.entity.CommentEntity;

public class CommentBeanUtil {
    public static CommentDTO getDTO(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setCommentId(entity.getCommentId());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setBookDTO(BookBeanUtil.getDTO(entity.getBookEntity()));
        dto.setUserDTO(UserBeanUtil.getDTO(entity.getUserEntity()));
        return dto;
    }
    public static CommentEntity getEntity(CommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setCommentId(dto.getCommentId());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setBookEntity(BookBeanUtil.getEntity(dto.getBookDTO()));
        entity.setUserEntity(UserBeanUtil.getEntity(dto.getUserDTO()));
        return entity;
    }
}
