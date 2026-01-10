package com.twilight.eCommercePlatform.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private Long prodId;
    private String prodName;
    private double price;
    private String prodImage;
    private int quantity;
    private double subtotal;
}
