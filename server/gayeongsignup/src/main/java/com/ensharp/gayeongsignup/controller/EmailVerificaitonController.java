package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.EmailRequestDto;
import com.ensharp.gayeongsignup.emailsend.EmailVerificationDto;
import com.ensharp.gayeongsignup.emailsend.MailServiceImpl;
import com.ensharp.gayeongsignup.member.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Post - / - 이메일 인증 요청 생성, 인증번호 메일 전송 -전체
Patch - / - 이메일 인증 상태 변경, 인증번호 검증 - 전체
 */

@Tag(name = "이메일 인증 모드", description = "email-verification")
@RestController
@RequestMapping("/api/v1/email-verifications")
@ControllerAdvice
@CrossOrigin
public class EmailVerificaitonController {
    private final MailServiceImpl mailServiceImpl;

    public EmailVerificaitonController(MemberServiceImpl memberServiceImpl, MailServiceImpl mailServiceImpl) {
        this.mailServiceImpl = mailServiceImpl;
    }

    //Post - / - 이메일 인증 요청 생성, 인증번호 메일 전송 -전체
    @Operation(summary = "이메일 인증 검사 요청",
            description = "해당 이메일과 인증번호가 일치하면 통과합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content =
                    @Content(
                            mediaType = "text/success-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "인증 번호가 발송되었습니다")
                    )), //인증 번호가 발송되었습니다.
            @ApiResponse(responseCode = "400", description = "Error 400",
                    content =
                    @Content(
                            mediaType = "text/error-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "유효한 이메일 형식을 입력하세요")
                    )) //유효한 이메일 형식을 입력하세요.
    })
    @PostMapping("")
    public ResponseEntity<String> sendMessage(@RequestBody @Valid EmailRequestDto emailRequestDto) {
        System.out.println("이메일 인증번호 전송 요청이 들어옴 : " + emailRequestDto.email());
        mailServiceImpl.deleteEmailSendHistoryIfExists(emailRequestDto.email());

        String result = mailServiceImpl.sendTextEmail(emailRequestDto.email());
        //위에서 예외발생하지 않았으면 아래 실행됨
        return ResponseEntity.ok(result);
    }

    //Patch - / - 이메일 인증 상태 변경, 인증번호 검증 - 전체
    @Operation(summary = "이메일 인증 검사 요청",
            description = "해당 이메일과 인증번호가 일치하면 통과합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content =
                    @Content(
                            mediaType = "text/success-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "인증 번호가 확인되었습니다")
                    )), //인증 번호가 확인되었습니다.
            @ApiResponse(responseCode = "404", description = "Error 404",
                    content =
                    @Content(
                            mediaType = "text/error-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "해당 메일로 인증 코드가 전송된 기록이 없습니다")
                    )) //해당 메일로 인증 코드가 전송된 기록이 없습니다
    })
    @PatchMapping("") //Patch 변경됨. 이메일 dto에 인증 상태 추가하여, 인증 상태를 업데이트할 수 있도록 수정 필요?
    public ResponseEntity<String> confirmVerificationCode(@RequestBody @Valid EmailVerificationDto emailVerificationDto) {
        System.out.println("이메일 인증번호 검증 : " + emailVerificationDto.email());
        String result = mailServiceImpl.confirmVerificationCode(emailVerificationDto.email(), emailVerificationDto.verificationCode());
        return ResponseEntity.ok(result);
    }
}
