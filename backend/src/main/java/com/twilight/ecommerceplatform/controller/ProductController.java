package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.dto.ProductDTO;
import com.twilight.ecommerceplatform.entities.Product;
import com.twilight.ecommerceplatform.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    //Create Product
    @PostMapping//Path to be declared
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO, @RequestParam Long userId) {
        ProductDTO DTO=productService.createProduct(productDTO, userId);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    //Get All Products
    @GetMapping("/viewitem")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProducts());

    }
    //Get Products By Id
    @PostMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    //Get Products by Owner
    @PutMapping("/owwer/{ownerId")
    public ResponseEntity<List<ProductDTO>> getProductByOwnerId(@PathVariable Long userId){
        return ResponseEntity.ok(productService.getProductsByOwner(userId));
    }

    //Update product
    @PostMapping ("/{id}")
    public ResponseEntity<ProductDTO>  updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id, @RequestParam long userId){
        ProductDTO updatedDTO=productService.updateProduct(id, productDTO, userId);
        return ResponseEntity.ok(updatedDTO);
    }

    //Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id, @RequestParam long userId){
        productService.deleteProduct(id, userId);
        return ResponseEntity.noContent().build();
    }


}
