package com.twilight.ecommerceplatform.services;

import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/// ////////////////////////////////////////////////////////////////////////
///For any type of address related query and address related database change
/// ////////////////////////////////////////////////////////////////////////


@Service
public class AddressService {
    ///  Repo for database connection and ORM
    @Autowired
    private AddressRepo addressRepo;

    /// Finding address by address ID
    public Address getById(Long addressId){
        return addressRepo.findById(addressId).orElse(null);
    }

    /// Finding address by user ID
    public Address getByUserId(long userId){
        return addressRepo.findByUserId(userId);
    }
}
