package com.ensharp.gayeongsignup.emailsend;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record EmailVerificationDto(
        @Id
        @Email
        @Schema(description = "이메일", example = "koo050803@naver.com")
        @NotBlank(message = "이메일을 입력해 주세요")
        String email,
        @Schema(description = "인증번호", example = "123456")
        @NotBlank(message = "인증 번호를 입력해 주세요")
        String verificationCode,
        boolean isAuthenticated
) {
    public EmailVerificationDto(EmailVerification entity) { //생성자
        this(
                entity.getEmail(),
                entity.getVerificationCode(),
                entity.getIsAuthenticated()
        );
    }
}
