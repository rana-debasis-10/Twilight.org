package com.twilight.eCommercePlatform.dto.entity;

import com.twilight.eCommercePlatform.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String prodName;
    private Double price;
    private Category prodCat;
    private String prodImg;
    private String description;
}
