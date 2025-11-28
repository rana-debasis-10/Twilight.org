package com.twilight.ecommerceplatform.utility;

import com.twilight.ecommerceplatform.DataToObjects.AddressDTO;
import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.entities.OrderItem;
import com.twilight.ecommerceplatform.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static OrderItem orderItemToProduct(Product product){
        OrderItem orderItem = new OrderItem();
        orderItem.setProdId(product.getProdId());
        orderItem.setPrice(product.getPrice());
        orderItem.setProdImage(product.getProdImage());
        orderItem.setProdName(product.getProdName());
        return orderItem;
    }

    public static List<OrderItem> orderItemToProduct(List<Product> products, List<Integer> quantities){
        List<OrderItem> orderItems = new ArrayList<>(0);
        OrderItem orderItem;

        for(int i = 0; i < products.size(); i++){
            orderItem = orderItemToProduct(products.get(i));
            orderItem.setQuantity(quantities.get(i));
            orderItem.setSubtotal(orderItem.getPrice() * orderItem.getQuantity());

            orderItems.add(orderItem);   // ✅ FIXED (missing add)
        }

        return orderItems;
    }

    public static Address addressDtoToAddress(AddressDTO addressDTO){
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setState(addressDTO.getState());
        address.setLandMark(addressDTO.getLandMark());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setStreet(addressDTO.getStreet());   // ✅ FIXED (was using address.getStreet())
        address.setPrimaryAddress(false);

        return address;
    }
}

