package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.dto.JoinRequest;
import com.ensharp.gayeongsignup.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/hello")
    public String getHello() {
        return "안녕!";
    }

    @PostMapping("/join")
    public String join(@RequestBody JoinRequest joinRequest) {
        String result = memberService.join(joinRequest);
        //result.equalsIgnoreCase("success") 대신
        if ("success".equalsIgnoreCase(result)) { //nullException발생 방지
            return "회원가입 success";
        } else {
            return "회원가입 fail";
        }
    }
}
/// @valid, @validated //유효성
/// @exceptionhandler, @controlleradvice 참고해서 추가하기
