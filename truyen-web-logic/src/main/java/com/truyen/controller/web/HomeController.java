package com.truyen.controller.web;

import com.truyen.command.CategoryCommand;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.dto.CategoryDTO;
import com.truyenvn.core.utils.FormUtil;
import com.truyenvn.core.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryCommand categoryCommand = FormUtil.populate(CategoryCommand.class,request);
        List<CategoryDTO> categoryDTOS = SingletonServiceUtil.getCategoryService().findAll();
        request.setAttribute("category_list", categoryDTOS);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
