package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.dto.entity.ProductDTO;
import com.twilight.eCommercePlatform.entities.Product;
import com.twilight.eCommercePlatform.repositories.ProductRepo;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    RestaurantOwnerService restaurantOwnerService;

    @Autowired
    RestaurantService restaurantService;

    public ProductDTO saveProduct(ProductDTO productDTO){
        Product product =new Product(productDTO);
        productRepo.save(product);
        return productDTO;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        try {
            Product product = new Product(productDTO);
            productRepo.save(product);
        }
        catch (RuntimeException e) {
            return null;
        }
        return productDTO;
    }

    public List<ProductDTO> getAllProducts(int pageNum){
        return productRepo.findAll(PageRequest.of(pageNum, 20))
                .getContent()
                .stream()
                .map(ProductDTO::new)
                .toList();
    }

    public List<Product> getProductByName(String name,int pageNum ){
        return productRepo.findByNameContainingIgnoreCase(name,PageRequest.of(pageNum,20)).getContent();

    }

    public ProductDTO getProductById(Long id){
        try{
        return new ProductDTO(productRepo.findById(id).orElse(null));}
        catch (NullPointerException e){
            return null;
        }
    }
    public Product getProduct(Long id){
        return productRepo.findById(id).orElse(null);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Product> product = productRepo.findByIdAndRestaurantId(id,restaurantService.getByEmail(userDetails.getEmail()).getId());
        if(product.isPresent()){
            Product.updateProduct(product.get(),productDTO);
            productRepo.save(product.get());
            return productDTO;
        }
        return null;
    }

    public List<ProductDTO> getProductsByOwner(Long userId) {
        return productRepo.findById(userId)
                .stream()
                .map(ProductDTO::new)
                .toList();
    }

    //Delete Product
    public void deleteProduct(long id,  long userId) {
        productRepo.deleteById(id);
    }


}
