package com.springboot.location.openstreetmap.controller;

import com.springboot.location.openstreetmap.dto.GeoObjectRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springboot.location.openstreetmap.service.GeoObjectService;

@Controller
@RequestMapping("/geo_location")
public class GeoObjectController {
    private final GeoObjectService geoObjectService;

    public GeoObjectController(final GeoObjectService geoObjectService) {
        this.geoObjectService = geoObjectService;
    }

    @GetMapping
    public String showFrontPage(@ModelAttribute("geoObject") final GeoObjectRequest request) {
        return "get-geo-object-name";
    }

    @GetMapping("/{geo_object_name}")
    public String getGeoObjectInfo(
        @PathVariable("geo_object_name") final String geoObjectName, final Model model
    ) {
        model.addAttribute("geoObject", geoObjectService.getGeoObjectInfo(geoObjectName));
        return "geo-object-info";
    }
}
