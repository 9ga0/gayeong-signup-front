package com.ensharp.gayeongsignup.member;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    public SignupRequestDto join(SignupRequestDto signupRequestDto);


    String login(String email, String password, HttpSession session);

    //비밀번호 변경 시에 성공/실패 점검 및 반환
    @Transactional
    String changePassword(String email, String newPassword);

    SignupRequestDto getUserInfo(String email);

    List<SignupRequestDto> getAllUserInfo();

    //비밀번호 변경 시에 성공/실패 점검 및 반환
    //@Transactional
    ///하다가 오류나도 되돌릴수있게 해줌. db조회에도 필요할수있다.  String changePassword(String email, String newPassword);

    //(String email, String password, String username,
                //String streetAddress, String detailAddress);


}
