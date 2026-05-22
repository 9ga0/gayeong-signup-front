package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.EmailRequestDto;
import com.ensharp.gayeongsignup.emailsend.EmailVarifyDto;
import com.ensharp.gayeongsignup.emailsend.MailService;
import com.ensharp.gayeongsignup.emailsend.MailServiceImpl;
import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import com.ensharp.gayeongsignup.exception.ErrorDto;
import com.ensharp.gayeongsignup.exception.ResponseDto;
import com.ensharp.gayeongsignup.signup.LoginDto;
import com.ensharp.gayeongsignup.signup.MemberServiceimpl;
import com.ensharp.gayeongsignup.signup.SignupRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1")
@ControllerAdvice
@CrossOrigin //(origins = "")? 모든 도메인, 요청방식에 대해 허용
public class DBController {
    private final MemberServiceimpl memberServiceimpl;
    private final MailServiceImpl mailServiceimpl;

    public DBController(MemberServiceimpl memberServiceimpl, MailService mailService, MailServiceImpl mailServiceimpl) {
        this.memberServiceimpl = memberServiceimpl;
        this.mailServiceimpl = mailServiceimpl;
    }

    //테스트용
    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return signupRequestDto.toString();
    }

    //회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity join(@RequestBody @Valid SignupRequestDto signupRequestDto) throws Exception {
        System.out.println("회원가입 요청이 들어옴 : " + signupRequestDto.email());
        String result = memberServiceimpl.join(signupRequestDto); //리턴값은 바디로 출력됨

        //if("success".equals(result)){
        //회원가입 성공시에만 동작. 그 전에 service에서 예외 던져짐
        return ResponseEntity.ok(result);
    }

    //이메일인증번호 전송
    @PostMapping("/email-verification/request")
    public ResponseEntity sendMessage(@RequestBody @Valid EmailRequestDto emailRequestDto) throws UnsupportedEncodingException {
        System.out.println("이메일 인증번호 전송 요청이 들어옴 : " + emailRequestDto.email());
        String result= mailServiceimpl.sendTxtEmail(emailRequestDto.email());
        return ResponseEntity.ok(result);
    }

    //이메일 인증
    @PostMapping("/email-verification/confirm")
    public ResponseEntity sendMessage(@RequestBody @Valid EmailVarifyDto emailVarifyDto) {
        System.out.println("이메일 인증번호 검증 : " + emailVarifyDto.email());
        String result= mailServiceimpl.confirmVerificationCode(emailVarifyDto.email(), emailVarifyDto.verificationCode());
        return ResponseEntity.ok(result);
    }

    //로그인
    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) { //loginDto사용으로 변경 필요
        System.out.println("로그인 요청이 들어옴 : " + loginDto.email());
        String result= memberServiceimpl.login(loginDto.email(), loginDto.password());
        return ResponseEntity.ok(result);
    }

    //Post 이메일 중복 검사
    @PostMapping("/auth/email-check")
    public ResponseEntity checkEmail(@RequestBody @Valid EmailRequestDto emailRequestDto) { //loginDto사용으로 변경 필요
        System.out.println("이메일 중복 확인 요청이 들어옴 : " + emailRequestDto.email());
        String result= mailServiceimpl.checkEmail(emailRequestDto.email());
        return ResponseEntity.ok(result);
    }

    //Patch 비밀번호 변경
    @PatchMapping("/auth/password")
    public ResponseEntity changePassword(@RequestBody @Valid LoginDto loginDto) {
        System.out.println("비밀번호 변경 요청이 들어옴 : " + loginDto.email());
        String result= memberServiceimpl.changePassword(loginDto.email(), loginDto.password());
        return ResponseEntity.ok(result);
    }

    //Get 특정 유저 정보
    //@GetMapping('/auth/me')
}

