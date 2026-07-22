package com.twilight.services;


import com.twilight.objects.Location;

import java.util.List;

public interface LocationService {
    void updateLocation(String driverId, Location location);
    List<String> findNearByDriver(Location location);
}
