package com.truyenvn.core.service;

import com.truyenvn.core.dto.AuthorDTO;
import com.truyenvn.core.dto.ParameterToQuery;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> findAll();
    Integer insertAuthor(AuthorDTO authorDTO);
    Object[] findAuthorByProperties(ParameterToQuery properties);
     Integer deleteAuthor(List<Integer> idS);
     AuthorDTO updateAuthor(AuthorDTO authorDTO);
}
