package com.ensharp.gayeongsignup.emailsend;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record EmailVarificationDto(
        @Id
        @Email
        @NotBlank(message = "이메일을 입력해 주세요")
        String email,
        @NotBlank(message = "인증 번호를 입력해 주세요")
        String verificationCode
) {
    public EmailVarificationDto(EmailVerification entity) { //생성자
        this(
                entity.getEmail(),
                entity.getVerificationCode()
        );
    }
}
