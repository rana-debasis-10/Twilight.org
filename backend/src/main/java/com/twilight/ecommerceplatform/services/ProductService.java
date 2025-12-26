package com.twilight.ecommerceplatform.services;

import com.twilight.ecommerceplatform.DataToObjects.ProductDTO;
import com.twilight.ecommerceplatform.entities.Product;
import com.twilight.ecommerceplatform.mapper.productMapper;
import com.twilight.ecommerceplatform.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    //mapping DTO to product
    private final productMapper prodMapper;
    @Autowired
    public ProductService(productMapper prodMapper) {

        this.prodMapper = prodMapper;
    }

    //Saving the new product to product repo
    public Product saveProduct(ProductDTO productDTO){
        Product product = prodMapper.toProduct(productDTO);
        return productRepo.save(product);
    }

    private ProductRepo productRepo;
    public List<Long> getProductPrices(List<Long> prodIds){
        return productRepo.findPriceByIds(prodIds);
    }
    public List<Product> getProducts(List<Long> prodIds) {
        return productRepo.findAllById(prodIds);
    }
    public Product getProductById(Long prodId){
        return productRepo.findById(prodId).orElse(null);
    }
}
