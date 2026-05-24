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
        //dto에 태그된 메시지 그대로 가져와서 에러코드 반환
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        System.out.println("@Valid 검증 실패 잡음: " + e.getStatusCode().value()+", "+ e.getMessage());

        return ResponseEntity
                .status(e.getStatusCode().value())
                .body(errorMessage);
}

@ExceptionHandler({Exception.class})
protected ResponseEntity handleServerException(Exception ex) {
    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
}
}
