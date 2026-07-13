package com.twilight.utils.mappers;

import com.twilight.dataTransferObjects.CustomerR;
import com.twilight.objects.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = CustomerAddressMapper.class)
public interface CustomerMapper {
    @Mapping(target = "customer.orders", ignore = true)

    public CustomerR toCustomerR(Customer customer);
}
