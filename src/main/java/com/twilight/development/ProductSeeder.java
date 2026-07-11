package com.twilight.development;
import com.twilight.objects.Product;
import com.twilight.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ProductSeeder {
    private final ProductRepository productRepository;
    public void seed(){};

}
