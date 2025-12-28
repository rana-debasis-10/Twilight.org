package com.twilight.ecommerceplatform.entities;

import jakarta.persistence.*;
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

    @NotNull(message = "Please enter number of items")
    private int prodQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownwer_id", nullable= false)
    protected User owner;

}
