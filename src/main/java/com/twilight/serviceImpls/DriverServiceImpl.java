package com.twilight.serviceImpls;

import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.Driver;
import com.twilight.repositories.DriverRepository;
import com.twilight.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;

public class DriverServiceImpl implements DriverService {


    @Autowired
    DriverRepository driverRepository;

    /**
     * Drivers mobile number is needed
     **/
    @Override
    public void findDriver(String mobNo) {
        driverRepository.findById(mobNo).orElseThrow(()->new NotFoundException("Driver already Exists","Driver does not exist"));
    }


    /**
     * //@param Driver Object is needed
     **/
    @Override
    public void createDriver(Driver driver) {
        driverRepository.findById(driver.getMobNo()).ifPresent((driverDb)->{throw new UnAuthorizedException("Driver Exists"," Driver Exists");});
        driverRepository.save(driver);
    }

    /**
     *  Delivery partner mobile number
     * Order ID of the order that is to be accepted
     *
     */
    @Override
    public void acceptOrder(String mobNo, Integer orderId) {

    }
}
