package com.truyenvn.core.dao;

import com.truyenvn.core.data.dao.GenericDAO;
import com.truyenvn.core.persistence.entity.ChapterEntity;

public interface ChapterDAO extends GenericDAO<Integer, ChapterEntity> {
    Integer findPreviousChapter(Integer bookId);
}
