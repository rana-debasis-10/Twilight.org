package com.twilight.ecommerceplatform.services;

import com.twilight.ecommerceplatform.dto.ProductDTO;
import com.twilight.ecommerceplatform.entities.Product;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.enums.UserRole;
import com.twilight.ecommerceplatform.mapper.productMapper;
import com.twilight.ecommerceplatform.repositories.ProductRepo;
import com.twilight.ecommerceplatform.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    //mapping DTO to product
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final productMapper prodMapper;
    @Autowired
    public ProductService(ProductRepo productRepo, productMapper prodMapper, UserRepo userRepo) {
        this.productRepo = productRepo;
        this.prodMapper= prodMapper;
        this.userRepo = userRepo;

    }



    //Saving the new product to product repo
    public Product saveProduct(ProductDTO productDTO){
        Product product = prodMapper.toProduct(productDTO);
        return productRepo.save(product);
    }

    //Create Product
    public ProductDTO createProduct(ProductDTO productDTO, long userId) {
        User owner = userRepo.findByUserId(userId);
        if (owner.getRole() != UserRole.RESTAURENT_OWNER) {
            throw new RuntimeException("Only restaurant owners can create products");
        }
        Product product = prodMapper.toProduct(productDTO);
        product.setOwner(owner);
        return prodMapper.toProductDTO(productRepo.save(product));
    }

    //Read Products
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    //Read Products by Id
    public ProductDTO getProductById(Long id){
        return productRepo.findById(id).map(prodMapper::toProductDTO).orElseThrow(() -> new RuntimeException("product not found"));

    }

    //Update Product
    public ProductDTO updateProduct(long id, ProductDTO productDTO, long userId) {
        Product product = productRepo.findById(id).orElseThrow(() ->new RuntimeException("product not found"));

        User user = userRepo.findById(userId).orElseThrow(() ->new RuntimeException("user not found"));

        if (user.getRole() != UserRole.RESTAURENT_OWNER){
            throw new RuntimeException("Only restaurant owners can update products");
        }

        if(!product.getOwner().getUserId().equals(userId)){
            throw new RuntimeException("Only restaurant owners can update products");
        }

        prodMapper.updateEntityFromDTO(productDTO, product);
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
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        if (user.getRole() != UserRole.RESTAURENT_OWNER){
            throw new RuntimeException("Only restaurant owners can delete products");
        }
        if (!product.getOwner().getUserId().equals(userId)){
            throw new RuntimeException("Only restaurant owners can delete products");
        }

        productRepo.deleteById(id);
    }


}
