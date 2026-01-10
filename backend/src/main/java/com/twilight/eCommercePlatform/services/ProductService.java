package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.dto.entity.ProductDTO;
import com.twilight.eCommercePlatform.entities.Product;
import com.twilight.eCommercePlatform.mapper.ProductMapper;
import com.twilight.eCommercePlatform.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    //mapping DTO to product
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductMapper prodMapper;

    //Saving the new product to product repo
    public Product saveProduct(ProductDTO productDTO){
        Product product = prodMapper.toProduct(productDTO);
        return productRepo.save(product);
    }
    // Create Product
    public ProductDTO createProduct(ProductDTO productDTO) {

        Product product = prodMapper.toProduct(productDTO);
        return prodMapper.toProductDTO(productRepo.save(product));
    }
    // Read Products
    public List<Product> getAllProducts(int pageNum){
        return productRepo.findAll(PageRequest.of(pageNum,20)).getContent();
    }
    // Search product by name
    public List<Product> getProductByName(String name,int pageNum ){
        return productRepo.findByNameContainingIgnoreCase(name,PageRequest.of(pageNum,20)).getContent();

    }
    // Get product By id
    public Product getProductById(Long id){
        return productRepo.findById(id).orElse(null);
    }
    //Update Product
    public ProductDTO updateProduct(long id, ProductDTO productDTO, long userId) {


        Product product= prodMapper.toProduct(productDTO);
        return prodMapper.toProductDTO(productRepo.save(product));
    }

    // GET PRODUCTS BY OWNER
    public List<ProductDTO> getProductsByOwner(Long userId) {
        return productRepo.findById(userId)
                .stream()
                .map(prodMapper::toProductDTO).toList();
    }

    //Delete Product
    public void deleteProduct(long id,  long userId) {
        productRepo.deleteById(id);
    }


}
