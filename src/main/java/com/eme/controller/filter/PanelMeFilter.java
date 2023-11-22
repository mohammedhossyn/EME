package com.eme.controller.filter;

import com.eme.controller.session.SessionManager;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/panel/me.xhtml")
public class PanelMeFilter implements Filter {

    @Inject
    private SessionManager sessionManager;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) req).getSession();
        if (sessionManager.validateHttpSession(httpSession)){
            chain.doFilter(req, resp);
        }else {
            ((HttpServletResponse) resp).sendRedirect("/login.xhtml");
        }


    }
}
