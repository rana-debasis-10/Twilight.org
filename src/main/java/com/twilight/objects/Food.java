package com.twilight.objects;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.value.qual.DoubleVal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    @Min(0)
    private double price;

    @NotNull
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "outlet_id")
    @NotNull
    private Outlet outlet;

}
