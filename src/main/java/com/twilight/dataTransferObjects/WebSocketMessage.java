package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record WebSocketMessage (
    @NotBlank String message,
    @NotNull
    Object payload)
implements Serializable {
}
