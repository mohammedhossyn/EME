package com.eme.controller.servlet;


import com.eme.controller.MeController;
import com.eme.controller.ProjectController;
import com.eme.model.entity.enums.TransactionStatus;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/projects")
public class ProjectsServlet extends HttpServlet {

    @Inject
    private MeController meController;

    @Inject
    private ProjectController projectController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List me = (List) meController.findAll().get(TransactionStatus.Done);
        req.getSession().setAttribute("me", me.get(0));
        req.getSession().setAttribute("projects", projectController.findAll().get(TransactionStatus.Done));
        resp.sendRedirect("/main/projects.jsp");
    }
}
