package com.ensharp.gayeongsignup.customautentication;

import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import com.ensharp.gayeongsignup.member.Member;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//입력받은 정보 확인 인증 성공 시, 토큰 생성
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder; //비밀번호 인코딩 검증

    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder){
        this.userDetailsService=customUserDetailsService;
        this. passwordEncoder=passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email= authentication.getName(); //name이지만 email이라고 생각
        String password = authentication.getCredentials().toString();
        //사용자 정보를 조회
        Member user =userDetailsService.loadUserByUsername(email);
        //비밀번호 일치 판단
        if(!passwordEncoder.matches(password,user.getPassword())){
            //throw new CustomException(ErrorCode.INVALID_PASSWORD);
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        //인증 성공 시 권한 포함한 토큰 반환
        //(principal-user객체 ,credentials-비밀번호는 보안위해 null처리, authorities-권한 목록)
        return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //CustomAuthenticationToken 타입의 인증을 처리
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
