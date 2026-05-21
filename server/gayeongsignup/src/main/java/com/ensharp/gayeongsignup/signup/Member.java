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

        public MemberBuilder(MemberDTO memberDTO) { //필수 값을 dto로 전달
            this.email = memberDTO.email();
            this.password = memberDTO.password();
            this.username = memberDTO.username();
            this.streetAddress = memberDTO.streetAddress();
            this.detailAddress = memberDTO.detailAddress();
        }

        public Member build() { //DTO -> Entity
            if (username==null ||email==null ||password==null||streetAddress==null||detailAddress==null){
                throw new IllegalStateException("필수값을 적어주세요.");
            }
            return new Member(this);
        }
    }
//
//    public Member email(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public Member password(String password) {
//        this.password = password;
//        return this;
//    }
//
//    public Member username(String username) {
//        this.username = username;
//        return this;
//    }
//
//    public Member streetAddress(String streetAddress) {
//        this.streetAddress = streetAddress;
//        return this;
//    }
//
//    public Member detailAddress(String detailAddress) {
//        this.detailAddress = detailAddress;
//        return this;
//    }

    public MemberDTO toDto() {
        return new MemberDTO(this);
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

    @Override
    public String toString() {
        return "MemberDTO{" +
                "'email'='" + email + '\'' +
                ", 'password'='" + password + '\'' +
                ", 'username'='" + username + '\'' +
                ", 'streetAddress'='" + streetAddress + '\'' +
                ", 'detailAddress'='" + detailAddress + '\'' +
                "}";
    }

}