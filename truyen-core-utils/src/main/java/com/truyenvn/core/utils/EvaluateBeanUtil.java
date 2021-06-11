package com.truyenvn.core.utils;

import com.truyenvn.core.dto.EvaluateDTO;
import com.truyenvn.core.persistence.entity.EvaluateEntity;

public class EvaluateBeanUtil {
    public static EvaluateDTO getDTO(EvaluateEntity entity) {
        EvaluateDTO dto = new EvaluateDTO();
        dto.setEvaluateId(entity.getEvaluateId());
        dto.setCount(entity.getCount());
        dto.setFollow(entity.getFollow());
        dto.setBookDTO(BookBeanUtil.getDTO(entity.getBookEntity()));
        dto.setUserDTO(UserBeanUtil.getDTO(entity.getUserEntity()));
        return dto;
    }

    public static EvaluateEntity getEntity(EvaluateDTO dto) {
        EvaluateEntity entity = new EvaluateEntity();
        entity.setEvaluateId(dto.getEvaluateId());
        entity.setCount(dto.getCount());
        entity.setFollow(dto.getFollow());
        entity.setBookEntity(BookBeanUtil.getEntity(dto.getBookDTO()));
        entity.setUserEntity(UserBeanUtil.getEntity(dto.getUserDTO()));
        return entity;
    }
}
