package com.ensharp.gayeongsignup.signup;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    default String join(SignupRequestDto signupRequestDto){
        return "fail";
    }
    String login(String email, String password);

    //(String email, String password, String username,
                //String streetAddress, String detailAddress);


}
