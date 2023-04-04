package com.springboot.location.openstreetmap.service;

import com.springboot.location.openstreetmap.entity.GeoObject;
import com.springboot.location.openstreetmap.dto.GeoObjectResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static com.springboot.location.openstreetmap.constants.Constants.GEO_OBJECT_API;


@Service
public class GeoObjectService {
    private final RestTemplate restTemplate;

    public GeoObjectService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("geoObjectName")
    public List<GeoObjectResponse> getGeoObjectInfo(final String geoObjectName) {
        final GeoObject[] entities = Objects.requireNonNull(restTemplate.getForEntity(
            String.format(GEO_OBJECT_API, geoObjectName),
            GeoObject[].class
        ).getBody());

        return Arrays.stream(entities)
            .map(GeoObjectResponse::of)
            .collect(Collectors.toList());
    }
}
