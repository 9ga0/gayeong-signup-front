package com.ensharp.gayeongsignup.emailsend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_send") ///스네이크케이스. 복수형
public class EmailVerification {
    @Id
    @Column(nullable = false) //필수 정보들. 비어있을 수 없다
    private String email;
    @Column(nullable = false)
    private String verificationCode;

    private EmailVerification() {
    }
/// EmailVerification네이밍
    public EmailVerification(String email, String verificationCode) {
        this.email=email;
        this.verificationCode=verificationCode;
    }

    private EmailVerification(Builder builder) { //Member 생성자
        this.email = builder.email;
        this.verificationCode = builder.verificationCode;
    }

    public static class Builder {
        private final String email;
        private final String verificationCode;

        public Builder(EmailVarificationDto emailVarificationDto) { //필수 값을 dto로 전달
            this.email = emailVarificationDto.email();
            this.verificationCode = emailVarificationDto.verificationCode();
        }
    }

    public String getEmail() {
        return email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}
