package com.eme.controller;

import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.validation.ObjectValidation;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.Person;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.TransactionStatus;
import com.eme.model.service.PersonService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.HashMap;
import java.util.Map;

@RequestScoped
@Named
public class PersonController {

    @Inject
    private Person person;

    @Inject
    private PersonService personService;

    @Inject
    private ObjectValidation objectValidation;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    private Map<TransactionStatus, Object> result;
    private Map<String, String> errors;

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
            Role role
    ) {
        result.clear();
        errors.clear();

        //  ---------CREATE-OBJECT-----------------
        person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .github(github)
                .telegram(telegram)
                .linkedin(linkedin)
                .instagram(instagram)
                .role(role)
                .build();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(person);
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, personService.save(person));
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
            Long id,
            String firstName,
            String lastName,
            String phone,
            String email,
            String github,
            String telegram,
            String linkedin,
            String instagram,
            Role role
    ) {
        result.clear();
        errors.clear();

        //  ---------CREATE-OBJECT-----------------
        person = Person.builder()
                .status(status)
                .versionId(versionId)
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .github(github)
                .telegram(telegram)
                .linkedin(linkedin)
                .instagram(instagram)
                .role(role)
                .build();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(person);
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, personService.save(person));
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
            result.put(TransactionStatus.Done, personService.physicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    public Map<TransactionStatus, Object> logicalRemove(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, personService.physicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-ALL---------------------------------------------------
    public Map<TransactionStatus, Object> findAll() {
        result.clear();
        try {
            result.put(TransactionStatus.Done, personService.findAll());
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-BY-ID-------------------------------------------------
    public Map<TransactionStatus, Object> findById(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, personService.findById(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

}