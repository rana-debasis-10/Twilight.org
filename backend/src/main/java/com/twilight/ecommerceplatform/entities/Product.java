package com.twilight.ecommerceplatform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prodId;

    @NotNull(message = "Please enter a name")
    private String prodName;

    @NotNull(message = "Please enter a category")
    private String prodCat;

    @NotNull(message = "Please enter a price")
    private int price;
    private String fileName;
    private String filePath;
}
