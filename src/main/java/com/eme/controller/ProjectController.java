package com.eme.controller;

import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.validation.ObjectValidation;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.Project;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.TransactionStatus;
import com.eme.model.service.ProjectService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.HashMap;
import java.util.Map;

@RequestScoped
@Named
public class ProjectController {

    @Inject
    private ProjectService projectService;

    @Inject
    private ObjectValidation objectValidation;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    private Map<TransactionStatus, Object> result = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();

    //-------INSERT------------------------------------------------------
    public Map<TransactionStatus, Object> save(
            String name,
            String progress,
            String title,
            String explanation,
            Attachment attachment
    ) {
        result.clear();
        errors.clear();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation( Project.builder()
                .name(name)
                .progress(progress)
                .title(title)
                .explanation(explanation)
                .attachment(attachment)
                .build());
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, projectService.save( Project.builder()
                        .name(name)
                        .progress(progress)
                        .title(title)
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
            String name,
            String progress,
            String title,
            String explanation,
            Attachment attachment
    ) {
        result.clear();
        errors.clear();

        //  ---------VALIDATING-DATA---------------
        errors = objectValidation.doValidation( Project.builder()
                .status(status)
                .id(id)
                .name(name)
                .progress(progress)
                .title(title)
                .explanation(explanation)
                .attachment(attachment)
                .build());
        try {
            if (errors.isEmpty()) {
                result.put(TransactionStatus.Done, projectService.edit( Project.builder()
                        .status(status)
                        .id(id)
                        .name(name)
                        .progress(progress)
                        .title(title)
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
            result.put(TransactionStatus.Done, projectService.physicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    public Map<TransactionStatus, Object> logicalRemove(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, projectService.physicalRemove(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-ALL---------------------------------------------------
    public Map<TransactionStatus, Object> findAll() {
        result.clear();
        try {
            result.put(TransactionStatus.Done, projectService.findAll());
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

    //------SELECT-BY-ID-------------------------------------------------
    public Map<TransactionStatus, Object> findById(Long id) {
        result.clear();
        try {
            result.put(TransactionStatus.Done, projectService.findById(id));
        } catch (Exception e) {
            result.put(TransactionStatus.Exception, exceptionWrapper.getMessage(e));
        }
        return result;
    }

}