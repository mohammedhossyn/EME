package com.eme.controller.servlet;


import com.eme.controller.MeController;
import com.eme.model.entity.Me;
import com.eme.model.entity.enums.TransactionStatus;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/mainPage")
public class MainServlet extends HttpServlet {

    @Inject
    private MeController meController;

//    @Inject
//    private BookController bookController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List result = (List) meController.findAll().get(TransactionStatus.Done);
        req.getSession().setAttribute("me", result.get(0));
//        req.getSession().setAttribute("books", bookController.findAll());
    }
}
