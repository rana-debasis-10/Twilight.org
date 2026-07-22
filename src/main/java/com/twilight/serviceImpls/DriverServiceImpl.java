package com.twilight.serviceImpls;

import com.twilight.exceptions.NotFoundException;
import com.twilight.objects.Driver;

import com.twilight.repositories.DriverRepository;
import com.twilight.repositories.OrderRepository;
import com.twilight.services.DriverService;
import com.twilight.types.OrderStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    final DriverRepository driverRepository;

    final OrderRepository orderRepository;

    @Override
    public void create(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void acceptOrder(String mobNo, Integer orderId) {
        Driver driver = driverRepository.findById(mobNo).orElseThrow(NotFoundException::new);
        int success = orderRepository.acceptOrder(orderId,driver, OrderStatus.driver_assigned,OrderStatus.driver_pending);
        if(success==0){
            throw new NotFoundException();
        }
    }
}
