package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductR(
        @NotBlank
        String name,
        @NotNull
        Double price ,
        @NotBlank
        String imageFileName
){}
