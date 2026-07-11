package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.Address;
import com.twilight.dataTransferObjects.Location;
import com.twilight.exceptions.GeocodingError;
import com.twilight.exceptions.SomethingWentWrongException;
import com.twilight.services.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@AllArgsConstructor

public class LocationServiceImpl implements LocationService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate<String,String> redis;


    @Override
    public Location getLocation(Address address) throws GeocodingError
    {
        String response = null;
        try {
            String headerName = "User-Agent";
            String headerValue = "TwilightFoodDelivery/1.0";

            HttpEntity<Void> request = createRequest(headerName,headerValue);
            String url = generateUrl(
                    address.state(),
                    address.city(),
                    address.pinCode(),
                    address.street(),
                    address.landMark()
            );


            response = new RestTemplate().exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class
            ).getBody();
        } catch (RestClientException e) {
            throw new SomethingWentWrongException(e.getMessage(),"Operation failed");
        }
        return formatForLatAndLon(response);
    }

    private String generateUrl(String state,
                               String city,
                               String pinCode,
                               String street,
                               String landmark
    ){
        String query =
                street + ", " +
                        city + ", " +
                        state + ", " +
                        "India";
        String baseUrl = "https://nominatim.openstreetmap.org/search";
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("q", query)
                .queryParam("format", "jsonv2")
                .queryParam("limit", 1)
                .build(false)
                .toUriString();
    }
    private <T> HttpEntity<T> createRequest(String headerName,
                                    String headerValue
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.set(headerName, headerValue);
        return new HttpEntity<T>(headers);
    }

    private Location formatForLatAndLon(String response)
    {
        JsonNode root = new ObjectMapper().readTree(response);

        if(root.isEmpty())
            throw new GeocodingError("Address not found for the request check the query","Address not found");
        double lat =
                root.get(0)
                        .get("lat")
                        .asDouble();

        double lon =
                root.get(0)
                        .get("lon")
                        .asDouble();
        return new Location(lat,lon);
    }

    @Override
    public void updateLocation(String driverMobNo, Location location) {
        GeoOperations<String, String> geo =
                redis.opsForGeo();
        geo.add(
                "driver:locations",
                new org.springframework.data.geo.Point(location.longitude(),location.latitude()),
                String.valueOf(driverMobNo)
        );
    }

    /**
     //* @param location
     //*/
    @Override
    public List<String> findNearByDriver(Location location) {


        GeoResults<RedisGeoCommands.GeoLocation<String>> results =
                redis.opsForGeo().radius(

                        "driver:locations",

                        new Circle(
                                new org.springframework.data.geo.Point(location.longitude(), location.latitude()),
                                new Distance(
                                        5,
                                        Metrics.KILOMETERS
                                )

                        )

                );

        return results.getContent()

                .stream()

                .map(r -> r.getContent().getName())

                .toList();


    }

}
