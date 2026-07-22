package com.twilight.serviceImpls;

import com.twilight.objects.Location;
import com.twilight.services.LocationService;
import com.twilight.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    final GeoOperations<String, String> geo;

    @Override
    public void updateLocation(String driverMobNo, Location location) {
        geo.add(
                Constants.DRIVER_LOCATION,
                new Point(location.getLongitude(),location.getLatitude()),
               driverMobNo
        );
    }

    @Override
    public List<String> findNearByDriver(Location location) {
        Point centerPoint = new Point(location.getLatitude(), location.getLongitude());
        GeoReference<String> reference = GeoReference.fromCoordinate(centerPoint);
        Distance radius = new Distance(Constants.OUTLET_DRIVER_MAX_DISTANCE, RedisGeoCommands.DistanceUnit.KILOMETERS);

        RedisGeoCommands.GeoSearchCommandArgs args = RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs()
                .sortAscending()
                .includeDistance();
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geo.search(Constants.DRIVER_LOCATION, reference, radius, args);
        return results.getContent().stream().map(result-> result.getContent().getName()).toList();
    }


}
