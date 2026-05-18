package com.ensharp.gayeongsignup.service;

import com.ensharp.gayeongsignup.dto.JoinRequest;

public interface MemberService {

    String join(JoinRequest joinRequest);
            //(String email, String password, String username,
                //String streetAddress, String detailAddress);
}
