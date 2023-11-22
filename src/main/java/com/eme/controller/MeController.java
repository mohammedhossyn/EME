package com.eme.controller;

import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.validation.ObjectValidation;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.Me;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.TransactionStatus;
import com.eme.model.service.MeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.HashMap;
import java.util.Map;

@RequestScoped
@Named
public class MeController {

    @Inject
    private MeService meService;

    @Inject
    private ObjectValidation objectValidation;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    private Map<TransactionStatus, Object> result = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();

    //-------INSERT------------------------------------------------------
    public Map<TransactionStatus, Object> save(
            String firstName,
            String lastName,
            String phone,
            String email,
            String github,
            String telegram,
            String linkedin,
            String instagram,
            Role role,
            String birthday,
            String jobPosition,
            Attachment attachment,
            String shortAboutMeContent,
            String completeAboutMeContent
    ) {
        result.clear();
        errors.clear();
        Me me = Me.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .github(github)
                .telegram(telegram)
                .linkedin(linkedin)
                .instagram(instagram)
                .role(role)
                .birthday(birthday)
                .jobPosition(jobPosition)
                .attachment(attachment)
                .shortAboutMeContent(shortAboutMeContent)
                .completeAboutMeContent(completeAboutMeContent)
                .build();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(me);
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, meService.save(me));
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
            Long id,
            String firstName,
            String lastName,
            String phone,
            String email,
            String github,
            String telegram,
            String linkedin,
            String instagram,
            String birthday,
            String jobPosition,
            Attachment attachment,
            String shortAboutMeContent,
            String completeAboutMeContent
    ) {
        result.clear();
        errors.clear();
        Me me = Me.builder()
                .status(Status.Active)
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .github(github)
                .telegram(telegram)
                .linkedin(linkedin)
                .instagram(instagram)
                .role(Role.ME)
                .birthday(birthday)
                .jobPosition(jobPosition)
                .attachment(attachment)
                .shortAboutMeContent(shortAboutMeContent)
                .completeAboutMeContent(completeAboutMeContent)
                .build();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(me);
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, meService.edit(me));
            } else {
                result.put(TransactionStatus.Error, errors);
            }
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //-------PHYSICAL-REMOVE---------------------------------------------
    public Map<TransactionStatus, Object> physicalRemove(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, meService.physicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    public Map<TransactionStatus, Object> logicalRemove(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, meService.physicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-ALL---------------------------------------------------
    public Map<TransactionStatus, Object> findAll() {
        result.clear();
        try {
            result.put(TransactionStatus.Done, meService.findAll());
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-BY-ID-------------------------------------------------
    public Map<TransactionStatus, Object> findById(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, meService.findById(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

}