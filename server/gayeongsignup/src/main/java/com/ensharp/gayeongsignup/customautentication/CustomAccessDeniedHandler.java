package com.ensharp.gayeongsignup.customautentication;

import com.ensharp.gayeongsignup.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

//인가 - 요청 거부 당했을 경우 DeniedHandler
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException {

        response.sendRedirect("/unauthorized"); //
        response.sendError(ErrorCode.UNAUTHORIZED.getStatus(), ErrorCode.UNAUTHORIZED.getMessage());

    }
}
