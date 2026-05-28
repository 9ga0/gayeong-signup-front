package com.ensharp.gayeongsignup.member;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    public SignupRequestDto join(SignupRequestDto signupRequestDto);

    public String login(String email, String password);

    //(String email, String password, String username,
                //String streetAddress, String detailAddress);


}
