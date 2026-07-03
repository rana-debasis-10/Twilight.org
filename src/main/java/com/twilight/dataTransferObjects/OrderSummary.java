package com.twilight.dataTransferObjects;

import com.twilight.objects.OrderAddress;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderSummary implements Serializable {
    Integer orderId;
    String deliveryMobNo;
    OrderAddress deliveryLocation ;
    Double pickupLatitude;
    Double pickupLongitude;
}
