package com.twilight.services;

import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;


import java.util.List;

public interface SearchService {
    List<OutletR> findNearestOutlets(Double lat, Double lon);
    List<FoodR> getMenuByOutlet(Integer outletId);
    boolean isDeliverable(Double lat, Double lon, Integer outletId);
}
