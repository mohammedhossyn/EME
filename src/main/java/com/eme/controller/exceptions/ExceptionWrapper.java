package com.eme.controller.exceptions;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;

@RequestScoped
@Named
public class ExceptionWrapper {

    //    ---------------------------------------------------------------------------------
    public String getMessage(Exception exception) {
        if (exception instanceof SQLException) {
            return "SQL Message: Check Your SQL CODES\n" + exception.getMessage();
        }else if (exception instanceof ClassNotFoundException) {
            return "CNF Message: You Need Class\n" + exception.getMessage();
        }else if (exception instanceof IOException) {
                return "IO Message: Input/Output Error\n" + exception.getMessage();
        }else if (exception instanceof ServletException) {
            return "Servlet Message: WebServlet Error\n" + exception.getMessage();
        }else {
        return "Not Added THIS Error Yet\n" + exception.getMessage();
        }
    }
}
