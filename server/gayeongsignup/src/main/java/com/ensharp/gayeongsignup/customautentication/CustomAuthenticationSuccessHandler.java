package com.ensharp.gayeongsignup.customautentication;

import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import com.ensharp.gayeongsignup.member.LoginResponseDto;
import com.ensharp.gayeongsignup.member.Member;
import com.ensharp.gayeongsignup.member.MemberRepository;
import com.ensharp.gayeongsignup.member.SignupRequestDto;
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
    private final MemberRepository memberRepository;

    public CustomAuthenticationSuccessHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //인증에 성공하면 Authentication 객체 안에 진짜 사용자 정보(Principal)가 담겨서 돌아옴.
        String email = authentication.getName();
        Member user = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        //Member user = (Member) authentication.getPrincipal();
        System.out.println("로그인 성공 핸들러 동작");

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String role = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("ROLE_USER");
        LoginResponseDto loginResponseDto = new LoginResponseDto(
                user.getUsername(),
                user.getEmail(),
                role,
                user.getStreetAddress(),
                user.getDetailAddress()
        );
        objectMapper.writeValue(response.getWriter(), loginResponseDto);

    }
}
