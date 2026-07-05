package com.twilight.endPoints;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.*;

import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.mappers.ProductMapper;
import com.twilight.mappers.RestaurantMapper;
import com.twilight.objects.OutletInvitation;
import com.twilight.objects.Product;
import com.twilight.services.*;
import com.twilight.utils.UserContext;

import com.twilight.validators.ImageValidator;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurant")
@Validated
@RequiredArgsConstructor
public class RestaurantEndpoints {
     private Location location;

    private   ProductMapper productMapper;

    private   MerchantService merchantService;

    private   UserContext userContext;

    private   StorageService storageService;

    private   ImageValidator imageValidator;



    private   LocationService geoCoding;

    private   RestaurantMapper restaurantMapper;


    @PostMapping("/create/outlet")
    @Transactional
    @Validated
    public void create(@RequestBody Address address) throws UnAuthorizedException{
        String mobNo = userContext.getMobNo();

        Location location = geoCoding.getLocation(address);
        merchantService.createOutlet(mobNo, location);
    }
    @PostMapping("/outlet/invite")
    @Transactional
    @Validated
    public OutletInvitation inviteManager(@MobileNumber @RequestParam("m") String inviteeMobNo, @RequestParam("o") Integer outletId){
        String merchantMobNo = userContext.getMobNo();
        return merchantService.invite(merchantMobNo,inviteeMobNo,outletId);
    }
    @GetMapping("/view")
    @Transactional
    @Validated
    public RestaurantR showRestaurant(){
        String merchantMobNo = userContext.getMobNo();
        return restaurantMapper.toDto(merchantService.findRestaurantByMobNo(merchantMobNo));
    }

    @PostMapping("/outlet/invite/other")
    @Transactional
    @Validated
    public OutletInvitation inviteOtherManager(
            @MobileNumber @RequestParam("m") String inviteeMobNo,
            @RequestParam("o") Integer outletId,
            @RequestParam("i")Integer invitationId
    ) throws BadRequestException {
        String merchantMobNo = userContext.getMobNo();
        return merchantService.inviteSomeoneElse(merchantMobNo,inviteeMobNo,outletId);
    }
    @GetMapping("/outlet/viewAll")
    @Transactional
    @Validated
    public List<OutletDetailed> viewAllOutlets(){
        String merchantMobNo = userContext.getMobNo();
        return  merchantService.viewAllOutlets(merchantMobNo);
    }


    @PostMapping(
            value = "/menu/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Transactional
    @Validated
    public void addProducts(
            @RequestPart("products")@Valid List<ProductR> products,
            @RequestPart("file")@Valid @NotNull List<MultipartFile> images
    ) throws IOException, NotFoundException {

        String mobNo = userContext.getMobNo();
        /*
         * Menu is supposed to be added once so before checking twice it must be checked
         */
        merchantService.checkForMenuAdded(mobNo);

        if(products.size()==images.size() || products.size()>100){
            throw new com.twilight.exceptions.BadRequestException("Must have equal number of images as products and should be less than 100", "");
        }

        imageValidator.validateImages(images);



        Map<String, MultipartFile> fileMap = images.stream()
                .collect(Collectors.toMap(
                        MultipartFile::getOriginalFilename,
                        Function.identity()
                ));


        List<Product> productsDb = new ArrayList<>();
        for (ProductR product : products) {

            MultipartFile image =
                    fileMap.get(product.imageFileName());

            if (image == null) {
                throw new com.twilight.exceptions.BadRequestException(
                        "Image not found: "
                                + product.imageFileName(),
                        "");
            }
            String key = storageService.generateKey("products",image.getOriginalFilename());

            Product productDb = productMapper.toProduct(product);

            productDb.setImage(key);

            productsDb.add(productDb);
        }
        merchantService.addAllToMenu(mobNo,productsDb);
    }


    @PostMapping(
            value ="/menu/add/product",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Transactional
    @Validated

    public void addProduct(@RequestPart("products")@Valid @NotNull ProductR productR,
                           @RequestPart("file") @Valid @NotNull MultipartFile image) throws IOException {
        String mobNo = userContext.getMobNo();

        imageValidator.validateImage(image);
        String key = storageService.generateKey("products",image.getOriginalFilename());
        Product product = productMapper.toProduct(productR);
        product.setImage(key);
        merchantService.addToMenu(mobNo,product);
        storageService.upload(image,key);

    }
}
