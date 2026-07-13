package com.twilight.dataTransferObjects;

import com.twilight.utils.annotations.MobileNumber;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CustomerR(
        @NotBlank @MobileNumber String mobNo,
        @NotBlank String name,
        List<Address> addresses
) {}
