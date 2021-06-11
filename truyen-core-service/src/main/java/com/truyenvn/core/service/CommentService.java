package com.truyenvn.core.service;

import com.truyenvn.core.dto.ParameterToQuery;

public interface CommentService {
    Object[] findByProperties(ParameterToQuery parameterToQuery);
    Integer findTotalComment(String bookName);
}
