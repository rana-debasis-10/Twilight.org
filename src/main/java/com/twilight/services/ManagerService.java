package com.twilight.services;

import com.twilight.objects.Food;
import com.twilight.utils.annotations.MobileNumber;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.exceptions.NotFoundException;
import com.twilight.objects.OutletInvitation;
import org.apache.coyote.BadRequestException;
import software.amazon.awssdk.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public interface ManagerService {
    List<OutletInvitation> viewAllInvitation(@MobileNumber String merchantMobNo);

    void acceptInvitation(@MobileNumber @NotNull String mobNo, @NotNull Integer invitationId) throws NotFoundException ;

    void updateFoodPrice(@NotNull Integer outletId, @NotNull Integer foodId, Double price) throws NotFoundException;

    void makeFoodAvailable(@NotNull Integer outletId , @NotNull Integer foodId) throws NotFoundException;

    void makeFoodUnavailable(@NotNull Integer outletId, @NotNull Integer foodId) throws NotFoundException;

    void openOutlet(@NotNull Integer outletId)throws NotFoundException;

    void closeOutlet(@NotNull Integer outletId)throws NotFoundException;

    OutletR viewOutlet(@NotNull Integer outletId) throws BadRequestException;

    List<Food> getAllFoods(@NotNull Integer outletId) throws NotFoundException;


    void acceptOrder(String mobNo, Integer orderId, boolean accept) throws IOException;
}
