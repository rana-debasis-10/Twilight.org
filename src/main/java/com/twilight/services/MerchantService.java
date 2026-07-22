package com.twilight.services;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.dataTransferObjects.Location;
import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.Merchant;
import com.twilight.objects.OutletInvitation;
import com.twilight.objects.Product;
import com.twilight.objects.Restaurant;
import software.amazon.awssdk.annotations.NotNull;

import java.util.List;

public interface MerchantService {
    void createOutlet(@MobileNumber @NotNull String mobNo, Location location);

    void create(@NotNull Merchant merchant, @NotNull Restaurant restaurant);

    OutletInvitation invite(@MobileNumber @NotNull String merchantMobNo, Integer outletId, @MobileNumber @NotNull String inviteeMobNo);

    List<OutletDetailed> viewAllOutlets(@MobileNumber String merchantMobNo);

    List<OutletInvitation> viewAllInvitation(@MobileNumber String merchantMobNo);

    void updateMenu(String mobNo, List<Product> products) throws NotFoundException;
}
