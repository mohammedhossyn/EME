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
    private Book book;

    @Inject
    private BookService bookService;

    @Inject
    private ObjectValidation objectValidation;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    private Map<TransactionStatus, Object> result;
    private Map<String, String> errors;

    //-------INSERT------------------------------------------------------
    public Map<TransactionStatus, Object> save(
            String title,
            String name,
            String writer,
            String explanation,
            Attachment attachment
    ) {
        result.clear();
        errors.clear();

        //  ---------CREATE-OBJECT-----------------
        book = Book.builder()
                .title(title)
                .name(name)
                .writer(writer)
                .explanation(explanation)
                .attachment(attachment)
                .build();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(book);
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, bookService.save(book));
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
            String title,
            String name,
            String writer,
            String explanation,
            Attachment attachment
    ) {
        result.clear();
        errors.clear();

        //  ---------CREATE-OBJECT-----------------
        book = Book.builder()
                .status(status)
                .versionId(versionId)
                .id(id)
                .title(title)
                .name(name)
                .writer(writer)
                .explanation(explanation)
                .attachment(attachment)
                .build();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(book);
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, bookService.save(book));
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
            result.put(TransactionStatus.Done, bookService.physicalRemove(id));
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

}