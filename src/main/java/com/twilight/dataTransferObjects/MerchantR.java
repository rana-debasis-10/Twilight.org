package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotBlank;

public record MerchantR(
        @NotBlank
        String email,
        @NotBlank
        String name) {
}
