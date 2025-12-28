package com.twilight.ecommerceplatform.mapper;

import com.twilight.ecommerceplatform.dto.ProductDTO;
import com.twilight.ecommerceplatform.entities.Product;
import com.twilight.ecommerceplatform.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // DTO → Entity
    @Mapping(target = "ownerId", expression = "java(map(dto.getOwnerId()))")
    Product toProduct(ProductDTO dto);

    // Entity → DTO
    @Mapping(source = "ownerId.userId", target = "ownerId")
    ProductDTO toProductDTO(Product product);

    // Update existing entity
    @Mapping(target = "ownerId", ignore = true)
    void updateEntityFromDTO(ProductDTO dto,
                             @MappingTarget Product product);

    // 🔥 Custom mapper method (Long → User)
    default User map(Long ownerId) {
        if (ownerId == null) {
            return null;
        }
        User user = new User();
        user.setUserId(ownerId); // FK reference only
        return user;
    }
}
