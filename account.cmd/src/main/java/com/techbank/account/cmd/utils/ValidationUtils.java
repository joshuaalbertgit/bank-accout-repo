package com.techbank.account.cmd.utils;

import com.techbank.account.common.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationUtils {
    public static ResponseEntity<BaseResponse> handleValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Invalid input data: ");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField())
                        .append(" ")
                        .append(error.getDefaultMessage())
                        .append(", ");
            }
            return new ResponseEntity<>(new BaseResponse(errorMessage.toString()), HttpStatus.BAD_REQUEST);
        }
        return null; // No validation errors, return null
    }
}