package com.truyen.controller.admin;

import com.truyen.command.AuthorCommand;
import com.truyen.controller.common.GeneralFunction;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.dto.AuthorDTO;
import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.utils.FormUtil;
import com.truyenvn.core.utils.RequestUtil;
import com.truyenvn.core.utils.SingletonServiceUtil;
import com.truyenvn.core.utils.WebCommonUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-home/author-management", "/ajax-author-edit"})
public class AuthorController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
    GeneralFunction generalFunction = new GeneralFunction();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthorCommand command = FormUtil.populate(AuthorCommand.class, request);
        if (command != null && command.getUrlType() != null) {
            if (command.getUrlType().equals(WebConstant.URL_LIST)) {
                //show list user
                RequestUtil.initSearchBean(request, command);
                ParameterToQuery parameterToQuery = generalFunction.buildParameterToQuery(command);
                Map<String, Object> mapValue = buildMapValue(command);
                parameterToQuery.setProperties(mapValue);
                Object[] objects = SingletonServiceUtil.getAuthorService().findAuthorByProperties(parameterToQuery);
                command.setListResult((List<AuthorDTO>) objects[1]);
                command.setTotalItems(Integer.parseInt(objects[0].toString()));
                command.setMaxPageItems(10);
                if (command.getCrudaction() != null) {
                    Map<String, String> mapMessage = buildMapMessage();
                    WebCommonUtil.addRedirectMessage(request, mapMessage, command.getCrudaction());
                }
                request.setAttribute(WebConstant.LIST_ITEM, command);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/author/list.jsp");
                rd.forward(request, response);
            } else if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
                //edit user form
                request.setAttribute(WebConstant.FORM_ITEM, command);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/author/edit.jsp");
                rd.forward(request, response);
            }
        }
    }
    private Map<String, String> buildMapMessage() {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
        mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.category.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.category.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.category.message.update.success"));
        return mapMessage;
    }

    private Map<String, Object> buildMapValue(AuthorCommand command) {
        Map<String, Object> properties = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(command.getPojo().getAuthorName())) {
            properties.put("authorName", command.getPojo().getAuthorName());
        }
        return properties;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthorCommand command = FormUtil.populate(AuthorCommand.class, request);
        if (command != null && command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.REDIRECT_DELETE)) {
                List<Integer> idS = new ArrayList<Integer>();
                if (command.getPojo().getAuthorId() != null) {
                    idS.add(command.getPojo().getAuthorId());
                } else {
                    for (String id : command.getCheckList()) {
                        idS.add(Integer.parseInt(id));
                    }
                }
                try {
                    SingletonServiceUtil.getAuthorService().deleteAuthor(idS);
                    response.sendRedirect("/admin-home/author-management?urlType=url_list&crudaction=redirect_delete");
                } catch (HibernateException e) {
                    response.sendRedirect("/admin-home/author-management?urlType=url_list&crudaction=redirect_error");
                }
            }
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT) && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
            if (command.getPojo() != null && command.getPojo().getAuthorId() != null) {
                //update category
                try {
                    SingletonServiceUtil.getAuthorService().updateAuthor(command.getPojo());
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
                } catch (HibernateException e) {
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
                }
            } else {
                //insert category
                try {
                    SingletonServiceUtil.getAuthorService().insertAuthor(command.getPojo());
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
                } catch (HibernateException e) {
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/author/edit.jsp");
            rd.forward(request, response);
        }
    }
}
