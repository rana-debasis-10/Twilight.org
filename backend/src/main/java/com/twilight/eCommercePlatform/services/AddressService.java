package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.dto.entity.AddressDTO;
import com.twilight.eCommercePlatform.repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    public List<AddressDTO> getAllAddress(int pageNum) {
        return addressRepo.findAll(PageRequest.of(pageNum, 20))
                .getContent()
                .stream()
                .map(AddressDTO::new)
                .toList();
    }
}
