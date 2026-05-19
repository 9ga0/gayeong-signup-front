package com.ensharp.gayeongsignup.dto;

import com.ensharp.gayeongsignup.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberDTO( //레코드 패턴(불변객체)
                         @Email
                         @NotBlank(message = "이메일은 필수 입력 사항입니다.") String email,
                         @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
                         @Size(min = 8, max = 16, message = "비밀번호 형식을 맞춰주세요.")
                         String password,
                         @NotBlank(message = "이름은 필수 입력 사항입니다.") String username,
                         @NotBlank(message = "주소는 필수 입력 사항입니다.") String streetAddress,
                         @NotBlank(message = "상세주소는 필수 입력 사항입니다.") String detailAddress
) {
    public MemberDTO(Member entity) { //생성자
        this(
                entity.getEmail(),
                entity.getPassword(),
                entity.getUsername(),
                entity.getStreetAddress(),
                entity.getDetailAddress()
        );
    }


    public String email() {
        return this.email;
    }

    public String password() {
        return this.password;
    }

    public String username() {
        return this.username;
    }

    public String streetAddress() {
        return this.streetAddress;
    }

    public String detailAddress() {
        return this.detailAddress;
    }
}