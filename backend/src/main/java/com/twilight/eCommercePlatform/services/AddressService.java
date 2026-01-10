package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.dto.entity.AddressDTO;
import com.twilight.eCommercePlatform.entities.Address;
import com.twilight.eCommercePlatform.mapper.AddressMapper;
import com.twilight.eCommercePlatform.repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private AddressMapper addressMapper;


    public List<Address> getAllAddress(int pageNum) {
        return addressRepo.findAll(PageRequest.of(pageNum,10)).getContent();
    }
    public AddressDTO getAddressById(Long addressId){
        return addressMapper.toAddressDTO(addressRepo.findById(addressId).orElse(null));
    }
}
