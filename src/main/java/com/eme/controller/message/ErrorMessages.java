package com.eme.controller.message;

import com.eme.model.entity.enums.Error;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestScoped
@Named
public class ErrorMessages {

    public String getErrorMessage(String error) {
        String message = "";
        switch (error) {
            case "Role_Error":
                message = "Role is not chose!";
                break;
            case "Status_Error":
                message = "Status is not chose!";
                break;
            case "VersionId_Error":
                message = "Someone is editing this record!";
                break;
            case "UserUsername_Error":
                message = "Username must have";
                break;
            case "UserPassword_Error":
                message = "Password must have ";
                break;
            case "FirstName_Error":
                message = "Firstname must be only en or fa alphabet & at least 25 characters!";
                break;
            case "LastName_Error":
                message = "Lastname must be only en or fa alphabet & at least 25 characters!";
                break;
            case "Phone_Error":
                message = "Phone must be 11 number(Iran)!";
                break;
            case "Email_Error":
                message = "Email is wrong!";
                break;
            case "GitHub_Error":
                message = "Github  is wrong!";
                break;
            case "Telegram_Error":
                message = "Telegram is wrong!";
                break;
            case "Linkedin_Error":
                message = "Linkedin is wrong!";
                break;
            case "Instagram_Error":
                message = "Instagram is wrong!";
                break;
            case "MyBirthday_Error":
                message = "Birthday is wrong!";
                break;
            case "MyJob_Error":
                message = "Job is wrong!";
                break;
            case "ShortAboutMe_Error":
                message = "Something wrong with Short About Me!";
                break;
            case "CompleteAboutMe_Error":
                message = "Something wrong with Complete About Me!";
                break;
            case "BookTitle_Error":
                message = "Book Title must be character & not empty, at least 60 characters!";
                break;
            case "BookName_Error":
                message = "Book Name must be character & not empty, at least 60 characters!";
                break;
            case "BookWriter_Error":
                message = "Book Writer must be character & not empty, at least 60 characters!";
                break;
            case "BookExplanation_Error":
                message = "Book Explanation must be character & not empty, at least 2000 characters!";
                break;
            case "BookGoogleLink_Error":
                message = "Book Google Link is wrong!";
                break;
            case "ProjectName_Error":
                message = "Project Name must be character & not empty, at least 30 characters!";
                break;
            case "ProjectProgress_Error":
                message = "Project Progress must be character & not empty, at least 50 characters!";
                break;
            case "ProjectTitle_Error":
                message = "Project Title must be character & not empty, at least 30 characters!";
                break;
            case "ProjectExplanation_Error":
                message = "Project Explanation must be character & not empty, at least 1000 characters!";
                break;
        }
        return message;
    }

    public List<String> getErrorsMessage(Map<String, String> errors){
        return new ArrayList<>(errors.values());
    }
}
