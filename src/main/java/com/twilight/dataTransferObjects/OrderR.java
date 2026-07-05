package com.twilight.dataTransferObjects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderR(
        @NotNull
        Integer outletId,
        @NotNull
        List<ItemR>foods,
        @NotNull
        Address address,
        @NotBlank
        @MobileNumber String deliveryMobNo,
        @NotNull PaymentMethod paymentMethod
){}
