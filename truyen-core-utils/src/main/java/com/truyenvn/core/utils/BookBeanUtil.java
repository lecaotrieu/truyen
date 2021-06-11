package com.truyenvn.core.utils;

import com.truyenvn.core.dto.BookDTO;
import com.truyenvn.core.dto.CategoryDTO;
import com.truyenvn.core.dto.ChapterDTO;
import com.truyenvn.core.dto.EvaluateDTO;
import com.truyenvn.core.persistence.entity.BookEntity;
import com.truyenvn.core.persistence.entity.CategoryEntity;
import com.truyenvn.core.persistence.entity.ChapterEntity;
import com.truyenvn.core.persistence.entity.EvaluateEntity;

import java.util.HashSet;
import java.util.Set;

public class BookBeanUtil {
    public static BookDTO getDTO(BookEntity entity) {
        BookDTO dto = new BookDTO();
        dto.setBookId(entity.getBookId());
        dto.setBookFullName(entity.getBookFullName());
        dto.setBookName(entity.getBookName());
        dto.setBrief(entity.getBrief());
        dto.setPoster(entity.getPoster());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setAuthorDTO(AuthorBeanUtil.getDTO(entity.getAuthorEntity()));
        if (entity.getUserEntity()!=null) {
            dto.setUserDTO(UserBeanUtil.getDTO(entity.getUserEntity()));
        }
        return dto;
    }

    public static BookEntity getEntity(BookDTO dto) {
        BookEntity entity = new BookEntity();
        entity.setBookId(dto.getBookId());
        entity.setBookFullName(dto.getBookFullName());
        entity.setBookName(dto.getBookName());
        entity.setBrief(dto.getBrief());
        entity.setPoster(dto.getPoster());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setAuthorEntity(AuthorBeanUtil.getEntity(dto.getAuthorDTO()));
        if (dto.getUserDTO()!=null ){
            entity.setUserEntity(UserBeanUtil.getEntity(dto.getUserDTO()));
        }
        return entity;
    }
}
