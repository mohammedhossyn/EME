package com.eme.controller.servlet;


import com.eme.controller.BookController;
import com.eme.controller.MeController;
import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.model.entity.Book;
import com.eme.model.entity.Me;
import com.eme.model.entity.enums.TransactionStatus;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/mainPage")
public class MainServlet extends HttpServlet {

    @Inject
    private MeController meController;

    @Inject
    private BookController bookController;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Me> me = (List<Me>) meController.findAll().get(TransactionStatus.Done);
            List<Book> books = (List<Book>) bookController.findAll().get(TransactionStatus.Done);
            req.getSession().setAttribute("me", me.get(0));
            req.getSession().setAttribute("books", books);
            
        } catch (Exception e) {
           exceptionWrapper.getMessage(e);
        }
        resp.sendRedirect("/main/main.jsp");
    }

}
