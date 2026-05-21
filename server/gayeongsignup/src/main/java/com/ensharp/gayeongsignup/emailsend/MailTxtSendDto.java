package com.ensharp.gayeongsignup.emailsend;

import com.ensharp.gayeongsignup.signup.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
    SimpleMailMessage 기반 텍스트 메일 전송 DTO
        @author : jonghoon
        @fileName : MailTxtSendDto
        @since : 11/11/24
 */
public record MailTxtSendDto(
        @NotBlank(message = "이메일은 필수 입력 사항입니다.")
        @Email(message = "올바른 이메일 형식을 입력해 주세요.")
        String emailAddr,
        @NotBlank(message = "제목은 필수 입력 사항입니다.") String subject,
        @NotBlank(message = "내용은 필수 입력 사항입니다.") String content
        ) {
    public MailTxtSendDto(MailTxtSend entity) { //생성자
        this(
                entity.getEmailAddr(),
                entity.getSubject(),
                entity.getContent()
        );
    }

}
