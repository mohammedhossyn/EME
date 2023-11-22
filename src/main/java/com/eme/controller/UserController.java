package com.eme.controller;

import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.session.FacesSessionMap;
import com.eme.controller.validation.ObjectValidation;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.MessageVO;
import com.eme.model.entity.User;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.TransactionStatus;
import com.eme.model.service.UserService;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
@Named
public class UserController implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private ObjectValidation objectValidation;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    @Inject
    private FacesSessionMap facesSessionMap;

    private Map<TransactionStatus, Object> result = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();

    //-------INSERT------------------------------------------------------
    public Map<TransactionStatus, Object> save(
            String username,
            String password
    ) {
        result.clear();
        errors.clear();


        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(User.builder()
                .username(username)
                .password(password)
                .build());
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, userService.save(User.builder()
                        .username(username)
                        .password(password)
                        .build()));
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
            String username,
            String password
    ) {
        result.clear();
        errors.clear();


        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(User.builder()
                .status(status)
                .username(username)
                .password(password)
                .build());
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, userService.edit(User.builder()
                        .status(status)
                        .username(username)
                        .password(password)
                        .build()));
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

    //------LOG-IN-------------------------------------------------------
    public Boolean validateUser(User user) {
        try {
            User result = userService.userValidation(user);
            if (result != null) {
                facesSessionMap.login(user.getUsername());
                return true;
            } else {
                facesSessionMap.setMessage(MessageVO.builder().error("Username and password is Wrong!").build());
                return false;
            }
        } catch (Exception e) {
            exceptionWrapper.getMessage(e);
            return false;
        }
    }

}