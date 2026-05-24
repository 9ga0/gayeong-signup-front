package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.EmailRequestDto;
import com.ensharp.gayeongsignup.emailsend.EmailVarificationDto;
import com.ensharp.gayeongsignup.emailsend.MailServiceImpl;
import com.ensharp.gayeongsignup.member.MemberServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/email-verification")
@ControllerAdvice
@CrossOrigin
public class EmailSendController {
    private final MailServiceImpl mailServiceImpl;

    public EmailSendController(MemberServiceImpl memberServiceImpl, MailServiceImpl mailServiceImpl) {
        this.mailServiceImpl = mailServiceImpl;
    }

    //이메일인증번호 전송
    @PostMapping("/request")
    public ResponseEntity<String> sendMessage(@RequestBody @Valid EmailRequestDto emailRequestDto) throws UnsupportedEncodingException {
        System.out.println("이메일 인증번호 전송 요청이 들어옴 : " + emailRequestDto.email());
        String result= mailServiceImpl.sendTextEmail(emailRequestDto.email());
        return ResponseEntity.ok(result);
    }

    //이메일 인증
    @PostMapping("/confirm")
    public ResponseEntity<String> confirmVerificationCode(@RequestBody @Valid EmailVarificationDto emailVarificationDto) {
        System.out.println("이메일 인증번호 검증 : " + emailVarificationDto.email());
        String result= mailServiceImpl.confirmVerificationCode(emailVarificationDto.email(), emailVarificationDto.verificationCode());
        return ResponseEntity.ok(result);
    }
}
