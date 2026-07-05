package com.twilight.dataTransferObjects;


import jakarta.validation.constraints.Min;
import org.checkerframework.checker.units.qual.min;

import jakarta.validation.constraints.NotNull;

public record Location(@NotNull Double latitude, @NotNull Double longitude){}
