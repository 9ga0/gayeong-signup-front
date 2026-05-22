package com.ensharp.gayeongsignup.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequestDto( //레코드 패턴(불변객체)
                                @NotBlank(message = "이메일은 필수 입력 사항입니다.")
                         @Email(message= "올바른 이메일 형식을 입력해 주세요.")
                         String email,
                                @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
                         @Size(min = 8, max = 16, message = "비밀번호 형식을 맞춰주세요.") String password,
                                @NotBlank(message = "이름은 필수 입력 사항입니다.") String username,
                                @NotBlank(message = "주소는 필수 입력 사항입니다.") String streetAddress,
                                @NotBlank(message = "상세주소는 필수 입력 사항입니다.") String detailAddress
) {
    public SignupRequestDto(Member entity) { //생성자
        this(
                entity.getEmail(),
                entity.getPassword(),
                entity.getUsername(),
                entity.getStreetAddress(),
                entity.getDetailAddress()
        );
    }
}