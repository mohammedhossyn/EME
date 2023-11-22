package com.eme.controller;

import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.validation.ObjectValidation;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.Book;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.TransactionStatus;
import com.eme.model.service.BookService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.HashMap;
import java.util.Map;

@RequestScoped
@Named
public class BookController {

    @Inject
    private BookService bookService;

    @Inject
    private ObjectValidation objectValidation;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    private Map<TransactionStatus, Object> result = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();

    //-------INSERT------------------------------------------------------
    public Map<TransactionStatus, Object> save(
            String title,
            String name,
            String writer,
            String explanation,
            String googleLink,
            Attachment attachment
    ) {
        result.clear();
        errors.clear();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(Book.builder()
                .title(title)
                .name(name)
                .writer(writer)
                .explanation(explanation)
                .googleLink(googleLink)
                .attachment(attachment)
                .build());
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, bookService.save(Book.builder()
                        .title(title)
                        .name(name)
                        .writer(writer)
                        .explanation(explanation)
                        .attachment(attachment)
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
            Long id,
            String title,
            String name,
            String writer,
            String explanation,
            String googleLink,
            Attachment attachment
    ) {
        result.clear();
        errors.clear();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(Book.builder()
                .status(status)
                .id(id)
                .title(title)
                .name(name)
                .writer(writer)
                .explanation(explanation)
                .googleLink(googleLink)
                .attachment(attachment)
                .build());
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, bookService.edit(Book.builder()
                        .status(status)
                        .id(id)
                        .title(title)
                        .name(name)
                        .writer(writer)
                        .explanation(explanation)
                        .attachment(attachment)
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
    public Map<TransactionStatus, Object> physicalRemove(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, bookService.physicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    public Map<TransactionStatus, Object> logicalRemove(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, bookService.logicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-ALL---------------------------------------------------
    public Map<TransactionStatus, Object> findAll() {
        result.clear();
        try {
            result.put(TransactionStatus.Done, bookService.findAll());
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-BY-ID-------------------------------------------------
    public Map<TransactionStatus, Object> findById(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, bookService.findById(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-ALL-ACTIVE---------------------------------------------
    public Map<TransactionStatus, Object> findAllActive() {
        result.clear();
        try {
            result.put(TransactionStatus.Done, bookService.findAllActive());
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

}