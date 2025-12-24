package com.twilight.ecommerceplatform.services;

import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;
    public Address getAddress(Long addressId){
        return addressRepo.findById(addressId).orElse(null);
    }
}
