package com.eme.controller;

import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.validation.ObjectValidation;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.enums.FormatType;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.TransactionStatus;
import com.eme.model.service.AttachmentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.HashMap;
import java.util.Map;

@RequestScoped
@Named
public class AttachmentController {

    @Inject
    private AttachmentService attachmentService;

    @Inject
    private ObjectValidation objectValidation;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    private Map<TransactionStatus, Object> result = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();

    //-------INSERT------------------------------------------------------
    public Map<TransactionStatus, Object> save(
            String name,
            String path,
            FormatType formatType
    ) {
        result.clear();
        errors.clear();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(Attachment.builder()
                .name(name)
                .path(path)
                .formatType(formatType)
                .build());
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, attachmentService.save(Attachment.builder()
                        .name(name)
                        .path(path)
                        .formatType(formatType)
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
            String name,
            String path,
            FormatType formatType
    ) {
        result.clear();
        errors.clear();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation(Attachment.builder()
                .status(status)
                .id(id)
                .name(name)
                .path(path)
                .formatType(formatType)
                .build());
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, attachmentService.edit(Attachment.builder()
                        .status(status)
                        .id(id)
                        .name(name)
                        .path(path)
                        .formatType(formatType)
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
            result.put(TransactionStatus.Done, attachmentService.physicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    public Map<TransactionStatus, Object> logicalRemove(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, attachmentService.physicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-ALL---------------------------------------------------
    public Map<TransactionStatus, Object> findAll() {
        result.clear();
        try {
            result.put(TransactionStatus.Done, attachmentService.findAll());
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-BY-ID-------------------------------------------------
    public Map<TransactionStatus, Object> findById(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, attachmentService.findById(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

}