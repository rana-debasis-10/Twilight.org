package com.twilight.dataTransferObjects;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.types.OutletStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OutletDetailed(
        @NotNull
        Integer id,
        @NotBlank
        String restaurantName,
        @NotBlank
        String restaurantImage,
        @NotNull
        OutletStatus outletStatus,

        @NotNull Double longitude,
        @NotNull Double latitude,
        @MobileNumber
        @NotNull
        String managerMobNo
){}
