package com.ensharp.gayeongsignup.signup;

import jakarta.persistence.*;

@Entity
@Table(name = "MEMBER")
public class Member {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Id
    @Column(nullable = false) //필수 정보들. 비어있을 수 없다
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String streetAddress;
    @Column(nullable = false)
    private String detailAddress;

    private Member() {
    }

    private Member(MemberBuilder builder) { //Member 생성자
        this.email = builder.email;
        this.password = builder.password;
        this.username = builder.username;
        this.streetAddress = builder.streetAddress;
        this.detailAddress = builder.detailAddress;
    }

    public static class MemberBuilder {
        private final String email;
        private final String password;
        private final String username;
        private final String streetAddress;
        private final String detailAddress;

        public MemberBuilder(SignupRequestDto signupRequestDto) { //필수 값을 dto로 전달
            this.email = signupRequestDto.email();
            this.password = signupRequestDto.password();
            this.username = signupRequestDto.username();
            this.streetAddress = signupRequestDto.streetAddress();
            this.detailAddress = signupRequestDto.detailAddress();
        }

        public Member build() { //DTO -> Entity
            if (username == null || email == null || password == null || streetAddress == null || detailAddress == null) {
                throw new IllegalStateException("필수값을 적어주세요.");
            }
            return new Member(this);
        }
    }

    public SignupRequestDto toDto() {
        return new SignupRequestDto(this);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void updatePassword(String newPassword) {
        password = newPassword;
    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", password=" + password + '\'' +
                ", username=" + username + '\'' +
                ", streetAddress=" + streetAddress + '\'' +
                ", detailAddress=" + detailAddress + '\'' +
                "}";
    }

}