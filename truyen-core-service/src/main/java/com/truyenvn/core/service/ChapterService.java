package com.truyenvn.core.service;

import com.truyenvn.core.dto.ChapterDTO;
import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.persistence.entity.BookEntity;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    Object[] findChapterByProperties(ParameterToQuery parameters);
    Object[] findChapterParametersByProperties(ParameterToQuery parameters);
    ChapterDTO findChapterByParameter(Map<String,Object> parameters);
    ChapterDTO findById(Integer id);
    Integer deleteChapter(List<Integer> idS);
    Integer findLastChapter(String bookName);
    ChapterDTO updateChapter(ChapterDTO chapterDTO);
    void insertChapter(ChapterDTO chapterDTO);
}
