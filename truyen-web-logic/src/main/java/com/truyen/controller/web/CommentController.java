package com.truyen.controller.web;

import com.truyen.command.CommentCommand;
import com.truyen.controller.common.GeneralFunction;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.dto.CommentDTO;
import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.utils.FormUtil;
import com.truyenvn.core.utils.RequestUtil;
import com.truyenvn.core.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/ajax-comment-list"})
public class CommentController extends HttpServlet {
    GeneralFunction generalFunction = new GeneralFunction();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentCommand command = FormUtil.populate(CommentCommand.class, request);
        if (command!=null && command.getUrlType()!=null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            command.setMaxPageItems(10);
            RequestUtil.initSearchBeanManual(command);
            ParameterToQuery parameterToQuery = generalFunction.buildParameterToQuery(command);
            Map<String, Object> mapProperties = buildMapProperties(command);
            parameterToQuery.setProperties(mapProperties);
            Object[] objects = SingletonServiceUtil.getCommentService().findByProperties(parameterToQuery);
            command.setTotalItems((Integer) objects[0]);
            command.setListResult((List<CommentDTO>) objects[1]);
            request.setAttribute(WebConstant.LIST_ITEM,command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/book/comment.jsp");
            rd.forward(request,response);
        }
    }

    private Map<String, Object> buildMapProperties(CommentCommand command) {
        Map<String,Object> mapValues = new HashMap<String, Object>();
        if (command.getBookName()!=null) {
            mapValues.put("bookName",command.getBookName());
        }
        return mapValues;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
