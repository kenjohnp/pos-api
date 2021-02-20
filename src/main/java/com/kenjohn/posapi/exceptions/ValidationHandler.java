package com.kenjohn.posapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@ControllerAdvice
public class ValidationHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();


        List<FieldError> fieldErrors = result.getFieldErrors();

//        String errorMessage = fieldErrors.get(0).getDefaultMessage();
        ApiException apiException = new ApiException(fieldErrors.get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));


        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
//        return ResponseEntity.badRequest().body(errorMessage);
    }
}
