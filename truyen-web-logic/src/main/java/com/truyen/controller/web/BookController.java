package com.truyen.controller.web;

import com.truyen.command.BookCommand;
import com.truyen.command.ChapterCommand;
import com.truyen.controller.common.GeneralFunction;
import com.truyenvn.core.common.WebConstant;
import com.truyenvn.core.common.utils.SessionUtil;
import com.truyenvn.core.dto.*;
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
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/detail-book"})
public class BookController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
    GeneralFunction generalFunction = new GeneralFunction();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookCommand command = FormUtil.populate(BookCommand.class, request);
        List<CategoryDTO> categoryDTOS = SingletonServiceUtil.getCategoryService().findAll();
        request.setAttribute("category_list", categoryDTOS);
        UserDTO userDTO = (UserDTO) SessionUtil.getInstance().getSession(request, WebConstant.LOGIN_USER);
        Map<String,Object> mapParameters = buildMapParameters(command);
        BookDTO bookDTO = SingletonServiceUtil.getBookService().findBookByParameters(mapParameters);
        Integer totalComment = SingletonServiceUtil.getCommentService().findTotalComment(bookDTO.getBookName());
        command.setTotalComment(totalComment);
        command.setPojo(bookDTO);
        request.setAttribute(WebConstant.FORM_ITEM, command);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/book/show.jsp");
        rd.forward(request, response);
    }

    private Map<String, Object> buildMapParameters(BookCommand command) {
        Map<String, Object> mapValue = new HashMap<String, Object>();
        if (command.getPojo().getBookName() != null) {
            mapValue.put("bookName", command.getPojo().getBookName());
        }
        return mapValue;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookCommand command = FormUtil.populate(BookCommand.class, request);
    }
}
