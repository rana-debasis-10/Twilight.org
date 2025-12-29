package com.twilight.ecommerceplatform.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    String email;
    String password;
    String role;

}
