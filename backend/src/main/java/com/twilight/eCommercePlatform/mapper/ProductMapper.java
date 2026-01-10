package com.twilight.eCommercePlatform.mapper;

import com.twilight.eCommercePlatform.dto.entity.ProductDTO;
import com.twilight.eCommercePlatform.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true) // set in service layer
    @Mapping(target = "available", constant = "true")
    Product toProduct(ProductDTO dto);

    // Entity → DTO
    ProductDTO toProductDTO(Product product);

    // Update existing entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "available", ignore = true)
    void updateEntityFromDTO(ProductDTO dto,
                             @MappingTarget Product product);
}
