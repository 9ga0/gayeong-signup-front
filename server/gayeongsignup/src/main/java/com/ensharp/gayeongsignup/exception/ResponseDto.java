package com.ensharp.gayeongsignup.exception;

import java.time.LocalDateTime;

public class ResponseDto<T> {
    private int status;         // HTTP 상태 코드
    private String message;     // 에러 메시지 또는 응답 메시지
    private T data;             // 반환할 데이터 (에러 시 null)
    private LocalDateTime timestamp; // 응답 시간

    public ResponseDto(int i, String str, T data) {
        this.status=i;
        this.message=str;
        this.data=data;
        this.timestamp=LocalDateTime.now();
    }

}