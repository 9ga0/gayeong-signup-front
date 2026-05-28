package com.ensharp.gayeongsignup.controller;


import ch.qos.logback.core.model.Model;
import com.ensharp.gayeongsignup.member.LoginDto;
import com.ensharp.gayeongsignup.member.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
post - / - 세션 생성, 로그인 - 전체
Delete - /current - 현재 세션 삭제, 로그아웃 - 로그인 사용자
get - /current - 현재 세션 조회, 로그인 상태 확인 - 전체
 */

@Tag(name = "세션 모드", description = "sessions")
@RestController
@RequestMapping("/api/v1/sessions")
@ControllerAdvice
@CrossOrigin
public class SessionController {
    private final MemberServiceImpl memberServiceImpl;

    public SessionController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    //post - / - 세션 생성, 로그인 - 전체
    @Operation(summary = "로그인 요청",
            description = "회원가입되어있는 계정이면 로그인에 성공합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content =
                    @Content(
                            mediaType = "text/success-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "구가영")
                    )), //{username}
            @ApiResponse(responseCode = "401", description = "Error 401",
                    content =
                    @Content(
                            mediaType = "text/error-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "비밀번호가 옳지 않습니다")
                    )) //"비밀번호가 옳지 않습니다"
    })
    @GetMapping("/login")
    public String showLoginForm(@RequestBody @Valid LoginDto loginDto) { //loginDto사용으로 변경 필요
        //세션 생성, 로그인 구현?
        return "/user/loginform";
    }
//
//    @GetMapping(value="/login")
//    public String login(HttpSession session, LoginDto dto, Model model) throws Exception{
//        String errorMessage = (String) session.getAttribute("errorMessage");
//        System.out.println(errorMessage);
//        //model.addAttribute("msg",errorMessage);
//        return "common/login.main";
//    }
}
