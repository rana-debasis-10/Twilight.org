package com.twilight.utils.mappers;

import com.twilight.dataTransferObjects.Address;
import com.twilight.objects.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerAddressMapper {
    @Mapping(target = "customer", ignore = true )
    @Mapping(target = "id",ignore =true)
    public Location toAddress(Address address);

    @Mapping(target = "customerAddress.customer", ignore = true )
    @Mapping(target = "customerAddress.id",ignore =true)
    public Address toAddressR(Location location);
}
