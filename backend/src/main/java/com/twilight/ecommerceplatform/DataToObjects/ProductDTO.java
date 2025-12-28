package com.twilight.ecommerceplatform.DataToObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String prodId;
    private String prodName;

    private String price;
    private String prodCat;
    private String prodQty;

    private long ownerId;
}
