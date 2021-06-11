package com.truyen.controller.admin;

import com.truyen.command.BookCommand;
import com.truyen.controller.common.GeneralFunction;
import com.truyenvn.core.service.utils.StringGlobalUtils;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.common.utils.SessionUtil;
import com.truyenvn.core.dto.*;
import com.truyenvn.core.utils.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-home/book-management"})
public class BookController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
    GeneralFunction generalFunction = new GeneralFunction();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookCommand command = FormUtil.populate(BookCommand.class, request);
        if (command != null && command.getUrlType() != null) {
            if (command.getUrlType().equals(WebConstant.URL_LIST)) {
                //show list user
                command.setMaxPageItems(5);
                RequestUtil.initSearchBean(request, command);
                ParameterToQuery parameterToQuery = generalFunction.buildParameterToQuery(command);
                Map<String, Object> mapValue = buildMapValue(command);
                parameterToQuery.setProperties(mapValue);
                Object[] objects = SingletonServiceUtil.getBookService().findBookByProperties(parameterToQuery);
                command.setListResult((List<BookDTO>) objects[1]);
                command.setTotalItems(Integer.parseInt(objects[0].toString()));
                if (command.getCrudaction() != null) {
                    Map<String, String> mapMessage = buildMapMessage();
                    WebCommonUtil.addRedirectMessage(request, mapMessage, command.getCrudaction());
                }
                request.setAttribute(WebConstant.LIST_ITEM, command);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/list.jsp");
                rd.forward(request, response);
            } else if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
                if (command.getPojo().getBookId() != null) {
                    command.setPojo(SingletonServiceUtil.getBookService().findById(command.getPojo().getBookId()));
                }
                List<CategoryDTO> categoryDTOS = SingletonServiceUtil.getCategoryService().findAll();
                command.setCategoryS(categoryDTOS);
                List<AuthorDTO> authorDTOS = SingletonServiceUtil.getAuthorService().findAll();
                command.setAuthorS(authorDTOS);
                request.setAttribute(WebConstant.FORM_ITEM, command);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
                rd.forward(request, response);
            }
        }
    }

    private Map<String, String> buildMapMessage() {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
        mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.book.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.book.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.book.message.update.success"));
        return mapMessage;
    }

    private Map<String, Object> buildMapValue(BookCommand command) {
        Map<String, Object> properties = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(command.getPojo().getBookFullName())) {
            properties.put("bookFullName", command.getPojo().getBookFullName());
        }
        return properties;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookCommand command = FormUtil.populate(BookCommand.class, request);
        UploadUtil uploadUtil = new UploadUtil();
        if (command != null && command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            String path = WebConstant.BOOK_POSTER;
            Set<String> titleValue = buildTitleValue();
            Object[] objects = uploadUtil.writeOrUpdateFile(request, titleValue, path);
            BookDTO bookDTO = new BookDTO();
            if (StringUtils.isNotBlank((String) objects[2])) {
                bookDTO.setPoster((String) objects[2]);
            }
            bookDTO = returnValueOfDTO(bookDTO, (Map<String, Object>) objects[3]);
            if (bookDTO.getBookId() != null) {
                try {
                    SingletonServiceUtil.getBookService().updateBook(bookDTO);
                    response.sendRedirect("/admin-home/book-management?urlType=url_list&crudaction=redirect_update");
                } catch (HibernateException e) {
                    response.sendRedirect("/admin-home/book-management?urlType=url_list&crudaction=redirect_error");
                }
            } else {
                UserDTO userDTO = (UserDTO) SessionUtil.getInstance().getSession(request, WebConstant.LOGIN_USER);
                bookDTO.setUserDTO(userDTO);
                try {
                    SingletonServiceUtil.getBookService().insertBook(bookDTO);
                    response.sendRedirect("/admin-home/book-management?urlType=url_list&crudaction=redirect_insert");
                } catch (HibernateException e) {
                    response.sendRedirect("/admin-home/book-management?urlType=url_list&crudaction=redirect_error");
                }


            }
        } else if (command != null && command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.REDIRECT_DELETE)) {
                List<Integer> idS = new ArrayList<Integer>();
                if (command.getPojo().getBookId() != null) {
                    idS.add(command.getPojo().getBookId());
                } else {
                    for (String id : command.getCheckList()) {
                        idS.add(Integer.parseInt(id));
                    }
                }
                try {
                    SingletonServiceUtil.getBookService().deleteBook(idS);
                    response.sendRedirect("/admin-home/book-management?urlType=url_list&crudaction=redirect_delete");
                } catch (HibernateException e) {
                    response.sendRedirect("/admin-home/book-management?urlType=url_list&crudaction=redirect_error");
                }
            }
        }
    }

    private BookDTO returnValueOfDTO(BookDTO dto, Map<String, Object> mapValue) {
        for (Map.Entry<String, Object> item : mapValue.entrySet()) {
            if (item.getKey().equals("pojo.bookFullName") && StringUtils.isNotBlank((String) item.getValue())) {
                dto.setBookFullName((String) item.getValue());
            } else if (item.getKey().equals("pojo.brief")) {
                dto.setBrief((String) item.getValue());
            } else if (item.getKey().equals("checkList")) {
                List<CategoryDTO> categoryDTOS = new ArrayList<CategoryDTO>();
                if (item.getValue() instanceof List) {
                    for (String str : (List<String>) item.getValue()) {
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setCategoryId(Integer.parseInt(str));
                        categoryDTOS.add(categoryDTO);
                    }
                } else {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setCategoryId(Integer.parseInt(item.getValue().toString()));
                    categoryDTOS.add(categoryDTO);
                }
                dto.setCategoryDTOS(categoryDTOS);
            } else if (item.getKey().equals("pojo.authorDTO.authorName")) {
                AuthorDTO authorDTO = new AuthorDTO();
                if (StringUtils.isBlank(item.getValue().toString())) {
                    authorDTO.setAuthorId(0);
                } else {
                    try {
                        authorDTO.setAuthorId(Integer.parseInt(item.getValue().toString()));
                    } catch (NumberFormatException e) {
                        authorDTO.setAuthorName(item.getValue().toString());
                        Integer authorId = SingletonServiceUtil.getAuthorService().insertAuthor(authorDTO);
                        authorDTO.setAuthorId(authorId);
                    }
                }
                dto.setAuthorDTO(authorDTO);
            } else if (item.getKey().equals("pojo.bookId")) {
                dto.setBookId(Integer.parseInt((String) item.getValue()));
            } else if (item.getKey().equals("pojo.userDTO.userId")) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserId(Integer.parseInt((String) item.getValue()));
                dto.setUserDTO(userDTO);
            } else if (dto.getPoster() == null && item.getKey().equals("pojo.poster")) {
                if (dto.getBookId() != null) {
                    dto.setPoster((String) item.getValue());
                } else {
                    dto.setPoster(WebConstant.BOOK_POSTER + File.separator + "null" + File.separator + "poster.jpg");
                }
            }
        }
        return dto;
    }

    private Set<String> buildTitleValue() {
        Set<String> titleValue = new HashSet<String>();
        titleValue.add("pojo.bookFullName");
        titleValue.add("pojo.brief");
        titleValue.add("checkList");
        titleValue.add("pojo.bookId");
        titleValue.add("pojo.authorDTO.authorName");
        titleValue.add("pojo.userDTO.userId");
        titleValue.add("pojo.poster");
        return titleValue;

    }


}
