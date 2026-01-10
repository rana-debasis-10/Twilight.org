package com.twilight.eCommercePlatform.mapper;

import com.twilight.eCommercePlatform.dto.entity.AddressDTO;
import com.twilight.eCommercePlatform.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping( target = "id" ,ignore = true)
    Address toAddress(AddressDTO dto);
    AddressDTO toAddressDTO(Address address);
}
