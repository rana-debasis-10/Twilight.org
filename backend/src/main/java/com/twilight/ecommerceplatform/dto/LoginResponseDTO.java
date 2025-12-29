package com.twilight.ecommerceplatform.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private Long userId;
    private String token;
    private String username;
    private String userRole;
    private String view;
}
