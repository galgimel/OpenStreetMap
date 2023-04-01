package com.springboot.location.openstreetmap.service;

import com.springboot.location.openstreetmap.entity.GeoObject;
import com.springboot.location.openstreetmap.response.GeoObjectResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.springboot.location.openstreetmap.constants.Constants.GEO_OBJECT_API;


@Service
public class GeoObjectService {
    private final RestTemplate restTemplate = new RestTemplate();


    @Cacheable("geoObjectName")
    public List<GeoObjectResponse> getGeoObjectInfo(String geoObjectName) {
        GeoObject[] entities = Objects.requireNonNull(restTemplate.getForEntity(
                   String.format(GEO_OBJECT_API, geoObjectName),
                   GeoObject[].class
               )
               .getBody());
        List<GeoObjectResponse> responses = new ArrayList<>();

        for (GeoObject entity : entities) {
            responses.add(GeoObjectResponse.of(entity));
        }
        return responses;
    }
}
