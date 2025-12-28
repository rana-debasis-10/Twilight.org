package com.twilight.ecommerceplatform.dto;


import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.enums.Currency;
import com.twilight.ecommerceplatform.enums.DeliveryStatus;
import com.twilight.ecommerceplatform.enums.PaymentStatus;
import java.time.Instant;

///////////////This Class for response as viewing order details ////////////////////
///////////////that are already made and added to database for the user.////////////
///////////////////////////////////////////////////////////////////////////////////
public class OrderEntityResponseDTO {
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