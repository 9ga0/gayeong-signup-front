package com.ensharp.gayeongsignup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    protected String handleCustomException(CustomException ex) {
        return ex.getErrorCode().getMessage();
//        return new ResponseEntity(new ErrorDto(ex.getErrorCode().getStatus(),
//                ex.getErrorCode().getMessage()),
//                HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }
    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleServerException(Exception ex){
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
