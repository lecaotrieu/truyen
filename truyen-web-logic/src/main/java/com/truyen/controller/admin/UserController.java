package com.truyen.controller.admin;

import com.truyen.command.UserCommand;
import com.truyen.controller.common.GeneralFunction;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.dto.RoleDTO;
import com.truyenvn.core.dto.UserDTO;
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

@WebServlet(urlPatterns = {"/admin-home/user-management", "/ajax-user-edit"})
public class UserController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
    GeneralFunction generalFunction = new GeneralFunction();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        if (command != null && command.getUrlType() != null) {
            if (command.getUrlType().equals(WebConstant.URL_LIST)) {
                //show list user
                RequestUtil.initSearchBean(request, command);
                ParameterToQuery parameterToQuery = generalFunction.buildParameterToQuery(command);
                Object[] objects = SingletonServiceUtil.getUserService().findUserByProperties(parameterToQuery);
                command.setListResult((List<UserDTO>) objects[1]);
                command.setTotalItems(Integer.parseInt(objects[0].toString()));
                command.setMaxPageItems(10);
                if (command.getCrudaction() != null) {
                    Map<String, String> mapMessage = buildMapMessage();
                    WebCommonUtil.addRedirectMessage(request, mapMessage, command.getCrudaction());
                }
                request.setAttribute(WebConstant.LIST_ITEM, command);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/list.jsp");
                rd.forward(request, response);
            } else if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
                //edit user form
                List<RoleDTO> roles = SingletonServiceUtil.getRoleService().findAll();
                command.setRoles(roles);
                if (command.getPojo().getUserId() != null) {
                    UserDTO userDTO = SingletonServiceUtil.getUserService().findById(command.getPojo().getUserId());
                    command.setPojo(userDTO);
                }
                request.setAttribute(WebConstant.FORM_ITEM, command);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
                rd.forward(request, response);
            }
        }
    }
    private Map<String, String> buildMapMessage() {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
        mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.user.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.user.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.user.message.update.success"));
        return mapMessage;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UploadUtil uploadUtil = new UploadUtil();
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.USER_AVATAR_URL)) {
            Set<String> valueTitle = buildSetValueUser();
            String path = WebConstant.USER_AVATAR + File.separator + command.getPojo().getUserId();
            Object[] objects = uploadUtil.writeOrUpdateFile(request, valueTitle, path);
            boolean checkStatusUploadImage = (Boolean) objects[0];
            if (StringUtils.isBlank((String) objects[1])) checkStatusUploadImage = false;
            if (checkStatusUploadImage) {
                String avatarLocation = (String) objects[2];
                command.getPojo().setAvatar(avatarLocation);
                SingletonServiceUtil.getUserService().updateAvatar(command.getPojo());
            }
            response.sendRedirect("/admin-home/user-management?urlType=url_list");
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT) && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId(command.getRoleId());
            command.getPojo().setRoleDTO(roleDTO);
            if (StringUtils.isBlank(command.getPojo().getName())) {
                command.getPojo().setName(null);
            }
            if (command.getPojo() != null && command.getPojo().getUserId() != null) {
                //update user
                try {
                    SingletonServiceUtil.getUserService().updateUser(command.getPojo());
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
                } catch (HibernateException e) {
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
                }

            } else {
                //insert user
                try {
                    SingletonServiceUtil.getUserService().insertUser(command.getPojo());
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
                } catch (HibernateException e) {
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST) && command.getCrudaction().equals(WebConstant.REDIRECT_DELETE)) {
            List<Integer> ids = new ArrayList<Integer>();
            if (command.getPojo()!=null && command.getPojo().getUserId()!=null){
                ids.add(command.getPojo().getUserId());
            }else {
                for (String id : command.getCheckList()) {
                    ids.add(Integer.parseInt(id));
                }
            }
            Integer result = SingletonServiceUtil.getUserService().deleteUser(ids);
            if (result != -1) {
                response.sendRedirect("/admin-home/user-management?urlType=url_list&crudaction=redirect_delete");
            } else {
                response.sendRedirect("/admin-home/user-management?urlType=url_list&crudaction=redirect_error");
            }
        }
    }

    private Set<String> buildSetValueUser() {
        Set<String> valueTitle = new HashSet<String>();
        return valueTitle;
    }
}
