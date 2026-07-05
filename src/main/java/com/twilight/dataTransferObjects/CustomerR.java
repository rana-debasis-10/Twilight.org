package com.twilight.dataTransferObjects;

import com.twilight.annotations.MobileNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CustomerR(
        @NotBlank @MobileNumber String mobNo,
        @NotBlank String name,
        List<Address> addresses
) {}
