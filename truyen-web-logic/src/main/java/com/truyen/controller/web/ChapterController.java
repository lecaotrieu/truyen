package com.truyen.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truyen.command.BookCommand;
import com.truyen.command.ChapterCommand;
import com.truyen.controller.common.GeneralFunction;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.common.utils.SessionUtil;
import com.truyenvn.core.dto.*;
import com.truyenvn.core.service.utils.SingletonDaoUtil;
import com.truyenvn.core.utils.FormUtil;
import com.truyenvn.core.utils.HttpUtil;
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
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/ajax-chapter-list", "/doc-truyen"})
public class ChapterController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
    GeneralFunction generalFunction = new GeneralFunction();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChapterCommand command = FormUtil.populate(ChapterCommand.class, request);
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            command.setMaxPageItems(75);
            RequestUtil.initSearchBeanManual(command);
            ParameterToQuery parameterToQuery = generalFunction.buildParameterToQuery(command);
            Map<String, Object> mapValue = buildMapValue(command);
            parameterToQuery.setProperties(mapValue);
            parameterToQuery.setSortExpression("chapter");
            parameterToQuery.setSortDirection("1");
            Object[] objects = SingletonServiceUtil.getChapterService().findChapterParametersByProperties(parameterToQuery);
            List<ChapterDTO> chapterDTOS = (List<ChapterDTO>) objects[1];
            command.setListResult(chapterDTOS);
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            command.setTotalPage((int) Math.ceil((double) command.getTotalItems() / command.getMaxPageItems()));
            request.setAttribute(WebConstant.LIST_ITEM, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/book/chapter-list.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            Map<String, Object> mapParameters = buildMapParameters(command);
            ChapterDTO chapterDTO = SingletonServiceUtil.getChapterService().findChapterByParameter(mapParameters);
            Integer lastChapter = SingletonServiceUtil.getChapterService().findLastChapter(command.getBookName());
            command.setLastChapter(lastChapter);
            if (chapterDTO == null) {
                response.sendRedirect("/detail-book?urlType=url_list&bookName=" + command.getBookName() + "&crudaction=redirect_error");
            } else {
                command.setPojo(chapterDTO);
                request.setAttribute(WebConstant.FORM_ITEM, command);
                RequestDispatcher rd = request.getRequestDispatcher("views/web/chapter/show.jsp");
                rd.forward(request, response);
            }
        }
    }

    private Map<String, Object> buildMapParameters(ChapterCommand command) {
        Map<String, Object> mapValue = new HashMap<String, Object>();
        if (command.getBookName() != null) {
            mapValue.put("bookEntity.bookName", command.getBookName());
        }
        if (command.getPojo().getChapter() != null) {
            mapValue.put("chapter", command.getPojo().getChapter());
        }
        return mapValue;
    }

    private Map<String, Object> buildMapValue(ChapterCommand command) {
        Map<String, Object> mapValue = new HashMap<String, Object>();
        if (command.getBookName() != null) {
            mapValue.put("bookName", command.getBookName());
        }
        return mapValue;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChapterCommand command = FormUtil.populate(ChapterCommand.class, request);
    }
}
