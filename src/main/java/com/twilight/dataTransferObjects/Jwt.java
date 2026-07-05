package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotBlank;

public record Jwt
   (@NotBlank String token){}

