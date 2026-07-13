package com.twilight.utils.development;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {


    private final MerchantSeeder merchantSeeder;
    private final RestaurantSeeder restaurantSeeder;
    private final OutletSeeder outletSeeder;
    private final ProductSeeder productSeeder;
    private final CustomerSeeder customerSeeder;
    private final DriverSeeder driverSeeder;
    private final OrderSeeder orderSeeder;

    @Override
    public void run(String @NonNull ... args) throws Exception {


        
        merchantSeeder.seed();

        restaurantSeeder.seed();

        outletSeeder.seed();

        productSeeder.seed();

        customerSeeder.seed();

        driverSeeder.seed();

        orderSeeder.seed();

    }
}
