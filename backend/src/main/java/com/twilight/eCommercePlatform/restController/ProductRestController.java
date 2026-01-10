package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.entity.ProductDTO;
import com.twilight.eCommercePlatform.entities.Product;
import com.twilight.eCommercePlatform.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ProductRestController {
    private final ProductService productService;
    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;

    }

    //Create Product
    @PostMapping//Path to be declared
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO DTO=productService.createProduct(productDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    //Get All Products
    @GetMapping("/viewItem/{pageNum}")
    public ResponseEntity<List<Product>> getAllProduct(@PathVariable int pageNum){
        return ResponseEntity.ok(productService.getAllProducts(pageNum));

    }
    //Get Products By ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }


    //Update product
    @PutMapping("/{id}")
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
