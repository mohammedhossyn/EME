package com.eme.controller.session;

import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.model.entity.*;
import com.eme.model.service.MeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Named
@SessionScoped
public class FacesSessionMap implements Serializable {

    @Inject
    private SessionManager sessionManager;

    @Inject
    private FacesContext facesContext;

    Map<String, Object> sessionMap;
    HttpSession httpSession;

    @Inject
    private MeService meService;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    public void login(String username) throws IOException {
        sessionMap = facesContext.getExternalContext().getSessionMap();
        httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        sessionManager.addHttpSession(httpSession);
        sessionMap.put("userLoggedIn", UserVO.builder().username(username).build());
        facesContext.getExternalContext().redirect("panel/me.xhtml");
    }

    public void logout() throws IOException {
        sessionMap = facesContext.getExternalContext().getSessionMap();
        httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        sessionManager.invalidateHttpSession(httpSession);
        sessionMap.remove("userLoggedIn");
        facesContext.getExternalContext().redirect("/login.xhtml");
    }

    public void setObjectOnSession(Object obj, String name){
        sessionMap = facesContext.getExternalContext().getSessionMap();
        sessionMap.put(name, obj);
    }

    public void setMessage(MessageVO message){
        sessionMap = facesContext.getExternalContext().getSessionMap();
        sessionMap.put("message", message);
    }

}
