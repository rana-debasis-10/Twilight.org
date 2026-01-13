package com.twilight.eCommercePlatform.dto.entity;

import com.twilight.eCommercePlatform.entities.Product;
import com.twilight.eCommercePlatform.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private Double price;
    private Category category;
    private String img;
    private String description;

    public ProductDTO(Product product) throws NullPointerException{
        if(product == null){
            throw new NullPointerException("Product Not Found");
        }
        this.setName( product.getName() );
        this.setPrice( product.getPrice() );
        this.setCategory( product.getCategory() );
        this.setImg( product.getImg() );
        this.setDescription( product.getDescription() );
    }
    public static void updateDTO(ProductDTO dto, Product product){
        if ( dto == null ) {
            return;
        }
        dto.setName( product.getName() );
        dto.setCategory( product.getCategory() );
        dto.setPrice( product.getPrice() );
        dto.setImg( product.getImg() );
        dto.setDescription( product.getDescription() );
    }
}
