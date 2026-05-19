package com.ensharp.gayeongsignup.service;

import com.ensharp.gayeongsignup.dto.MemberDTO;
import com.ensharp.gayeongsignup.entity.Member;

public interface MemberService {

    String join(MemberDTO memberDTO);

    //(String email, String password, String username,
                //String streetAddress, String detailAddress);
}
