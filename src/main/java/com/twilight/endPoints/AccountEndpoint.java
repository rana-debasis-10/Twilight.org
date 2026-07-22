package com.twilight.endPoints;

import com.twilight.dataTransferObjects.DriverR;
import com.twilight.dataTransferObjects.Jwt;
import com.twilight.dataTransferObjects.MerchantR;
import com.twilight.dataTransferObjects.RestaurantR;
import com.twilight.utils.annotations.Image;
import com.twilight.utils.mappers.MerchantMapper;
import com.twilight.utils.mappers.RestaurantMapper;
import com.twilight.objects.*;
import com.twilight.services.*;
import com.twilight.types.Role;
import com.twilight.security.UserContext;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/account")
@Validated
@AllArgsConstructor
public class AccountEndpoint {
    private  CustomerService customerService;

    private UserContext user;

    private  JwtService jwtService;

    private ManagerService managerService;

    private  RestaurantMapper restaurantMapper;

    private  MerchantMapper merchantMapper;

    private  StorageService storageService;

    private  MerchantService merchantService;

    private  DriverService driverService;




    @GetMapping("/customer/register")
    @Transactional
    public Jwt createCustomer(@RequestParam(name = "n")@NotBlank String name){
        String mobNo = user.getMobNo();
        customerService.create(mobNo,name);
        return new Jwt(jwtService.generateToken(mobNo, Role.customer,name));
    }

    @GetMapping("/customer/login")
    @Transactional
    Jwt loadCustomer() {
        String mobNo = user.getMobNo();
        Customer customer =customerService.load(mobNo);
        return new Jwt(jwtService.generateToken(mobNo,Role.customer,customer.getName()));
    }

    @PostMapping(value = "/merchant/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public Jwt createMerchantAndRestaurant(
            @RequestPart("merchant")@Valid MerchantR merchantR,
            @RequestPart("restaurant")@Valid RestaurantR restaurantR,
            @RequestPart("image")@Valid @Image MultipartFile image
    ) throws IOException {

        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantR);
        Merchant merchant = merchantMapper.toMerchant(merchantR);
        String mobNo = user.getMobNo();

        merchant.setMobNo(mobNo);

        String key = storageService.generateKey("restaurants", image.getOriginalFilename());

        restaurant.setImage(key);

        merchantService.create(merchant, restaurant);

        storageService.upload(image,key);

        return new Jwt(jwtService.generateToken(mobNo, Role.merchant));
    }


    @PostMapping(value = "/merchant/login")
    @Transactional
    public Jwt loadMerchant(){
        String mobNo = user.getMobNo();
        merchantService.getMerchant(mobNo);
        return new Jwt(jwtService.generateToken(mobNo,Role.merchant));
    }


    @GetMapping("/manager/invitations")
    @Transactional
    public List<OutletInvitation> viewInvitation(){
        String mobNo= user.getMobNo();
        return managerService.getInvitation(mobNo);
    }   


    @GetMapping("/manager/invitation/accept")
    @Transactional
    public Jwt acceptInvitation(@RequestParam("i" )Integer invitationId) {
        String mobNo = user.getMobNo();
        Integer outletId = managerService.acceptInvitation(mobNo, invitationId);
        return new Jwt(jwtService.generateToken(mobNo, Role.manager, outletId));
    }



    @GetMapping("/manager/login")
    @Transactional
    public Jwt managerLogin(){
        String mobNo = user.getMobNo();
        Integer outletId = managerService.findLinkedOutlet(mobNo);
        return new Jwt(jwtService.generateToken(mobNo, Role.manager, outletId));
    }

    
    @GetMapping("/driver/login")
    @Transactional
    public Jwt driverLogin(){
        String mobNo = user.getMobNo();
        driverService.findDriver(mobNo);
        String jwt = jwtService.generateToken(mobNo,Role.driver);
        return new Jwt(jwt);
    }


    @GetMapping("/driver/register")
    @Transactional
    public Jwt registerDriver(@RequestBody @Valid DriverR driverR){
        String mobNo = user.getMobNo();
        Driver driver = new Driver(mobNo,driverR.name(),driverR.drivingLicense(), driverR.pan(),driverR.aadhaar(),driverR.bankAccount(),driverR.ifsc());
        driverService.create(driver);
        return new Jwt(jwtService.generateToken(mobNo,Role.driver));

    }

}
