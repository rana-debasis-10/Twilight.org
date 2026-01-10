package com.twilight.eCommercePlatform.entities;

import com.twilight.eCommercePlatform.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please enter a name")
    private String name;

    @NotNull(message = "Please enter a category")
    private Category category;

    @NotNull(message = "Please enter a price")
    private Double price;

    @NotNull(message = "Please upload an image")
    private String img;

    public boolean available;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restId")
    private Restaurant restaurant;

}
