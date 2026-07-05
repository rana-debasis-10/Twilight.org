package com.twilight.dataTransferObjects;

import com.twilight.types.OutletStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record OutletR (
        @NotNull Integer outletId ,
        @NotBlank
        String restaurantName,
        @NotBlank
        String restaurantImage ,
        @NotNull
        OutletStatus outletStatus,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude
)implements Serializable{}
