package com.twilight.ecommerceplatform.dto;

import com.twilight.ecommerceplatform.enums.UserRole;
import lombok.Data;

@Data
public class LoginRequestDTO {
    String email;
    String password;
    UserRole role;

}
