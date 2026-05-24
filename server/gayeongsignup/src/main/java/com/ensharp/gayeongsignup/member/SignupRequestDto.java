package com.ensharp.gayeongsignup.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequestDto( //레코드 패턴(불변객체)
                                @Schema(description = "이메일", example = "koo050803@naver.com")
                                @NotBlank(message = "이메일은 필수 입력 사항입니다.")
                                @Email(message = "올바른 이메일 형식을 입력해 주세요.")
                                String email,
                                @Schema(description = "8~16자의 영문 대/소문자, 숫자, 특수문자", example = "Koo098@@")
                                @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
                                @Size(min = 8, max = 16, message = "비밀번호 형식을 맞춰주세요.")
                                String password,
                                @Schema(description = "이름", example = "구가영")
                                @NotBlank(message = "이름은 필수 입력 사항입니다.")
                                String username,
                                @Schema(description = "주소", example = "경상남도 거제시 냠냠대로")
                                @NotBlank(message = "주소는 필수 입력 사항입니다.")
                                String streetAddress,
                                @Schema(description = "상세주소", example = "쩝쩝동", nullable = true)
                                //@NotBlank(message = "상세주소는 필수 입력 사항입니다.")
                                String detailAddress
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