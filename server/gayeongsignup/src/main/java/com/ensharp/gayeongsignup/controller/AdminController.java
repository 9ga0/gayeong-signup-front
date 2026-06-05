package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.EmailRepository;
import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import com.ensharp.gayeongsignup.member.MemberRepository;
import com.ensharp.gayeongsignup.member.MemberServiceImpl;
import com.ensharp.gayeongsignup.member.SignupRequestDto;
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

import java.util.List;

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
    private final EmailRepository emailRepository;
    private final MemberServiceImpl memberServiceImpl;

    public AdminController(MemberRepository memberRepository, EmailRepository emailRepository, MemberServiceImpl memberServiceImpl) {
        this.memberRepository = memberRepository;
        this.emailRepository = emailRepository;
        this.memberServiceImpl = memberServiceImpl;
    }

    //Get - /users - 전체 유저조회 - ADMIN
    @Operation(summary = "전체 유저 조회", description = "회원가입된 모든 유저를 조회합니다.")
    @GetMapping("/users")
    public ResponseEntity<List<SignupRequestDto>> getAllUserInfo() {
        System.out.println("전체 유저 정보 확인 요청이 들어옴");
        List<SignupRequestDto> result = memberServiceImpl.getAllUserInfo();
        return ResponseEntity.ok(result); //매핑된 유저 데이터 전달해야함
    }


    //Delete - /users/{email} - 특정 유저 삭제 -ADMIN
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content =
                    @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "해당 회원 정보를 삭제했습니다")
                    )
            )
    })
    @Transactional
    @Operation(summary = "특정 유저 삭제", description = "이메일을 기준으로 사용자를 삭제합니다.")
    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(@Parameter(name = "email", description = "이메일", required = true, example = "koo050803@naver.com")
                                             @RequestParam String email) {
        System.out.println("회원정보 삭제 요청이 들어옴 : " + email);
        if (memberRepository.existsByEmail(email)) { //회원 존재하면 해당 회원 삭제
            memberRepository.deleteByEmail(email);
            System.out.println("회원 삭제 성공");
            return ResponseEntity.ok("해당 회원 정보를 삭제했습니다");
        }
        throw new CustomException(ErrorCode.USER_NOT_FOUND);
    }

    //Delete - /email-verifications - 이메일 인증 기록 전체 삭제
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content =
                    @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "모든 이메일 인증 기록을 삭제했습니다")
                    )
            )
    })
    @Transactional
    @Operation(summary = "이메일 인증 기록 전체 삭제", description = "이메일 인증 기록을 전체 삭제합니다.")
    @DeleteMapping("/email-verifications")
    public ResponseEntity<String> deleteEmail() {
        System.out.println("이메일 인증 기록 전체 삭제 요청이 들어옴 : ");
        emailRepository.deleteAll();
        System.out.println("전체 삭제 성공");
        return ResponseEntity.ok("모든 이메일 인증 기록을 삭제했습니다");
    }

}
