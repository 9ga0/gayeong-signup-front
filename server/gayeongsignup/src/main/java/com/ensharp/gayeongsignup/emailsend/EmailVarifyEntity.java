package com.ensharp.gayeongsignup.emailsend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmailSend")
public class EmailVarifyEntity {
    @Id
    @Column(nullable = false) //필수 정보들. 비어있을 수 없다
    private String email;
    @Column(nullable = false)
    private String verificationCode;

    private EmailVarifyEntity() {
    }

    public EmailVarifyEntity(String email, String verificationCode) {
        this.email=email;
        this.verificationCode=verificationCode;
    }

    private EmailVarifyEntity(Builder builder) { //Member 생성자
        this.email = builder.email;
        this.verificationCode = builder.verificationCode;
    }

    public static class Builder {
        private final String email;
        private final String verificationCode;

        public Builder(EmailVarifyDto emailVarifyDto) { //필수 값을 dto로 전달
            this.email = emailVarifyDto.email();
            this.verificationCode = emailVarifyDto.verificationCode();
        }

    }

    public String getEmail() {
        return email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}
