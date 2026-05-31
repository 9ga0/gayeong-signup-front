package com.ensharp.gayeongsignup.customautentication;

import com.ensharp.gayeongsignup.member.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//인증 성공 핸들러. 응답 값 반환
//Rest API이기 떄문에 redirect 처리 하지 않고, response에 값 넣어줌.
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //인증에 성공하면 Authentication 객체 안에 진짜 사용자 정보(Principal)가 담겨서 돌아옴.
        Member user = (Member) authentication.getPrincipal();
        String userRole = user.getUserRole();
        String targetUrl = "/my-page"; //기본 url

        System.out.println("로그인 성공 핸들러 동작");

        Map<String, String> data = new HashMap<>();
        if (user.getEmail().equals("koo050803@gmail.com")){ //관리자 이메일 하드코딩
            /// 추후 관리자 repository 파야 할듯
            data.put("username", user.getUsername());
            data.put("role", user.getUserRole()); 
            targetUrl="/admin-page"; //admin일떄 이동할 프론트url
            //내정보 페이지로 이동
        }else{ //유저 계정으로 로그인했을때
            data.put("username", user.getUsername());
            data.put("role", user.getUserRole());
            targetUrl="/admin-page"; //admin일떄 이동할 프론트url
            //관리자 페이지로 이동한다.
        }
        data.put("redirectUrl", targetUrl);
        //로그인 성공시 유저이름을 http응답 바디에 넣음
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(),data);
    }
}
