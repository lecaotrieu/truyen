package com.truyenvn.core.utils;

import com.truyenvn.core.dto.AuthorDTO;
import com.truyenvn.core.dto.BookDTO;
import com.truyenvn.core.persistence.entity.AuthorEntity;
import com.truyenvn.core.persistence.entity.BookEntity;

import java.util.HashSet;
import java.util.Set;

public class AuthorBeanUtil {
    public static AuthorDTO getDTO(AuthorEntity entity) {
        AuthorDTO dto = new AuthorDTO();
        dto.setAuthorId(entity.getAuthorId());
        dto.setAuthorName(entity.getAuthorName());
        dto.setContent(entity.getContent());
        dto.setSex(entity.getSex());
        return dto;
    }

    public static AuthorEntity getEntity(AuthorDTO dto) {
        AuthorEntity entity = new AuthorEntity();
        entity.setAuthorId(dto.getAuthorId());
        entity.setAuthorName(dto.getAuthorName());
        entity.setContent(dto.getContent());
        entity.setSex(dto.getSex());
        return entity;
    }
}
