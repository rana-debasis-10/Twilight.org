package com.twilight.utils.development;
import com.twilight.repositories.OutletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OutletSeeder {
    public final OutletRepository outletRepository;
    public void seed(){};

}
