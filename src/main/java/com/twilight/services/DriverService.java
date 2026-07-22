package com.twilight.services;

import com.twilight.objects.Driver;

public interface DriverService {
    void create(Driver driver);
    void acceptOrder(String mobNo,Integer orderId);
}
