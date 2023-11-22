package com.eme.controller.session;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import com.eme.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Named
@SessionScoped
public class SessionManager implements Serializable {
    public static Map<HttpSession, Session> sessionMap = new HashMap<>();


    public static void addHttpSession(HttpSession httpSession) {
        sessionMap.put(httpSession, null);
    }


    public static void addWebSocketSession(HttpSession httpSession, Session Session) {
        sessionMap.put(httpSession, Session);
    }

    public static boolean validateHttpSession(HttpSession httpSession) {
        return sessionMap.containsKey(httpSession);
    }


    public static boolean validateWebSocketSession(Session session) {
        return sessionMap.containsValue(session);
    }


    public static Session getWebSocketSession(HttpSession httpSession) {
        return sessionMap.get(httpSession);
    }


    public static Set<HttpSession> getHttpSessions() {
        return sessionMap.keySet();
    }


    public static Collection<Session> getWebSocketSessions() {
        return sessionMap.values();
    }


    public static boolean invalidateHttpSession(HttpSession httpSession) {
        if (sessionMap.containsKey(httpSession)) {
            sessionMap.remove(httpSession);
            return true;
        }
        return false;
    }


    public static List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (HttpSession httpSession : sessionMap.keySet()) {
            userList.add((User) httpSession.getAttribute("user"));
        }
        return userList;
    }


    public static HttpSession findHttpSessionByUserName(String userName) {
        for (HttpSession httpSession : sessionMap.keySet()) {
            if (((User) httpSession.getAttribute("user")).getUsername().equals(userName)) {
                return httpSession;
            }
        }
        return null;
    }
}
