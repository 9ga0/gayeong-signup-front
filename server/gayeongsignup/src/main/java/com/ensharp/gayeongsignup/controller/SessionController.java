package com.ensharp.gayeongsignup.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
