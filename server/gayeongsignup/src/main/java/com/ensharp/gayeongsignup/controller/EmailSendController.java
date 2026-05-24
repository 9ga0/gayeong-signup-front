package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.EmailRequestDto;
import com.ensharp.gayeongsignup.emailsend.EmailVerificationDto;
import com.ensharp.gayeongsignup.emailsend.MailServiceImpl;
import com.ensharp.gayeongsignup.member.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "이메일 인증 검사 요청",
            description = "해당 이메일과 인증번호가 일치하면 통과합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"), //인증 번호가 발송되었습니다.
            @ApiResponse(responseCode = "400", description = "Error 400") //유효한 이메일 형식을 입력하세요.
    })
    @PostMapping("/request")
    public ResponseEntity<String> sendMessage(@RequestBody @Valid EmailRequestDto emailRequestDto) {
        System.out.println("이메일 인증번호 전송 요청이 들어옴 : " + emailRequestDto.email());
        mailServiceImpl.deleteEmailSendHistoryIfExists(emailRequestDto.email());

        String result = mailServiceImpl.sendTextEmail(emailRequestDto.email());
        //위에서 예외발생하지 않았으면 아래 실행됨
        return ResponseEntity.ok(result);
    }

    //이메일 인증
    @Operation(summary = "이메일 인증 검사 요청",
            description = "해당 이메일과 인증번호가 일치하면 통과합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"), //인증 번호가 확인되었습니다.
            @ApiResponse(responseCode = "404", description = "Error 404") //해당 메일로 인증 코드가 전송된 기록이 없습니다
    })
    @PostMapping("/confirm")
    public ResponseEntity<String> confirmVerificationCode(@RequestBody @Valid EmailVerificationDto emailVerificationDto) {
        System.out.println("이메일 인증번호 검증 : " + emailVerificationDto.email());
        String result = mailServiceImpl.confirmVerificationCode(emailVerificationDto.email(), emailVerificationDto.verificationCode());
        return ResponseEntity.ok(result);
    }
}
