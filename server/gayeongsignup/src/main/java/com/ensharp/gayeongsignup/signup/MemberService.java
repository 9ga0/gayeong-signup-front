package com.ensharp.gayeongsignup.signup;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    default String join(MemberDTO memberDTO){
        return "fail";
    }

    //(String email, String password, String username,
                //String streetAddress, String detailAddress);


}
