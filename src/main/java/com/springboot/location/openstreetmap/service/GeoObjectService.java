package com.springboot.location.openstreetmap.service;

import com.springboot.location.openstreetmap.entity.GeoObject;
import com.springboot.location.openstreetmap.dto.GeoObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.springboot.location.openstreetmap.constants.Constants.GEO_OBJECT_API;


@Service
public class GeoObjectService {
    private final RestTemplate restTemplate;

    @Autowired
    public GeoObjectService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "geoObjects", key = "#geoObjectName")
    public GeoObjectResponse getGeoObjectInfo(final String geoObjectName) {
        final GeoObject[] entities = Objects.requireNonNull(restTemplate.getForEntity(
            String.format(GEO_OBJECT_API, geoObjectName),
            GeoObject[].class
        ).getBody());

        return GeoObjectResponse.of(entities[0]);
    }
}
