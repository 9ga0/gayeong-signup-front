package com.ensharp.gayeongsignup.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class loginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON 변환기
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        //인증 정보 저장? 세션에도 반영

        //인증된 사용자 정보
        Member user = (Member) authentication.getPrincipal();
//        String sAuth = user.getUserRole();
//        String targetUrl = "/my-page"; //기본 url
//        if(sAuth.equals("ROLE_USER")){
//            targetUrl = "/my-page"; //user일떄 이동할 프론트url
//            //내정보 페이지로 이동한다.
//        } else if(sAuth.equals("ROLE_ADMIN")){
//            targetUrl="/admin-page"; //admin일떄 이동할 프론트url
//            //관리자 페이지로 이동한다.
//        }
        //response 데이터를 대입해 프론트에 전달하는 방식으로 변경
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), new LoginDto(user.getEmail(),user.getPassword()));
    }
}
