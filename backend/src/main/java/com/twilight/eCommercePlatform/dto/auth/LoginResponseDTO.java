package com.twilight.eCommercePlatform.dto.auth;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private Long userId;
    private String token;
    private String username;
    private String userRole;
    private String view;
}