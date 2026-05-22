package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.EmailRequestDto;
import com.ensharp.gayeongsignup.emailsend.EmailVarifyDto;
import com.ensharp.gayeongsignup.emailsend.MailService;
import com.ensharp.gayeongsignup.emailsend.MailServiceImpl;
import com.ensharp.gayeongsignup.signup.MemberServiceimpl;
import com.ensharp.gayeongsignup.signup.SignupRequestDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@ControllerAdvice
@CrossOrigin(origins = "")
public class DBController {
    private final MemberServiceimpl memberServiceimpl;
    private final MailServiceImpl mailServiceimpl;

    public DBController(MemberServiceimpl memberServiceimpl, MailService mailService, MailServiceImpl mailServiceimpl) {
        this.memberServiceimpl = memberServiceimpl;
        this.mailServiceimpl = mailServiceimpl;
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
    @PostMapping("/auth/signup")
    public String join(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        System.out.println("회원가입 요청이 들어옴 : "+ signupRequestDto.email());
        return memberServiceimpl.join(signupRequestDto); //리턴값은 바디로 출력됨
    }

    //이메일인증번호 전송
    @PostMapping("/email-verification/request")
    public String sendMessage(@RequestBody @Valid EmailRequestDto emailRequestDto) throws UnsupportedEncodingException {
        System.out.println("이메일 인증번호 전송 요청이 들어옴 : "+emailRequestDto.email());
        return mailServiceimpl.sendTxtEmail(emailRequestDto.email());
    }

    //이메일 인증
    @PostMapping("/email-verification/confirm")
    public String sendMessage(@RequestBody @Valid EmailVarifyDto emailVarifyDto) {
        System.out.println("이메일 인증번호 검증 : "+ emailVarifyDto.email());
        return mailServiceimpl.confirmVerificationCode(emailVarifyDto.email(),emailVarifyDto.verificationCode());
    }

    //로그인
    @PostMapping("/auth/login")
    public String login(@RequestBody @Valid String email, String password){ //loginDto사용으로 변경 필요

        return memberServiceimpl.login(email, password);
    }

}
/// @exceptionhandler, @controlleradvice 참고해서 추가하기

