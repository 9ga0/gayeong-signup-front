package com.ensharp.gayeongsignup.customautentication;

import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import com.ensharp.gayeongsignup.member.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private ObjectMapper objectMapper = new ObjectMapper();

    //url과 일치할 경우 해당 필터가 동작.
    public CustomAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl); // "/api/login" 를 넘겨받고 있음
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginDto loginDto = objectMapper.readValue(request.getReader(), LoginDto.class);

//        Member foundMember = memberRepository.findByEmailAndPassword(loginDto.email(), loginDto.password())
//                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_PASSWORD));

        // 아이디, 비밀번호값이 없으면 커스텀 에러던지기
        if(!StringUtils.hasLength(loginDto.email())) {
            throw new CustomException(ErrorCode.INVALID_EMAIL);
        }else if(!StringUtils.hasLength(loginDto.password())){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        //인증 되지 않은 토큰 생성
        CustomAuthenticationToken token = new CustomAuthenticationToken(
                loginDto.email(), loginDto.password()
        );

        //manager에게 토큰 넘겨주어서 인증처리 맡김
        Authentication authentication = getAuthenticationManager().authenticate(token);
        return authentication;
    }
}
