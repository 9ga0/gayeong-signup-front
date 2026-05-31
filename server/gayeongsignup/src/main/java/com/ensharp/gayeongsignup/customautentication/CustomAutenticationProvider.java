package com.ensharp.gayeongsignup.customautentication;

import com.ensharp.gayeongsignup.member.Member;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//입력받은 정보 확인 인증 성공 시, 토큰 생성
@Component
public class CustomAutenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder; //비밀번호 인코딩 검증

    public CustomAutenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder){
        this.userDetailsService=customUserDetailsService;
        this. passwordEncoder=passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email= authentication.getName(); //name이지만 email이라고 생각
        String password = authentication.getCredentials().toString();
        //사용자 정보를 조회
        Member user =userDetailsService.loadUserByUsername(email);
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("유효하지 않은 이메일 또는 패스워드.");
        }
        //인증 성공 시, authentication객체 리턴
        UsernamePasswordAuthenticationToken authenticatedUser ;
        authenticatedUser = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        return authenticatedUser;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //UsernamePasswordAuthenticationToken 타입의 인증을 지원
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
