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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "구가영")
                    )), //{username}
            @ApiResponse(responseCode = "401", description = "Error 401",
                    content =
                    @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "사용자를 찾을 수 없습니다")
                    )) //"사용자를 찾을 수 없습니다"
    })
    @PostMapping("")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto, HttpSession session) { /// 로직수정
        //세션 생성, service 불러와 로그인 로직 수행 및 이름 가져와 출력.
//        System.out.println("로그인 요청 들어옴: "+loginDto.email());
//        String username = memberServiceImpl.login(loginDto.email(), loginDto.password(),session);
//        return ResponseEntity.ok(username);//회원 이름을 출력
        return ResponseEntity.ok("Spring Security 필터가 로그인 세션을 사용해서 처리합니다");
    }

    //Delete - /current - 현재 세션 삭제, 로그아웃 - 로그인 사용자
    @Operation(summary = "현재 세션 삭제, 로그아웃",
            description = "세션 삭제하여 로그아웃합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content =
                    @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "로그아웃 되었습니다")
                    )), //로그아웃 되었습니다
    })
    @DeleteMapping("/current")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("로그아웃, 세션 삭제 요청이 들어옴 ");
        Authentication auththentication = SecurityContextHolder.getContext().getAuthentication();
        if(auththentication != null){
            //시큐리티가 제공하는 로그아웃 핸들러 사용
            new SecurityContextLogoutHandler().logout(request, response, auththentication);
        }
        //session.invalidate(); //세션 무효화
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
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "구가영")
                    )), //{username}
            @ApiResponse(responseCode = "401", description = "Error 401",
                    content =
                    @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "로그인되어 있지 않습니다")
                    )), //로그인되어 있지 않습니다
    })
    @GetMapping("/current")
    public ResponseEntity<String> getCurrentSession(Authentication authentication) {
        System.out.println("로그인 상태 확인(현재 세션 조회) 요청");

        //로그인 중인 사용자가 없거나, 익명의 사용자라면 로그인되지 않은 것
        if (authentication == null ||!authentication.isAuthenticated()|| "anonymousUser".equals(authentication.getPrincipal())) {
            throw new CustomException(ErrorCode.INVALID_LOGIN_SESSION);
        }
        return ResponseEntity.ok(authentication.getName()); //로그인 상태인 유저의 이름을 반환

//        //현재 스레드의 인증정보를 가져옴
//        SecurityContextHolder.getContext();
//        //현재 로그인한 사용자의 Authentication객체를 반환.
//        SecurityContextHolder.getContext().getAuthentication();
//        //현재 사용자가 인증된 상태인지 boolean으로 확인
//        authentication.isAuthenticated();
//        //세션에 담긴 사용자 정보를 꺼냄
//        authentication.getPrincipal();
    }

}
