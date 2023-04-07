package com.springboot.location.openstreetmap.service;

import com.springboot.location.openstreetmap.dto.CoordinatePoint;
import com.springboot.location.openstreetmap.dto.GeoObjectResponse;
import com.springboot.location.openstreetmap.entity.GeoObject;
import com.springboot.location.openstreetmap.entity.Geometry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class GeoObjectServiceTest {
    @Autowired
    CacheManager cacheManager;
    @Autowired
    GeoObjectService service;
    @Mock
    RestTemplate restTemplate;

    private GeoObjectResponse getCachedGeoObject(String geoObjectName) {
        return Objects.requireNonNull(cacheManager.getCache("geoObjects"))
            .get(geoObjectName, GeoObjectResponse.class);
    }

    @Test
    void testFirst() {
        GeoObject[] geoObjects = new GeoObject[1];
        geoObjects[0] = new GeoObject(
            "Hermitage", new Geometry("Polygon", new CoordinatePoint(30.3, 59.9)));
        Mockito.when(restTemplate.getForEntity(
            "https://nominatim.openstreetmap.org/search?q=Hermitage&country=russia&format=json&polygon_geojson=1",
            GeoObject[].class))
            .thenReturn(new ResponseEntity<>(geoObjects, HttpStatus.OK));

        GeoObjectResponse hermitage = service.getGeoObjectInfo("Hermitage");
        assertEquals(hermitage, getCachedGeoObject("Hermitage"));
    }

    @Test
    void testSecond() {
        GeoObject[] geoObjects = new GeoObject[1];
        geoObjects[0] = new GeoObject(
            "Nevsky+prospect", new Geometry("LineString", new CoordinatePoint(30.4, 59.9)));
        Mockito.when(restTemplate.getForEntity(
                "https://nominatim.openstreetmap.org/search?q=Nevsky+prospect&country=russia&format=json&polygon_geojson=1",
                GeoObject[].class))
            .thenReturn(new ResponseEntity<>(geoObjects, HttpStatus.OK));

        service.getGeoObjectInfo("Nevsky+prospect");
        assertNull(getCachedGeoObject("Nevsky+prospect"));
    }
}