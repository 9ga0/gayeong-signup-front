package com.ensharp.gayeongsignup.customautentication;

import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import com.ensharp.gayeongsignup.member.Member;
import com.ensharp.gayeongsignup.member.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// UserDetails는 사용자 1명의 정보 (권한,비밀번호,계정상태 등)을 담음
// USerDetailsService는 로그인 시 DB에서 유저를 찾는 역할을 함.

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Member member= memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member.getEmail().equals("koo050803@gmail.com")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(member.getEmail(), member.getPassword(),authorities);
    }

}
