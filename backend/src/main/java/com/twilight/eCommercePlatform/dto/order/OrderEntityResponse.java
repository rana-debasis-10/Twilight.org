package com.twilight.eCommercePlatform.dto.order;

import com.twilight.eCommercePlatform.entities.Address;
import com.twilight.eCommercePlatform.enums.Currency;
import com.twilight.eCommercePlatform.enums.DeliveryStatus;
import com.twilight.eCommercePlatform.enums.PaymentStatus;
import java.time.Instant;

/// For viewing order from database
public class OrderEntityResponse {
    /// Database Order Id
    private long orderId;

    /// Amount of the order
    private long amount;

    /// Currency in which order is placed
    private Currency currency;

    /// Payment Status of the Payment
    private PaymentStatus paymentStatus;

    /// Delivery Status
    private DeliveryStatus deliveryStatus;

    /// Receipt of the order generated
    private String receipt;

    /// Time when the order is generated
    private Instant timeStamp;

    /// Mobile Number of receiver
    private String mobNo;
    /// Delivery Address of the Receiver
    private Address address;
}