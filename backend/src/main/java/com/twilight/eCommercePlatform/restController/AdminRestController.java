package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.entities.*;
import com.twilight.eCommercePlatform.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/admin")
public class AdminRestController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/orders/{pageNum}")
    public List<OrderEntity> getAllOrder(@PathVariable int pageNum){
        return orderService.getAllOrder(pageNum);
    }
    @GetMapping("/users/{pageNum}")
    public List<User> getAllUser(@PathVariable int pageNum){
        return userService.getAllUser(pageNum);
    }
    @GetMapping("/addresses/{pageNum}")
    public List<Address> getAllAddress(@PathVariable int pageNum){
        return addressService.getAllAddress(pageNum);
    }
    @GetMapping("/restaurants/{pageNum}")
    public List<Restaurant> getAllRestaurant(@PathVariable int pageNum){
        return restaurantService.getAllRestaurant(pageNum);
    }
}
