package com.ensharp.gayeongsignup.dto;

import lombok.Data;

@Data
public class JoinRequest {
    private String email;
    private String password;
    private String username;
    private String streetAddress;
    private String detailAddress;
}
