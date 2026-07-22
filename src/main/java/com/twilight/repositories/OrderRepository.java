package com.twilight.repositories;

import com.twilight.objects.Driver;
import com.twilight.objects.Order;
import com.twilight.types.OrderStatus;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<@NonNull Order,@NonNull Integer> {
    List<Order> findByCustomerMobNo(String mobNo, Pageable pageable);
    Optional<Order> findByRazorpayOrderId(String razorpayOrderId);
    @Modifying
    @Query("""
        UPDATE OrderEntity o
        SET o.status = :status,
            o.driver = :driver
        WHERE o.id = :orderId
        AND o.status = :pending
   """)
    int acceptOrder(
            Integer orderId,
            Driver driver,
            OrderStatus status,
            OrderStatus pending);
}
