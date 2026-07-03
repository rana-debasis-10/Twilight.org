package com.twilight.services;


import com.twilight.dataTransferObjects.Address;
import com.twilight.dataTransferObjects.Point;
import com.twilight.exceptions.GeocodingError;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface LocationService {
    Point getLocation(@NotNull Address address) throws GeocodingError;
    void updateLocation(String driverId, Point location);
    List<String> findNearByDriver(Point point);
}
