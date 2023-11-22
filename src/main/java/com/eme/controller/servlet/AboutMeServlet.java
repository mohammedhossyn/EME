package com.eme.controller.servlet;


import com.eme.controller.MeController;
import com.eme.model.entity.enums.TransactionStatus;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/aboutMe")
public class AboutMeServlet extends HttpServlet{

    @Inject
    private MeController meController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List me = (List) meController.findAll().get(TransactionStatus.Done);
        req.getSession().setAttribute("me", me.get(0));
        resp.sendRedirect("/main/aboutMe.jsp");
    }
}
