package com.twilight.services;

import com.twilight.objects.Driver;

public interface DriverService {
    void findDriver(String mobNo);
    void createDriver(Driver driver);
    void acceptOrder(String mobNo,Integer orderId);
}
