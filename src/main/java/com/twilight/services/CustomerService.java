package com.twilight.services;

import com.twilight.objects.User;
import com.twilight.utils.annotations.MobileNumber;
import jakarta.validation.constraints.NotBlank;

public interface CustomerService {
    User create(@MobileNumber String mobNo, @NotBlank String name);
}
