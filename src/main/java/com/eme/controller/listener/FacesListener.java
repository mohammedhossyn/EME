//package com.eme.controller.listener;
//
//import com.eme.controller.exceptions.ExceptionWrapper;
//import com.eme.model.entity.Me;
//import com.eme.model.service.MeService;
//import jakarta.faces.context.FacesContext;
//import jakarta.inject.Inject;
//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;
//
//import java.util.Map;
//
//@WebListener
//public class FacesListener implements ServletContextListener {
//
//    Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//
//    @Inject
//    private MeService meService;
//
//    @Inject
//    private ExceptionWrapper exceptionWrapper;
//
//    public void contextInitialized(ServletContextEvent event) {
//        try {
//            Me me = meService.findById(1L);
//            sessionMap.put("editMe", me);
//        } catch (Exception e) {
//            exceptionWrapper.getMessage(e);
//        }
//    }
//
//}
