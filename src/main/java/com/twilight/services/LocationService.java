package com.twilight.services;


import com.twilight.dataTransferObjects.Address;
import com.twilight.dataTransferObjects.Location;
import com.twilight.exceptions.GeocodingError;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface LocationService {
    Location getLocation(@NotNull Address address) throws GeocodingError;
    void updateLocation(String driverId, Location location);
    List<String> findNearByDriver(Location location);
}
