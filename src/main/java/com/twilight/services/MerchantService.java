package com.twilight.services;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.Location;
import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.SomethingWentWrongException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.Merchant;
import com.twilight.objects.OutletInvitation;
import com.twilight.objects.Product;
import com.twilight.objects.Restaurant;
import software.amazon.awssdk.annotations.NotNull;

import java.util.List;

public interface MerchantService {
    void createOutlet(@MobileNumber @NotNull String mobNo, Location location);

    void createMerchant(@NotNull Merchant merchant,@NotNull Restaurant restaurant);

    OutletInvitation invite(@MobileNumber @NotNull String merchantMobNo, @MobileNumber @NotNull String inviteeMobNo, Integer outletId);

    OutletInvitation inviteSomeoneElse(String merchantMobNo, String inviteeMobNo, Integer outletId) throws com.twilight.exceptions.BadRequestException;

    List<OutletDetailed> viewAllOutlets(@MobileNumber String merchantMobNo);

    void getMerchant(String mobNo);

    List<OutletInvitation> viewAllInvitation(@MobileNumber String merchantMobNo);

    Restaurant findRestaurantByMobNo(String mobNo);

    void addAllToMenu(String mobNo, List<Product> products) throws NotFoundException;

    void addToMenu(String mobNo, Product product) throws NotFoundException , SomethingWentWrongException;

    void checkForMenuAdded(String mobNo) throws UnAuthorizedException;
}
