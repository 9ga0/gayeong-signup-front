package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import com.ensharp.gayeongsignup.member.MemberRepository;
import com.ensharp.gayeongsignup.member.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Get - /users - 전체 유저조회 - ADMIN
Delete - /users/{email} - 특정 유저 삭제 -ADMIN
Delete - /email-verifications - 이메일 인증 기록 전체 삭제
 */

@Tag(name = "관리자 모드", description = "manage")

@RestController
@RequestMapping("/api/v1/admin")
@ControllerAdvice
@CrossOrigin
public class AdminController {
    private final MemberRepository memberRepository;
    private final MemberServiceImpl memberServiceImpl;

    public AdminController(MemberRepository memberRepository, MemberServiceImpl memberServiceImpl) {
        this.memberRepository = memberRepository;
        this.memberServiceImpl = memberServiceImpl;
    }

    //Get - /users - 전체 유저조회 - ADMIN



    //Delete - /users/{email} - 특정 유저 삭제 -ADMIN
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content =
                    @Content(
                            mediaType = "text/success-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "해당 회원 정보를 삭제했습니다")
                    )
            )
    })
    @Transactional
    @Operation(summary = "특정 유저 삭제", description = "이메일을 기준으로 사용자를 삭제합니다.")
    @DeleteMapping("/users/{email}")
    public ResponseEntity<String> deleteUser(@Parameter(name = "email", description = "이메일", required = true, example = "koo050803@naver.com")
                                             @RequestParam String email) {
        System.out.println("회원정보 삭제 요청이 들어옴 : " + email);
        if (memberRepository.existsByEmail(email)) { //회원 존재하면 해당 회원 삭제
            memberRepository.deleteByEmail(email);
            System.out.println("회원 삭제 성공");
            return ResponseEntity.ok("success");
        }
        throw new CustomException(ErrorCode.USER_NOT_FOUND);
    }

    //Delete - /email-verifications - 이메일 인증 기록 전체 삭제


}
