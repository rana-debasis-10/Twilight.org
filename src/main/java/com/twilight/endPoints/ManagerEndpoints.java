package com.twilight.endPoints;

import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.services.JwtService;
import com.twilight.services.ManagerService;
import com.twilight.utils.UserContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/manager")
@Validated
@RequiredArgsConstructor
public class ManagerEndpoints {
    private
       UserContext user;

    private
       ManagerService managerService;

    private
       JwtService jwtService;



    @GetMapping("/update/food/price")
    @Transactional
    void updateFoodPrice(@RequestParam Integer foodId, Double price) {
        Integer outletId = (Integer)user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access",
                    "No Linked Outlet");
        managerService.updateFoodPrice(outletId,foodId,price);
    };

    @GetMapping("/update/food/available")
    @Transactional
    void makeFoodAvailable(@RequestParam Integer foodId){
        Integer outletId = (Integer) user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        managerService.makeFoodAvailable(outletId,foodId);
    };

    @GetMapping("/update/food/unavailable")
    @Transactional
    void makeFoodUnavailable(@RequestParam (value = "f")Integer foodId){
        Integer outletId = (Integer)user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        managerService.makeFoodUnavailable(outletId,foodId);
    };

    @GetMapping("/update/outlet/open")
    @Transactional
    void openOutlet(){
        Integer outletId = (Integer) user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        managerService.openOutlet(outletId);
    };

    @GetMapping("/update/outlet/close")
    @Transactional
    void closeOutlet(){
        Integer outletId = (Integer) user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        managerService.closeOutlet(outletId);
    };

    @GetMapping("/view/outlet")
    @Transactional
    OutletR viewOutlet() throws BadRequestException {
        Integer outletId = (Integer) user.getCredential();
        if(outletId==null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        return managerService.viewOutlet(outletId);
    }
    @GetMapping
    @Transactional
    List<FoodR> viewAllFoods(){
        Integer outletId = (Integer) user.getCredential();
        if(outletId==null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        return managerService.getAllFoods(outletId);
    }
}

