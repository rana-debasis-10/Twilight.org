package com.twilight.services;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.objects.Customer;
import com.twilight.objects.Location;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;

public interface CustomerService {
    Customer load(@NotNull @MobileNumber String mobNo);
    void create(@NotNull @MobileNumber String mobNo, @NotNull String name);
    void addAddressAsType(@NonNull Customer customer, @NotNull Location address);
}
