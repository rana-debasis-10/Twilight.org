package com.twilight.endPoints;

import com.twilight.dataTransferObjects.DriverR;
import com.twilight.dataTransferObjects.Jwt;
import com.twilight.dataTransferObjects.MerchantR;
import com.twilight.dataTransferObjects.RestaurantR;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.mappers.MerchantMapper;
import com.twilight.mappers.RestaurantMapper;
import com.twilight.objects.*;
import com.twilight.services.*;
import com.twilight.types.Role;
import com.twilight.utils.UserContext;
import com.twilight.validators.ImageValidator;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountEndpoint {
    @Autowired
    CustomerService customerService;

    @Autowired
    private UserContext user;

    @Autowired
    JwtService jwtService;

    @Autowired
    ManagerService managerService;

    @Autowired
    RestaurantMapper restaurantMapper;
    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    ImageValidator imageValidator;

    @Autowired
    StorageService storageService;

    @Autowired
    MerchantService merchantService;

    @Autowired DriverService driverService;


    @GetMapping("/customer/register")
    @Validated
    @Transactional
    public Jwt createCustomer(@RequestParam(name = "n",required = true)String name){
        String mobNo = user.getMobNo();
        customerService.create(mobNo,name);
        return new Jwt(jwtService.generateToken(mobNo, Role.customer,name));
    }

    @GetMapping("/customer/login")
    @Validated
    @Transactional
    Jwt loadCustomer() {
        String mobNo = user.getMobNo();
        Customer customer =customerService.load(mobNo);
        return new Jwt(jwtService.generateToken(mobNo,Role.customer,customer.getName()));
    }

    @Validated
    @PostMapping(value = "/merchant/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public Jwt createMerchantAndRestaurant(
            @RequestPart("merchant") MerchantR merchantR,
            @RequestPart("restaurant") RestaurantR restaurantR,
            @RequestPart("image") MultipartFile image
    ) throws IOException {

        imageValidator.validateImage(image);

        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantR);
        Merchant merchant = merchantMapper.toMerchant(merchantR);

        String mobNo = user.getMobNo();

        merchant.setMobNo(mobNo);

        restaurant.setImage(storageService.upload(image, "restaurant"));

        merchantService.createMerchant(merchant, restaurant);

        return new Jwt(jwtService.generateToken(mobNo, Role.merchant));
    }

    @Validated
    @PostMapping(value = "/merchant/login")
    @Transactional
    public Jwt loadMerchant(){
        String mobNo = user.getMobNo();
        Merchant merchant = merchantService.getMerchant(mobNo);
        return new Jwt(jwtService.generateToken(mobNo,Role.merchant));
    }

    @GetMapping("/manager/invitations")
    @Validated
    @Transactional
    public List<OutletInvitation> viewInvitation(){
        String mobNo= user.getMobNo();
        return managerService.getInvitation(mobNo);
    }   


    @GetMapping("/manager/invitation/accept")
    @Validated
    @Transactional
    public Jwt acceptInvitation(@RequestParam("i")Integer invitationId) {
        String mobNo = user.getMobNo();
        Integer outletId = managerService.acceptInvitation(mobNo, invitationId);
        return new Jwt(jwtService.generateToken(mobNo, Role.manager, outletId));
    }



    @GetMapping("/manager/login")
    @Transactional
    @Validated
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
    public Jwt registerDriver(@RequestBody DriverR driverR){
        String mobNo = user.getMobNo();
        Driver driver = new Driver(mobNo,driverR.name(),driverR.drivingLicense(), driverR.pan(),driverR.aadhaar(),driverR.bankAccount(),driverR.ifsc());
        driverService.createDriver(driver);
        return new Jwt(jwtService.generateToken(mobNo,Role.driver));

    }

}
