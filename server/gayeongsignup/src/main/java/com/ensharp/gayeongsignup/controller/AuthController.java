package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.emailsend.EmailRequestDto;
import com.ensharp.gayeongsignup.emailsend.MailServiceImpl;
import com.ensharp.gayeongsignup.member.LoginDto;
import com.ensharp.gayeongsignup.member.MemberServiceImpl;
import com.ensharp.gayeongsignup.member.SignupRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 모드", description = "auth")
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
    @Operation(summary = "회원가입 요청",
            description = "등록되지 않은 이메일이며, 상세주소를 제외한 모든 값을 형식에 맞게 입력해주셔야 회원가입에 성공합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SignupRequestDto.class)
                    )
            ),
            @ApiResponse(responseCode = "409", description = "Error 409",
                    content =
                    @Content(
                            mediaType = "text/error-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "이미 사용 중인 이메일입니다")
                    ))
    })
    @PostMapping("/signup")
    public ResponseEntity<SignupRequestDto> join(@RequestBody @Valid SignupRequestDto signupRequestDto) throws Exception {
        System.out.println("회원가입 요청이 들어옴 : " + signupRequestDto.email());
        SignupRequestDto result = memberServiceImpl.join(signupRequestDto); //리턴값은 바디로 출력됨

        //if("success".equals(result)){
        //회원가입 성공시에만 동작. 그 전에 service에서 예외 던져짐
        return ResponseEntity.ok(result);
    }

    //로그인
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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto) { //loginDto사용으로 변경 필요
        System.out.println("로그인 요청이 들어옴 : " + loginDto.email());
        String result = memberServiceImpl.login(loginDto.email(), loginDto.password());
        return ResponseEntity.ok(result);
    }

    //Post 이메일 중복 검사
    @Operation(summary = "이메일 중복 검사 요청",
            description = "이미 가입된 이메일이 아니면 통과합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content =
                    @Content(
                            mediaType = "text/success-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "사용 가능한 이메일입니다")
                    )), //사용 가능한 이메일입니다
            @ApiResponse(responseCode = "409", description = "Error 409",
                    content =
                    @Content(
                            mediaType = "text/error-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "이미 사용 중인 이메일입니다")
                    )) //이미 사용 중인 이메일입니다
    })
    @PostMapping("/email-check")
    public ResponseEntity<String> checkEmail(@RequestBody @Valid EmailRequestDto emailRequestDto) { //loginDto사용으로 변경 필요
        System.out.println("이메일 중복 확인 요청이 들어옴 : " + emailRequestDto.email());
        String result = mailServiceImpl.checkEmail(emailRequestDto.email());
        return ResponseEntity.ok(result);
    }

    //Patch 비밀번호 변경
    @Operation(summary = "비밀번호 변경 요청",
            description = "이메일과 비밀번호를 전달받아 해당 이메일의 비밀번호를 변경합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Success",
                    content =
                    @Content(
                            mediaType = "text/success-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "비밀번호가 변경되었습니다")
                    )) //
    })
    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid LoginDto loginDto) {
        System.out.println("비밀번호 변경 요청이 들어옴 : " + loginDto.email());
        String result = memberServiceImpl.changePassword(loginDto.email(), loginDto.password());
        return ResponseEntity.ok(result);
    }

    //Get 특정 유저 정보
    @Operation(summary = "특정 유저 정보 검색 요청",
            description = "입력받은 이메일의 유저 정보를 전달합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원정보"), //{userInfo}
            @ApiResponse(responseCode = "409", description = "Error 409",
                    content =
                    @Content(
                            mediaType = "text/error-message",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "존재하지 않는 회원입니다")
                    )
            ) //존재하지 않는 회원입니다
    })
    @GetMapping("/me")
    public ResponseEntity<SignupRequestDto> getUserInfo(@RequestParam String email) {
        System.out.println("특정 유저 정보 확인 요청이 들어옴: " + email);
        SignupRequestDto result = memberServiceImpl.getUserInfo(email);
        return ResponseEntity.ok(result); //매핑된 유저 데이터 전달해야함
    }
}

