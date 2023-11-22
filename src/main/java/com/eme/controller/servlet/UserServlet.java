package com.eme.controller.servlet;

import com.eme.controller.UserController;
import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.model.entity.User;
import com.eme.model.entity.enums.Status;
import com.eme.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/user.do")
public class UserServlet extends HttpServlet {

    @Inject
    private UserController userController;

    @Inject
    private ExceptionWrapper exceptionWrapper;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            userController.save(username,password);
        } catch (Exception e) {
            exceptionWrapper.getMessage(e);
        }
        resp.sendRedirect("/index.jsp");
    }
}
