package com.ensharp.gayeongsignup.customautentication;

import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//인증 실패 핸들러. 에러에 따라 에러 메시지 전달
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
//        String errMsg = "이메일 또는 비밀번호가 유효하지 않습니다.";
//        String errorMessage = "로그인에 실패했습니다.";
//        if (exception instanceof BadCredentialsException) {
//            errorMessage = "비밀번호가 일치하지 않습니다.";
//        } else if (exception instanceof UsernameNotFoundException) {
//            errorMessage = "존재하지 않는 계정입니다.";
//        }
        System.out.println("로그인 실패 핸들러 동작");
        //예외메시지 전달

        response.setStatus(ErrorCode.USER_NOT_FOUND.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Map<String,String> data = new HashMap<>();
        data.put("error",ErrorCode.USER_NOT_FOUND.getMessage());

        objectMapper.writeValue(response.getWriter(), data);

    }
}
