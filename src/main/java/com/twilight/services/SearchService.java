package com.twilight.services;

import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.objects.Food;


import java.util.List;

public interface SearchService {
    List<OutletR> findNearestOutlets(Double lat, Double lon);
    List<Food> getMenuByOutlet(Integer outletId);
}
