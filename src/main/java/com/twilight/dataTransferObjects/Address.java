package com.twilight.dataTransferObjects;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Address(
        @NotBlank
        String state,
        @NotBlank
        String city,
        @NotBlank
        String pinCode,
        @NotBlank
        String street,
        String landMark
){}
