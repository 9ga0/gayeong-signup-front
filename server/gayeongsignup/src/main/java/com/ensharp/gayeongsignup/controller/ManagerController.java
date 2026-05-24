package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.EmailRequestDto;
import com.ensharp.gayeongsignup.member.MemberRepository;
import com.ensharp.gayeongsignup.member.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manage")
@ControllerAdvice
@CrossOrigin
public class ManagerController {
    private final MemberRepository memberRepository;
    private final MemberServiceImpl memberServiceImpl;

    public ManagerController(MemberRepository memberRepository, MemberServiceImpl memberServiceImpl) {
        this.memberRepository = memberRepository;
        this.memberServiceImpl = memberServiceImpl;
    }

    //특정 유저 정보 삭제
    @Transactional
    @Operation(summary = "사용자 삭제", description = "이메일을 기준으로 사용자를 삭제합니다.")
    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@Parameter(name = "email",description = "이메일", required = true, example = "koo050803@naver.com")
                                                 @RequestParam String email){
        System.out.println("회원정보 삭제 요청이 들어옴 : " + email);
        if (memberRepository.existsByEmail(email)) { //이미 이메일있으면 그 데이터 지우고 새로 넣기위함.
            memberRepository.deleteByEmail(email);
        }
        System.out.println("회원 삭제 성공");
        return ResponseEntity.ok("success");
    }



}
