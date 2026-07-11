package com.twilight.development;
import com.twilight.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class DriverSeeder {
    private final DriverRepository driverRepository;
    public void seed(){};

}
