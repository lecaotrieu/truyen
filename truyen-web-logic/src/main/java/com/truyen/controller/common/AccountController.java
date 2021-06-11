package com.truyen.controller.common;

import com.truyen.command.UserCommand;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.common.utils.SessionUtil;
import com.truyenvn.core.dto.UserDTO;
import com.truyenvn.core.utils.FormUtil;
import com.truyenvn.core.utils.SingletonServiceUtil;
import com.truyenvn.core.utils.WebCommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/account-login", "/admin-account-login"})
public class AccountController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        if (command != null && command.getUrlType() != null) {
            if (command.getUrlType().equals(WebConstant.LOGIN)) {
                if (command.getCrudaction() != null) {
                    Map<String, String> mapMessage = buildMapMessage();
                    WebCommonUtil.addRedirectMessage(request, mapMessage, command.getCrudaction());
                }
                RequestDispatcher rd = request.getRequestDispatcher("/views/account/login.jsp");
                rd.forward(request, response);
            } else if (command.getUrlType().equals(WebConstant.LOGOUT)) {
                SessionUtil.getInstance().removeSession(request, WebConstant.LOGIN_USER);
                response.sendRedirect("/trang-chu");
            } else if (command.getUrlType().equals(WebConstant.LOGIN_ERROR)) {
                request.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.login.error"));
                RequestDispatcher rd = request.getRequestDispatcher("/views/account/login.jsp");
                rd.forward(request, response);
            }
        }
    }

    private Map<String, String> buildMapMessage() {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
        mapMessage.put(WebConstant.LOGIN_INCOMPETENT, bundle.getString("label.login.error.incompetent"));
        mapMessage.put(WebConstant.NOT_LOGGED, bundle.getString("label.login.not.logged"));
        return mapMessage;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.LOGIN)) {
            UserDTO loginUser = SingletonServiceUtil.getUserService().userLogin(command.getPojo());
            if (loginUser.getUserId() != null) {
                SessionUtil.getInstance().putSession(request, WebConstant.LOGIN_USER, loginUser);
                if (loginUser.getRoleDTO().getRoleId() == 1) {
                    response.sendRedirect("/admin-home");
                } else if (loginUser.getRoleDTO().getRoleId() == 2) {
                    response.sendRedirect("/trang-chu");
                }
            } else {
                String uri = request.getRequestURI();
                response.sendRedirect(uri + "?urlType=login_error");
            }
        }
    }
}
