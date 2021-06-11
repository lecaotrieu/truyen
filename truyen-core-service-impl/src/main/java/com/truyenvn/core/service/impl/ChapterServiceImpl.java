package com.truyenvn.core.service.impl;

import com.lowagie.text.Chapter;
import com.truyenvn.core.dto.ChapterDTO;
import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.persistence.entity.ChapterEntity;
import com.truyenvn.core.service.ChapterService;
import com.truyenvn.core.service.utils.SingletonDaoUtil;
import com.truyenvn.core.utils.ChapterBeanUtil;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChapterServiceImpl implements ChapterService {
    public Object[] findChapterByProperties(ParameterToQuery parameters) throws HibernateException {
        String whereClause = " AND bookEntity.bookName = '" + parameters.getProperties().get("bookName") + "'";
        parameters.getProperties().remove("bookName");
        Object[] objects = SingletonDaoUtil.getChapterDAO().findByProperty(parameters.getProperties(), parameters.getSortExpression(),
                parameters.getSortDirection(), parameters.getOffset(), parameters.getLimit(), null, null, whereClause);
        List<ChapterDTO> chapterDTOS = new ArrayList<ChapterDTO>();
        for (ChapterEntity entity : (List<ChapterEntity>) objects[1]) {
            ChapterDTO chapterDTO = ChapterBeanUtil.getDTO(entity);
            chapterDTOS.add(chapterDTO);
        }
        objects[1] = chapterDTOS;
        return objects;
    }

    public Object[] findChapterParametersByProperties(ParameterToQuery parameters) {
        String whereClause = " AND bookEntity.bookName = '" + parameters.getProperties().get("bookName") + "'";
        parameters.getProperties().remove("bookName");
        String selectClause = " SELECT chapterId, chapter, title ";
        Object[] objects = SingletonDaoUtil.getChapterDAO().findByProperty(parameters.getProperties(), parameters.getSortExpression(),
                parameters.getSortDirection(), parameters.getOffset(), parameters.getLimit(), selectClause, null, whereClause);
        List<ChapterDTO> chapterDTOS = new ArrayList<ChapterDTO>();
        for (Object[] o : (List<Object[]>) objects[1]) {
            ChapterDTO chapterDTO = new ChapterDTO();
            chapterDTO.setChapterId(Integer.parseInt(o[0].toString()));
            chapterDTO.setChapter(Integer.parseInt(o[1].toString()));
            if (o[2] != null) {
                chapterDTO.setTitle((String) o[2]);
            }
            chapterDTOS.add(chapterDTO);
        }
        objects[1] = chapterDTOS;
        return objects;
    }

    public ChapterDTO findChapterByParameter(Map<String, Object> parameters) {
        ChapterDTO dto = null;
        ChapterEntity entity = SingletonDaoUtil.getChapterDAO().findByParameter(parameters);
        if (entity != null) {
            dto = ChapterBeanUtil.getDTO(entity);
        }
        return dto;
    }

    public ChapterDTO findById(Integer id) throws HibernateException {
        ChapterEntity entity = SingletonDaoUtil.getChapterDAO().findById(id);
        ChapterDTO dto = ChapterBeanUtil.getDTO(entity);
        return dto;
    }

    public Integer deleteChapter(List<Integer> idS) {
        Integer result = SingletonDaoUtil.getChapterDAO().delete(idS);
        return result;
    }

    public Integer findLastChapter(String bookName) {
        String whereClause = " AND bookEntity.bookName = '" + bookName + "'";
        Object[] objects = SingletonDaoUtil.getChapterDAO().findByProperty(null, "chapter", "2", null, 1, null, null, whereClause);
        List<ChapterEntity> chapterEntities = (List<ChapterEntity>) objects[1];
        ChapterEntity entity = chapterEntities.get(0);
        return entity.getChapter();
    }

    public ChapterDTO updateChapter(ChapterDTO chapterDTO) throws HibernateException {
        ChapterEntity entity = SingletonDaoUtil.getChapterDAO().findById(chapterDTO.getChapterId());
        if (chapterDTO.getTitle() == null) {
            chapterDTO.setTitle("");
        }
        if (chapterDTO.getContent() == null) {
            chapterDTO.setContent("");
        }
        entity.setContent(chapterDTO.getContent());
        entity.setTitle(chapterDTO.getTitle());
        entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        entity = SingletonDaoUtil.getChapterDAO().update(entity);
        chapterDTO = ChapterBeanUtil.getDTO(entity);
        return chapterDTO;
    }

    public void insertChapter(ChapterDTO chapterDTO) throws HibernateException {
        Integer chapter = SingletonDaoUtil.getChapterDAO().findPreviousChapter(chapterDTO.getBookDTO().getBookId());
        chapterDTO.setChapter(chapter);
        if (chapterDTO.getTitle() == null) {
            chapterDTO.setTitle("");
        }
        if (chapterDTO.getContent() == null) {
            chapterDTO.setContent("");
        }
        chapterDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        ChapterEntity entity = ChapterBeanUtil.getEntity(chapterDTO);
        SingletonDaoUtil.getChapterDAO().save(entity);
    }
}
