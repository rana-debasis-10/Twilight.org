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
    private final ProductRepo productRepo;
    private final productMapper prodMapper;
    @Autowired
    public ProductService(ProductRepo productRepo, productMapper prodMapper) {
        this.productRepo = productRepo;
        this.prodMapper= prodMapper;
    }



    //Saving the new product to product repo
    public Product saveProduct(ProductDTO productDTO){
        Product product = prodMapper.toProduct(productDTO);
        return productRepo.save(product);
    }

    //Create Product
    public ProductDTO createProduct(ProductDTO productDTO){
        Product product = prodMapper.toProduct(productDTO);
        return prodMapper.toProductDTO(productRepo.save(product));
    }

    //Read Products
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    //Read Products by Id
    public ProductDTO getProductById(Long id){
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        return prodMapper.toProductDTO(product);
    }

    //Update Product
    public ProductDTO updateProduct(long id, ProductDTO productDTO){
        Product product = productRepo.findById(id).orElseThrow(() ->new RuntimeException("product not found"));
        prodMapper.updateEntityFromDTO(productDTO, product);
        return prodMapper.toProductDTO(productRepo.save(product));
    }

    //Delete Product
    public void deleteProduct(long id){
        if(!productRepo.existsById(id)){
            throw new RuntimeException("product not found");
        }

        productRepo.deleteById(id);
    }


}
