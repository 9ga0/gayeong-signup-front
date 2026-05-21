package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.MailSendService;
import com.ensharp.gayeongsignup.emailsend.MailTxtSendDto;
import com.ensharp.gayeongsignup.signup.MemberDTO;
import com.ensharp.gayeongsignup.signup.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
@CrossOrigin(origins = "")
public class DBController {
    private final MemberService memberService;
    private final MailSendService mailSendService;

    public DBController(MemberService memberService, MailSendService mailSendService) {
        this.memberService = memberService;
        this.mailSendService = mailSendService;
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

    //회원가입
    @PostMapping("/join")
    public String join(@RequestBody @Valid MemberDTO memberDTO) {
        System.out.println("회원가입 요청이 들어옴 : "+memberDTO.email());
        return memberService.join(memberDTO);
    }

    //이메일인증
    @PostMapping("/emailsend")
    public String sendMessage(@RequestBody @Valid MailTxtSendDto mailTxtSendDto) throws UnsupportedEncodingException {
        System.out.println("이메일 인증번호 전송 요청이 들어옴 : "+mailTxtSendDto.emailAddr());
        return mailSendService.sendTxtEmail(mailTxtSendDto);
    }

    //

}
/// @exceptionhandler, @controlleradvice 참고해서 추가하기
