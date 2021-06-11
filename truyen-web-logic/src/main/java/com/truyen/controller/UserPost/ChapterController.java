package com.truyen.controller.UserPost;

import com.truyen.command.ChapterCommand;
import com.truyen.controller.common.GeneralFunction;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.common.utils.SessionUtil;
import com.truyenvn.core.dto.*;
import com.truyenvn.core.utils.*;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/manage-chapter"})
public class ChapterController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
    GeneralFunction generalFunction = new GeneralFunction();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChapterCommand command = FormUtil.populate(ChapterCommand.class, request);
        UserDTO userDTO = (UserDTO) SessionUtil.getInstance().getSession(request, WebConstant.LOGIN_USER);
        try {
            BookDTO bookDTO = SingletonServiceUtil.getBookService().findUniqueBook("bookName", command.getBookName());
            command.setBookId(bookDTO.getBookId());
            if (bookDTO == null || !userDTO.getUserId().equals(bookDTO.getUserDTO().getUserId())) {
                response.sendRedirect("/manage-book?urlType=url_list&crudaction=redirect_error");
            } else {
                if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
                    command.setMaxPageItems(5);
                    RequestUtil.initSearchBeanManual(command);
                    ParameterToQuery parameterToQuery = generalFunction.buildParameterToQuery(command);
                    Map<String, Object> properties = buildMapValue(command);
                    parameterToQuery.setProperties(properties);
                    Object[] objects = SingletonServiceUtil.getChapterService().findChapterByProperties(parameterToQuery);
                    command.setListResult((List<ChapterDTO>) objects[1]);
                    command.setTotalItems(Integer.parseInt(objects[0].toString()));
                    command.setTotalPage((int) Math.ceil((double) command.getTotalItems() / command.getMaxPageItems()));
                    if (command.getCrudaction() != null) {
                        Map<String, String> mapMessage = buildMapMessage();
                        WebCommonUtil.addRedirectMessage(request, mapMessage, command.getCrudaction());
                    }
                    request.setAttribute(WebConstant.LIST_ITEM, command);
                    RequestDispatcher rd = request.getRequestDispatcher("views/web/chapter/list.jsp");
                    rd.forward(request, response);
                } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
                    if (command.getPojo().getChapter() != null) {
                        Map<String,Object> mapParameters = buildMapParameters(command);
                        ChapterDTO chapterDTO = SingletonServiceUtil.getChapterService().findChapterByParameter(mapParameters);
                        if (chapterDTO == null) {
                            response.sendRedirect("/manage-chapter?urlType=url_list&bookName=" + command.getBookName() + "&crudaction=redirect_error");
                        } else {
                            command.setPojo(chapterDTO);
                            request.setAttribute(WebConstant.FORM_ITEM, command);
                            RequestDispatcher rd = request.getRequestDispatcher("views/web/chapter/edit.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute(WebConstant.FORM_ITEM, command);
                        RequestDispatcher rd = request.getRequestDispatcher("views/web/chapter/edit.jsp");
                        rd.forward(request, response);
                    }
                }
            }
        } catch (HibernateException e) {
            response.sendRedirect("/manage-book?urlType=url_list&crudaction=redirect_error");
        }
    }

    private Map<String, Object> buildMapParameters(ChapterCommand command) {
        Map<String,Object> mapValue = new HashMap<String, Object>();
        if (command.getBookName()!=null) {
            mapValue.put("bookEntity.bookName",command.getBookName());
        }
        if (command.getPojo().getChapter()!=null) {
            mapValue.put("chapter",command.getPojo().getChapter());
        }
        return mapValue;
    }


    private Map<String, String> buildMapMessage() {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
        mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.chapter.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.chapter.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.chapter.message.update.success"));
        return mapMessage;
    }

    private Map<String, Object> buildMapValue(ChapterCommand command) {
        Map<String, Object> mapValue = new HashMap<String, Object>();
        if (command.getPojo().getChapter() != null) {
            mapValue.put("chapter", command.getPojo().getChapter());
        }
        if (command.getPojo().getTitle() != null) {
            mapValue.put("title", command.getPojo().getTitle());
        }
        if (command.getBookName() != null) {
            mapValue.put("bookName", command.getBookName());
        }
        return mapValue;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChapterCommand command = FormUtil.populate(ChapterCommand.class, request);
        UserDTO userDTO = (UserDTO) SessionUtil.getInstance().getSession(request, WebConstant.LOGIN_USER);
        if (command.getPojo().getChapterId() == null) {
            UploadUtil uploadUtil = new UploadUtil();
            Set<String> titleValue = buildTitleValue();
            Object[] objects = uploadUtil.writeOrUpdateFile(request, titleValue, null);
            command = returnValueOfDTO(command, (Map<String, Object>) objects[3]);
        }
        try {
            if (command.getBookName() != null) {
                BookDTO bookDTO = SingletonServiceUtil.getBookService().findUniqueBook("bookName", command.getBookName());
                if (!userDTO.getUserId().equals(bookDTO.getUserDTO().getUserId()) || bookDTO == null) {
                    response.sendRedirect("/manage-book?urlType=url_list&crudaction=redirect_error");
                } else {
                    if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
                        if (command.getPojo().getChapter() != null) {
                            command.getPojo().setBookDTO(bookDTO);
                            SingletonServiceUtil.getChapterService().updateChapter(command.getPojo());
                            response.sendRedirect("/manage-chapter?urlType=url_list&bookName=" + command.getBookName() + "&crudaction=redirect_update");
                        } else {
                            command.getPojo().setBookDTO(bookDTO);
                            SingletonServiceUtil.getChapterService().insertChapter(command.getPojo());
                            response.sendRedirect("/manage-chapter?urlType=url_list&bookName=" + command.getBookName() + "&crudaction=redirect_insert");
                        }
                    } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
                        if (command.getCrudaction()!=null && command.getCrudaction().equals(WebConstant.REDIRECT_DELETE)){
                            List<Integer> idS = new ArrayList<Integer>();
                            idS.add(command.getPojo().getChapterId());
                            Integer i = SingletonServiceUtil.getChapterService().deleteChapter(idS);
                            response.sendRedirect("/manage-chapter?urlType=url_list&bookName=" + command.getBookName() + "&page=" + command.getPage() + "&crudaction=redirect_delete");
                        }
                    }
                }
            } else {
                response.sendRedirect("/manage-book?urlType=url_list&crudaction=redirect_error");
            }
        } catch (HibernateException e) {
            response.sendRedirect("/manage-book?urlType=url_list&crudaction=redirect_error");
        }
    }

    private ChapterCommand returnValueOfDTO(ChapterCommand command, Map<String, Object> mapValue) {
        ChapterDTO dto = new ChapterDTO();
        for (Map.Entry<String, Object> item : mapValue.entrySet()) {
            if (item.getKey().equals("bookId")) {
                command.setBookId(Integer.parseInt(item.getValue().toString()));
            } else if (item.getKey().equals("bookName")) {
                command.setBookName((String) item.getValue());
            } else if (item.getKey().equals("pojo.content")) {
                dto.setContent((String) item.getValue());
            } else if (item.getKey().equals("pojo.title")) {
                dto.setTitle((String) item.getValue());
            } else if (item.getKey().equals("pojo.chapter")) {
                dto.setChapter(Integer.parseInt(item.getValue().toString()));
            } else if (item.getKey().equals("pojo.chapterId")) {
                dto.setChapterId(Integer.parseInt(item.getValue().toString()));
            } else if (item.getKey().equals("urlType")) {
                command.setUrlType((String) item.getValue());
            }
        }
        command.setPojo(dto);
        return command;
    }

    private Set<String> buildTitleValue() {
        Set<String> titleValue = new HashSet<String>();
        titleValue.add("bookId");
        titleValue.add("pojo.chapterId");
        titleValue.add("pojo.chapter");
        titleValue.add("bookName");
        titleValue.add("pojo.title");
        titleValue.add("pojo.content");
        titleValue.add("urlType");
        return titleValue;

    }
}
