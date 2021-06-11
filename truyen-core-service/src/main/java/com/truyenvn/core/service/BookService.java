package com.truyenvn.core.service;

import com.truyenvn.core.dto.BookDTO;
import com.truyenvn.core.dto.ParameterToQuery;

import java.util.List;
import java.util.Map;

public interface BookService {
    Object[] findBookByProperties(ParameterToQuery properties);
    void insertBook(BookDTO dto);
    BookDTO updateBook(BookDTO dto);
    BookDTO findById(Integer iD);
    BookDTO findUniqueBook(String property, Object value);
    BookDTO findBookByParameters(Map<String,Object> parameters);
    Integer deleteBook(List<Integer> idS);
}
