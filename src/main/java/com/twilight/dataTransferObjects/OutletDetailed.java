package com.twilight.dataTransferObjects;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.types.OutletStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface OutletDetailed {
        Integer getId();
        String getName();
        String getImage();
        OutletStatus getStatus();
        Double getLongitude();
        Double getLatitude();
        String getManagerMobNo();
}
