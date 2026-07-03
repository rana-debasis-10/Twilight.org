package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;
public record DriverR(
        @NotNull String name,
        @NotNull String drivingLicense,
        @NotNull String pan,
        @NotNull String aadhaar,
        @NotNull String bankAccount,
        @NotNull String ifsc) {
}