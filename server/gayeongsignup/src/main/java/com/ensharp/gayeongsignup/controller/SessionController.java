package com.ensharp.gayeongsignup.controller;


import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
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
    @PostMapping("")
    public ResponseEntity<String> login( HttpSession session) { //
        //세션 생성, service 불러와 로그인 로직 수행 및 이름 가져와 출력.
//        System.out.println("로그인 요청 들어옴: "+loginDto.email());
//        String username = memberServiceImpl.login(loginDto.email(), loginDto.password(),session);
        return ResponseEntity.ok("로그인 성공적");//회원 이름을 출력
    }

    //Delete - /current - 현재 세션 삭제, 로그아웃 - 로그인 사용자
    @Operation(summary = "현재 세션 삭제, 로그아웃",
            description = "세션 삭제하여 로그아웃합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content =
                    @Content(
                            mediaType = "text/success-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "로그아웃 되었습니다")
                    )), //로그아웃 되었습니다
    })
    @DeleteMapping("/current")
    public ResponseEntity<String> logout(HttpSession session) {
        System.out.println("로그아웃, 세션 삭제 요청이 들어옴 : " + session.getId());
        session.invalidate(); //세션 무효화
        System.out.println("로그아웃(세션 삭제) 성공");
        return ResponseEntity.ok("로그아웃 되었습니다");
    }

    //get - /current - 현재 세션 조회, 로그인 상태 확인 - 전체
    @Operation(summary = "현재 세션 조회, 로그인 상태 확인",
            description = "현재 세션 조회하여 로그인 상태를 확인합니다.")
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
                            examples = @ExampleObject(value = "로그인되어 있지 않습니다")
                    )), //로그인되어 있지 않습니다
    })
    @GetMapping("/current")
    public ResponseEntity<String> getCurrentSession(HttpSession session){
        String username = session.getAttribute("loginUser").toString();
        System.out.println("로그인 상태 확인(현재 세션 조회) 요청");
        if(username!=null){
            return ResponseEntity.ok(username);
        } else{
            throw new CustomException(ErrorCode.INVALID_LOGIN_SESSION);
        }
    }

}
