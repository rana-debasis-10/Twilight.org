package com.twilight.ecommerceplatform.utility;

import com.twilight.ecommerceplatform.DataToObjects.AddressDTO;
import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.entities.OrderItem;
import com.twilight.ecommerceplatform.entities.Product;

import java.util.ArrayList;
import java.util.List;
/// ///////////////////////////////////////////////////////////////
/// Converter class is to update Data to Object to Database Objects
/// ///////////////////////////////////////////////////////////////


public class Converter {

    /// Make product Item to orderItem adding its quantities (For single product)
    public static OrderItem productToOrderItem(Product product){
        OrderItem orderItem = new OrderItem();
        orderItem.setProdId(product.getProdId());
        orderItem.setPrice(product.getPrice());
        orderItem.setProdName(product.getProdName());
        return orderItem;
    }

    ///  Convert Product to OrderItems//////////////////////////////////////////
    public static List<OrderItem> productsToOrderItems(List<Product> products, List<Integer> quantities){
        List<OrderItem> orderItems = new ArrayList<>(0);
        OrderItem orderItem;

        for(int i = 0; i < products.size(); i++){
           if(products.get(i).isAvailable) {
                orderItem = productToOrderItem(products.get(i));
                orderItem.setQuantity(quantities.get(i));
                orderItem.setSubtotal(orderItem.getPrice() * orderItem.getQuantity());

                orderItems.add(orderItem);
            }
        }

        return orderItems;
    }

    /// Updating AddressDTO to Address/////////////////////////////////////////
    public static Address addressDtoToAddress(AddressDTO addressDTO){
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setState(addressDTO.getState());
        address.setLandMark(addressDTO.getLandMark());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setStreet(addressDTO.getStreet());
        address.setPrimaryAddress(false);

        return address;
    }
}

