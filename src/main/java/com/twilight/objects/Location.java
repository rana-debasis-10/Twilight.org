package com.twilight.objects;

import com.twilight.types.Label;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)

public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    Label label;

    @NotNull
    String description;

    @NotNull
    @DecimalMin(value = "0.0")
    Double latitude;

    @NotNull
    @DecimalMin(value = "0.0")
    Double longitude;

    @ManyToOne
    @JoinColumn(name = "mob_no")
    private Customer customer;

    @OneToOne(mappedBy = "location")
    private Outlet outlet;

    @OneToOne(mappedBy = "deliveryLocation")
    private Order order;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    LocalDateTime updatedAt;
}
