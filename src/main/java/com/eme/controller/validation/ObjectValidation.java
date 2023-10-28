package com.eme.controller.validation;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.HashMap;
import java.util.Map;

@RequestScoped
@Named
public class ObjectValidation {
    //  ---------CREATE-VALIDATION-FACTORY-----------------------------------------------
    Validator validator = jakarta.validation.Validation.buildDefaultValidatorFactory().getValidator();

    //  ---------CREATE-MAP-FOR-ERRORS---------------------------------------------------
    Map<String,String> errors = new HashMap<>();

    //  ---------VALIDATING-OBJECT-FIELDS-INPUT------------------------------------------
    public Map<String,String> doValidation(Object object){
        for (ConstraintViolation<Object> violation : validator.validate(object)) {
            errors.put(violation.getPropertyPath().toString(),violation.getMessage());
        }
        return errors;
    }
}
