package com.springboot.location.openstreetmap.restController;

import com.springboot.location.openstreetmap.dto.GeoObjectResponse;
import com.springboot.location.openstreetmap.service.GeoObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/geo_location")
public class GeoObjectRestController {
    private final GeoObjectService service;

    @Autowired
    public GeoObjectRestController(final GeoObjectService service) {
        this.service = service;
    }

    @GetMapping("/{geo_object_name}")
    public GeoObjectResponse showGeoObject(@PathVariable("geo_object_name") final String name) {
        return service.getGeoObjectInfo(name);
    }
}
