package com.twilight.objects;

import com.twilight.types.OrderStatus;
import lombok.*;
import com.twilight.utils.annotations.MobileNumber;
import com.twilight.types.PaymentMethod;
import com.twilight.types.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "mob_no")
    @NotNull
    private Customer customer;

    @DecimalMin(value = "0.0")
    private Double total;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private PaymentMethod paymentMethod;

    @Column(unique = true)
    private String razorpayOrderId;


    @Enumerated(value = EnumType.STRING)
    @NotNull
    private OrderStatus status = OrderStatus.outlet_pending;

    @MobileNumber
    @NotNull
    private String deliveryMobNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    @NotNull
    private OrderLocation deliveryLocation;

    @OneToOne
    @JoinColumn(name = "outlet_id")
    private Outlet outlet;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "mob_no")
    private Driver driver;

    double deliveryCharge;

    double outletCharge;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    LocalDateTime updatedAt;

}
