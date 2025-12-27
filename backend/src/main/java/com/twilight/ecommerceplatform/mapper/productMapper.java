package com.twilight.ecommerceplatform.mapper;

import com.twilight.ecommerceplatform.DataToObjects.ProductDTO;
import com.twilight.ecommerceplatform.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface productMapper {

    Product  toProduct(ProductDTO productDTO);
    ProductDTO toProductDTO(Product product);

    void updateEntityFromDTO(ProductDTO dto,
                             @MappingTarget Product product);

}
