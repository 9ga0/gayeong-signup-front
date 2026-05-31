package com.ensharp.gayeongsignup.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Component
public class loginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

//    @Override
//    public void onAuthenticationFailure (HttpServletRequest request, HttpServletResponse response,
//                                        AuthenticationException exception) throws IOException, ServletException{
//        String errorMessage;
//        String inputUserId = (String) request.getParameter("userId");
//        if (exception instanceof BadCredentialsException){
//            errorMessage = "아이디 또는 비밀번호가 맞지 않습니다.";
//        }
//
//    }
}
