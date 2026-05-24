package com.ensharp.gayeongsignup.exception;

public enum ErrorCode {
    USER_NOT_FOUND(401, "사용자를 찾을 수 없습니다"),
    HAS_EMAIL(409, "이미 사용 중인 이메일입니다"),
    INVALID_PASSWORD(401, "비밀번호가 옳지 않습니다"),
    INCORRECT_AUTH_NUMBER(400,  "인증 번호가 일치하지 않습니다"),
    MAIL_NOT_FOUND(404, "해당 메일로 인증 코드가 전송된 기록이 없습니다"),
    INVALID_EMAIL(400, "유효한 이메일 형식을 입력하세요"),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500,"서버 에러입니다. 서버 팀에 연락주세요!"); /// 고치기

    /// 예외처리. 익셉션 공부하고 사용하기.
    private final int status;
    private final String message;

    ErrorCode(int status,  String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
