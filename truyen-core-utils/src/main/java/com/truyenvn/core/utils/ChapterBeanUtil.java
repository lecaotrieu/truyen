package com.truyenvn.core.utils;

import com.truyenvn.core.dto.ChapterDTO;
import com.truyenvn.core.persistence.entity.ChapterEntity;

public class ChapterBeanUtil {
    public static ChapterDTO getDTO (ChapterEntity entity) {
        ChapterDTO dto = new ChapterDTO();
        dto.setChapterId(entity.getChapterId());
        dto.setChapter(entity.getChapter());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setTitle(entity.getTitle());
        dto.setView(entity.getView());
        if (entity.getBookEntity()!=null) {
            dto.setBookDTO(BookBeanUtil.getDTO(entity.getBookEntity()));
        }
        return dto;
    }
    public static ChapterEntity getEntity (ChapterDTO dto) {
        ChapterEntity entity = new ChapterEntity();
        entity.setChapterId(dto.getChapterId());
        entity.setChapter(dto.getChapter());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setTitle(dto.getTitle());
        entity.setView(dto.getView());
        if (dto.getBookDTO()!=null) {
            entity.setBookEntity(BookBeanUtil.getEntity(dto.getBookDTO()));
        }
        return entity;
    }
}
