package com.ensharp.gayeongsignup.member;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/// 엔티티가 디티오를 알고있지 못하도록
@Entity
@Table(name = "members")///members 또는 users
public class Member implements UserDetails {
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
     ///상세주소 입력안해도 넘어갈수있어야
    private String detailAddress;

    private String userRole; //권한: ROLE_ADMIN, ROLE_USER

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

    @Override //사용자의 권한 목록 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        String role = (userRole != null) ? userRole : "ROLE_USER";
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public boolean isEnabled(){
        return this.isEnabled(); //사용자 활성화 여부 반환
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

    public String getUserRole(){
        return userRole;
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