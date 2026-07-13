package com.twilight.objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    @Max(5)
    private Integer quantity;

    @DecimalMin(value = "0.0")
    private Double price;

    @DecimalMin(value = "0.0")
    private Double subtotal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;


}

