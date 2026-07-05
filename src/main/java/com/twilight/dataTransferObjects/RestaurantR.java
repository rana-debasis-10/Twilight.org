package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotBlank;


public record RestaurantR(@NotBlank String name, @NotBlank String fssai){
}
