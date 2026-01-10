package com.twilight.eCommercePlatform.utility;

import com.twilight.eCommercePlatform.dto.order.OrderRequestDTO;
import com.twilight.eCommercePlatform.entities.OrderItem;
import com.twilight.eCommercePlatform.entities.Product;
import com.twilight.eCommercePlatform.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/// Converter class is to update Data to Object to Database Objects
@Component
public class Converter {

    @Autowired
    ProductService productService;

    /// Make product Item to orderItem adding its quantities
    public static OrderItem productToOrderItem(Product product){
        OrderItem orderItem = new OrderItem();
        orderItem.setProdId(product.getId());
        orderItem.setPrice(product.getPrice());
        orderItem.setProdName(product.getName());
        return orderItem;
    }

    ///  Convert Product to OrderItems
    public List<OrderItem>ToOrderItems(OrderRequestDTO orderDetails) throws Exception {
        List<Product> products = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : orderDetails.getProdIdAndQuantity().entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();

            Product product = productService.getProductById(productId);
            if (product == null) {
                continue;
            }

            products.add(product);
            quantities.add(quantity);
        }


        List<OrderItem> orderItems = new ArrayList<>(0);
        OrderItem orderItem;

        for(int i = 0; i < products.size(); i++){
           if(products.get(i).isAvailable()) {
                orderItem = productToOrderItem(products.get(i));
                orderItem.setQuantity(quantities.get(i));
                orderItem.setSubtotal(orderItem.getPrice() * orderItem.getQuantity());

                orderItems.add(orderItem);
            }
        }

        return orderItems;
    }


}

