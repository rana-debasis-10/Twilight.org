package com.twilight.dataTransferObjects;
import com.twilight.types.Label;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Location(
        double latitude,
        double longitude,
        @NotBlank String description,
        Label label
){}
