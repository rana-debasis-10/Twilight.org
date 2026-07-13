package com.twilight.dataTransferObjects;

import com.twilight.utils.annotations.MobileNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
public record OrderSummary (
        @NotNull
    Integer orderId,
    @NotBlank
    @MobileNumber
    String deliveryMobNo,

    @NotNull
    OrderAddress deliveryLocation ,
    @NotNull
    Double pickupLatitude,
    @NotNull
    Double pickupLongitude) implements Serializable {
}
