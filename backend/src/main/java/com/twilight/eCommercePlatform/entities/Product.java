package com.twilight.eCommercePlatform.entities;

import com.twilight.eCommercePlatform.dto.entity.ProductDTO;
import com.twilight.eCommercePlatform.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please enter a name")
    private String name;

    @NotNull(message = "Please enter a category")
    @Enumerated (EnumType.STRING)
    private Category category;

    @NotNull(message = "Please enter a price")
    private Double price;

    private String img;

    public boolean available= true;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    public Product(ProductDTO dto){
        this.setName( dto.getName() );
        this.setCategory( dto.getCategory() );
        this.setPrice( dto.getPrice() );
        this.setImg( dto.getImg() );
        this.setDescription(dto.getDescription());
        this.setAvailable( true );
    }
    public static void updateProduct(Product product,ProductDTO dto){
        if(dto.getName()!=null){
            product.setName(dto.getName());
        }
        if(dto.getImg()!=null){
            product.setName(dto.getImg());
        }
        if(dto.getPrice()!=null){
            product.setName(dto.getName());
        }
        if(dto.getDescription()!=null){
            product.setDescription(dto.getDescription());
        }
        if (dto.getCategory()!=null){
            product.setCategory(dto.getCategory());
        }
    }
}
