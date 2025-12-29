package com.twilight.ecommerceplatform.DataToObjects;

import lombok.Data;
import org.mapstruct.Mapping;

@Data
public class LoginRequestDTO {
    String email;
    String password;
    String role;

}
