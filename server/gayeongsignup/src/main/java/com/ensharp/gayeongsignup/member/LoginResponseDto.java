package com.ensharp.gayeongsignup.member;

public record LoginResponseDto(
        String username,
        String email,
        String role,
        String streetAddress,
        String detailAddress
) {}
