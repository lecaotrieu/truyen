package com.truyen.filter;

import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.common.utils.SessionUtil;
import com.truyenvn.core.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        UserDTO userDTO = (UserDTO) SessionUtil.getInstance().getSession(request, WebConstant.LOGIN_USER);
        if (url.startsWith("/admin")) {
            if (userDTO != null) {
                if (userDTO.getRoleDTO().getName().equals(WebConstant.ROLE_ADMIN)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (userDTO.getRoleDTO().getName().equals(WebConstant.ROLE_USER)) {
                    response.sendRedirect(request.getContextPath() + "/account-login?urlType=login&&crudaction=login_incompetent");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/account-login?urlType=login&&crudaction=not_logged");
            }
        } else {
            if (url.startsWith("/manage")) {
                if (userDTO == null) {
                    response.sendRedirect(request.getContextPath() + "/account-login?urlType=login");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    public void destroy() {

    }
}
