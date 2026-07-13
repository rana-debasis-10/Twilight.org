package com.twilight.utils.mappers;

import com.twilight.dataTransferObjects.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderAddressMapper {
    @Mapping(target = "orderAddress.order", ignore = true)
    @Mapping(target = "orderAddress.id",ignore =true)
    public Address toAddressR(OrderAddress orderAddress);

    @Mapping(target ="order", ignore =true)
    @Mapping(target = "id",ignore =true)

    OrderAddress toAddress(Address address);
}
