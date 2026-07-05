package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record MenuUpdateR(
        @NotNull Integer productId,
        @NotNull Integer restaurantId
)implements Serializable {}
