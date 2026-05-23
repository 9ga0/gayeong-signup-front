package com.ensharp.gayeongsignup.exception;

import jakarta.validation.constraints.NotBlank;

public record ErrorDto(
        @NotBlank int status,
        @NotBlank String message
) {
    public ErrorDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
