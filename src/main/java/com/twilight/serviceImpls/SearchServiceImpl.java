package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.Location;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.objects.Food;
import com.twilight.repositories.FoodRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.services.SearchService;
import com.twilight.types.OutletStatus;
import com.twilight.utils.Constants;
import com.twilight.utils.GeoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    final OutletRepository outletRepository;

    final FoodRepository foodRepository;


    @Override
    @Cacheable("outlets")
    public List<OutletR> findNearestOutlets(
            Double lat,
            Double lon) {

        return outletRepository.findNearestOutlets(lat,lon, OutletStatus.open,Constants.MAX_OUTLET_FILTER_LIMIT)
                        .stream()
                        .filter(outlet ->
                                        GeoUtil.calculateDistance(outlet.getLatitude(),outlet.getLongitude(),lat,lon)
                                <= Constants.MAXIMUM_DELIVERABLE_DISTANCE
                        )
                        .toList();
    }

    @Override
    public List<Food> getMenuByOutlet(Integer outletId) {
        return foodRepository.findByOutletId(outletId);
    }

}
