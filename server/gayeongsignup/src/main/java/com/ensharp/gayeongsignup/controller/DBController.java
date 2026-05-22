package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.MailSendService;
import com.ensharp.gayeongsignup.emailsend.MailTxtSendDto;
import com.ensharp.gayeongsignup.signup.MemberServiceimpl;
import com.ensharp.gayeongsignup.signup.SignupRequestDto;
import com.ensharp.gayeongsignup.signup.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
@CrossOrigin(origins = "")
public class DBController {
    private final MemberServiceimpl memberServiceimpl;
    private final MailSendService mailSendService;

    public DBController(MemberServiceimpl memberServiceimpl, MailSendService mailSendService) {
        this.memberServiceimpl = memberServiceimpl;
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
    public String postMemberDto(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return signupRequestDto.toString();
    }

    //회원가입
    @PostMapping("/join")
    public String join(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        System.out.println("회원가입 요청이 들어옴 : "+ signupRequestDto.email());
        return memberServiceimpl.join(signupRequestDto); //리턴값은 바디로 출력됨
    }

    //이메일인증
    @PostMapping("/emailsend")
    public String sendMessage(@RequestBody @Valid MailTxtSendDto mailTxtSendDto) throws UnsupportedEncodingException {
        System.out.println("이메일 인증번호 전송 요청이 들어옴 : "+mailTxtSendDto.emailAddr());
        return mailSendService.sendTxtEmail(mailTxtSendDto);
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestBody @Valid String email, String password){ //loginDto사용으로 변경 필요

        return memberServiceimpl.login(email, password);
    }

}
/// @exceptionhandler, @controlleradvice 참고해서 추가하기
