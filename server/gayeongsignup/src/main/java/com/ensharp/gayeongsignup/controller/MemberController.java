package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.dto.MemberDTO;
import com.ensharp.gayeongsignup.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
@CrossOrigin(origins = "")
public class MemberController {
    //    private final MemberRepository repository;
    private final MemberService memberService;

    //    //@RequiredArgsConstructor 역할
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/default")
    public String postMethod() {
        return "안녕하세요!";
    }

    @PostMapping("/member")
    public String postMember(@RequestBody @Valid Map<String, String> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    //위랑 같은 결과?
    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody @Valid MemberDTO memberDTO) {
        return memberDTO.toString();
    }

    @PostMapping("/join")
    public String join(@RequestBody MemberDTO memberDTO) {
        String result = memberService.join(memberDTO);
        //result.equalsIgnoreCase("success") 대신
        if ("success".equalsIgnoreCase(result)) { //nullException발생 방지
            return "회원가입 success";
        } else {
            return "회원가입 fail";
        }
    }

}
/// @exceptionhandler, @controlleradvice 참고해서 추가하기
