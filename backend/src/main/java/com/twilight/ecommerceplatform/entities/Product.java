package com.twilight.ecommerceplatform.entities;

import com.twilight.ecommerceplatform.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodId;

    @NotNull(message = "Please enter a name")
    private String prodName;

    @NotNull(message = "Please enter a category")
    private Category prodCat;

    @NotNull(message = "Please enter a price")
    private Double price;

    @NotNull(message = "Please upload an image")
    private String prodImg;

    public boolean isAvailable;

    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", nullable= false)
    private User ownerId;

}
