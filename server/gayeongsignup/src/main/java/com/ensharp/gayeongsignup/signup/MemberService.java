package com.ensharp.gayeongsignup.signup;

public interface MemberService {

    default String join(MemberDTO memberDTO){
        return "fail";
    }

    //(String email, String password, String username,
                //String streetAddress, String detailAddress);
}
