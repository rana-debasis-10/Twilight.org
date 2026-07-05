package com.twilight.dataTransferObjects;

import com.twilight.annotations.MobileNumber;
import com.twilight.objects.Food;
import com.twilight.objects.Restaurant;
import com.twilight.types.OutletStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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
