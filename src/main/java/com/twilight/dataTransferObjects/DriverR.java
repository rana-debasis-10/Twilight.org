package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotBlank
;
public record DriverR(
        @NotBlank
         String name,
        @NotBlank
         String drivingLicense,
        @NotBlank
         String pan,
        @NotBlank
         String aadhaar,
        @NotBlank
         String bankAccount,
        @NotBlank
         String ifsc) {
}