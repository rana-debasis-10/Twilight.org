package com.twilight.utils.mappers;

import com.twilight.dataTransferObjects.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "label", ignore = true)
    Location toLocation(com.twilight.objects.Location location);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "label", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    com.twilight.objects.Location toLocation(Location location);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "label", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "order", ignore = true)
    com.twilight.objects.OrderLocation toOrderLocation(Location location);

}