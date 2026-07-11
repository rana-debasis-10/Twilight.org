package com.twilight.development;
import com.twilight.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerSeeder {
    public final CustomerRepository customerRepository;
    public void seed(){};
}
