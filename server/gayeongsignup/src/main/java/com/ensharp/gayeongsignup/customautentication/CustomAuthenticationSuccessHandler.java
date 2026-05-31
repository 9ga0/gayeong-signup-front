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
        System.out.println("로그인 성공 핸들러 동작");

        Map<String, Object> data = new HashMap<>();
        if (user.getEmail().equals("koo050803@gmail.com")) { //관리자 이메일 하드코딩
            /// 추후 관리자 repository 파야 할듯
            data.put("role", "ROLE_ADMIN");
        }else {
            data.put("role", "ROLE_USER");
        }

            //관리자일때, 관리자 페이지로 이동한
            //유저 계정으로 로그인했을때, //내정보 페이지로 이동
        data.put("username", user.getUsername());
        data.put("detailAddress",user.getDetailAddress());
        data.put("streetAddress",user.getStreetAddress());

        //로그인 성공시 유저이름을 http응답 바디에 넣음
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), data);    }
}
