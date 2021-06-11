package com.truyenvn.core.service.impl;

import com.truyenvn.core.dto.CommentDTO;
import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.persistence.entity.CommentEntity;
import com.truyenvn.core.service.CommentService;
import com.truyenvn.core.service.utils.SingletonDaoUtil;
import com.truyenvn.core.utils.CommentBeanUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements CommentService {
    public Object[] findByProperties(ParameterToQuery parameterToQuery) {
        String whereClause = " AND bookEntity.bookName = '" + parameterToQuery.getProperties().get("bookName") + "'";
        parameterToQuery.getProperties().remove("bookName");
        Object[] objects = SingletonDaoUtil.getCommentDAO().findByProperty(parameterToQuery.getProperties(), parameterToQuery.getSortExpression(), parameterToQuery.getSortDirection(), parameterToQuery.getOffset(), parameterToQuery.getLimit(), null, null, whereClause);
        List<CommentDTO> commentDTOS = new ArrayList<CommentDTO>();
        for (CommentEntity entity : (List<CommentEntity>) objects[1]) {
            CommentDTO commentDTO = CommentBeanUtil.getDTO(entity);
            commentDTOS.add(commentDTO);
        }
        objects[1] = commentDTOS;
        return objects;
    }

    public Integer findTotalComment(String bookName) {
        Map<String,Object> mapParamenter = new HashMap<String, Object>();
        mapParamenter.put("bookEntity.bookName",bookName);
        Integer count = SingletonDaoUtil.getCommentDAO().countByParameter(mapParamenter);
        return count;
    }
}
