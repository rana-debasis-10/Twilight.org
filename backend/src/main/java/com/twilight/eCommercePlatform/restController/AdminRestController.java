package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.entity.AddressDTO;
import com.twilight.eCommercePlatform.dto.entity.ProductDTO;
import com.twilight.eCommercePlatform.dto.entity.RestaurantDTO;
import com.twilight.eCommercePlatform.dto.entity.UserDTO;
import com.twilight.eCommercePlatform.entities.*;
import com.twilight.eCommercePlatform.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/orders/{pageNum}")
    public List<OrderEntity> getAllOrder(@PathVariable int pageNum){
        return orderService.getAllOrder(pageNum);
    }
    @GetMapping("/users/{pageNum}")
    public List<UserDTO> getAllUser(@PathVariable int pageNum){
        return userService.getAllUser(pageNum);
    }
    @GetMapping("/addresses/{pageNum}")
    public List<AddressDTO> getAllAddress(@PathVariable int pageNum){
        return addressService.getAllAddress(pageNum);
    }
    @GetMapping("/restaurants/{pageNum}")
    public List<RestaurantDTO> getAllRestaurant(@PathVariable int pageNum){
        return restaurantService.getAllRestaurant(pageNum);
    }
    @GetMapping("/products/{pageNum}")
    public List<ProductDTO> getAllProduct(@PathVariable int pageNum){
        return productService.getAllProducts(pageNum);
    }
}
