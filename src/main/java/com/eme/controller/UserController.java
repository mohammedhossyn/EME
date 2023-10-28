package com.eme.controller;

import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.validation.ObjectValidation;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.User;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.TransactionStatus;
import com.eme.model.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.HashMap;
import java.util.Map;

@RequestScoped
@Named
public class UserController {

    @Inject
    private User user;

    @Inject
    private UserService userService;

    @Inject
    private ObjectValidation objectValidation;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    private Map<TransactionStatus, Object> result;
    private Map<String, String> errors;

    //-------INSERT------------------------------------------------------
    public Map<TransactionStatus, Object> save(
            String username,
            String password
    ) {
        result.clear();
        errors.clear();

        //  ---------CREATE-OBJECT-----------------
        user = User.builder()
                .username(username)
                .password(password)
                .build();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(user);
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, userService.save(user));
            } else {
                result.put(TransactionStatus.Error, errors);
            }
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //-------UPDATE------------------------------------------------------
    public Map<TransactionStatus, Object> edit(
            Status status,
            Long versionId,
            String username,
            String password
    ) {
        result.clear();
        errors.clear();

        //  ---------CREATE-OBJECT-----------------
        user = User.builder()
                .status(status)
                .versionId(versionId)
                .username(username)
                .password(password)
                .build();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(user);
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, userService.save(user));
            } else {
                result.put(TransactionStatus.Error, errors);
            }
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //-------PHYSICAL-REMOVE---------------------------------------------
    public Map<TransactionStatus, Object> physicalRemove(String username) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, userService.physicalRemove(username));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    public Map<TransactionStatus, Object> logicalRemove(String username) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, userService.physicalRemove(username));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-ALL---------------------------------------------------
    public Map<TransactionStatus, Object> findAll() {
        result.clear();
        try {
            result.put(TransactionStatus.Done, userService.findAll());
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-BY-ID-------------------------------------------------
    public Map<TransactionStatus, Object> findById(String username) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, userService.findById(username));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

}