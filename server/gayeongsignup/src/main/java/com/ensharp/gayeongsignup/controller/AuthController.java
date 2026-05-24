package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.EmailRequestDto;
import com.ensharp.gayeongsignup.emailsend.MailServiceImpl;
import com.ensharp.gayeongsignup.member.LoginDto;
import com.ensharp.gayeongsignup.member.MemberServiceImpl;
import com.ensharp.gayeongsignup.member.SignupRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@ControllerAdvice
@CrossOrigin //(origins = "")? 모든 도메인, 요청방식에 대해 허용
public class AuthController {
    private final MemberServiceImpl memberServiceImpl;
    private final MailServiceImpl mailServiceImpl;

    /// 인터페이스 사용하지 않고 있다?
    public AuthController(MemberServiceImpl memberServiceImpl, MailServiceImpl mailServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
        this.mailServiceImpl = mailServiceImpl;
    }/// i소문자 수정.

    /// 컨트롤러를 auth, email-verification등으로 분류하기

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> join(@RequestBody @Valid SignupRequestDto signupRequestDto) throws Exception {
        System.out.println("회원가입 요청이 들어옴 : " + signupRequestDto.email());
        String result = memberServiceImpl.join(signupRequestDto); //리턴값은 바디로 출력됨

        //if("success".equals(result)){
        //회원가입 성공시에만 동작. 그 전에 service에서 예외 던져짐
        return ResponseEntity.ok(result);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto) { //loginDto사용으로 변경 필요
        System.out.println("로그인 요청이 들어옴 : " + loginDto.email());
        String result= memberServiceImpl.login(loginDto.email(), loginDto.password());
        return ResponseEntity.ok(result);
    }

    //Post 이메일 중복 검사
    @PostMapping("/email-check")
    public ResponseEntity<String> checkEmail(@RequestBody @Valid EmailRequestDto emailRequestDto) { //loginDto사용으로 변경 필요
        System.out.println("이메일 중복 확인 요청이 들어옴 : " + emailRequestDto.email());
        String result= mailServiceImpl.checkEmail(emailRequestDto.email());
        return ResponseEntity.ok(result);
    }

    //Patch 비밀번호 변경
    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid LoginDto loginDto) {
        System.out.println("비밀번호 변경 요청이 들어옴 : " + loginDto.email());
        String result= memberServiceImpl.changePassword(loginDto.email(), loginDto.password());
        return ResponseEntity.ok(result);
    }

    //Get 특정 유저 정보
    @GetMapping("/me")
    public ResponseEntity<SignupRequestDto> getUserInfo(@RequestParam String email){
        System.out.println("특정 유저 정보 확인 요청이 들어옴: "+  email);
        SignupRequestDto result= memberServiceImpl.getUserInfo(email);
        return ResponseEntity.ok(result); //매핑된 유저 데이터 전달해야함
    }
}

