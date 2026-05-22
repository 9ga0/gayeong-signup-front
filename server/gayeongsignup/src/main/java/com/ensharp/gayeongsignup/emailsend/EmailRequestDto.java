package com.ensharp.gayeongsignup.emailsend;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDto(
        @Email @NotBlank(message = "이메일을 입력해 주세요")
        String email
) {
public EmailRequestDto(String email){
        this.email=email;
    }

}