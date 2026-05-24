package com.ensharp.gayeongsignup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity handleCustomException(CustomException e) {
//        return new ResponseEntity(new ErrorDto(ex.getErrorCode().getStatus(),
//                ex.getErrorCode().getMessage()),
//                HttpStatus.valueOf(ex.getErrorCode().getStatus()));
        System.out.println("CustomException 잡음: " + e.getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().getMessage());
    }
    //Valid 검증 실패시 발생하는 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException e) {
        System.out.println("@Valid 검증 실패 잡음: " + e.getMessage());
        return ResponseEntity
                .status(ErrorCode.INVALID_EMAIL.getStatus())
                .body(ErrorCode.INVALID_EMAIL.getMessage());
    }
    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleServerException(Exception ex){
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
