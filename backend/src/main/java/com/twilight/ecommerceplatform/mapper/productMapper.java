package com.twilight.ecommerceplatform.mapper;

import com.twilight.ecommerceplatform.dto.ProductDTO;
import com.twilight.ecommerceplatform.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;



@Mapper(componentModel = "spring")
public interface productMapper {

    @Mapping(source = "owner_id", target = "owmer_Id")
    Product  toProduct(ProductDTO productDTO);

    @Mapping(target = "omner", ignore = true)
    ProductDTO toProductDTO(Product product);

    void updateEntityFromDTO(ProductDTO dto,
                             @MappingTarget Product product);

}
