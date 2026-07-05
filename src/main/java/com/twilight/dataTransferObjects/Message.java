package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotBlank;

public record Message(@NotBlank String message) {
}
