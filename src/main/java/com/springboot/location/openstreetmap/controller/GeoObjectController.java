package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import service.GeoObjectService;

@Controller
@RequestMapping("/geo_location")
public class GeoObjectController {
    private final GeoObjectService geoObjectService;

    public GeoObjectController(GeoObjectService geoObjectService) {
        this.geoObjectService = geoObjectService;
    }

    @GetMapping
    public String showFrontPage() {
        return "front-page";
    }

    @GetMapping("/{geo_object_name}")
    public String getGeoObjectInfo(
        @PathVariable("geo_object_name") final String geoObjectName, final Model model
    ) {
        model.addAttribute("name", geoObjectService.getGeoObjectInfo(geoObjectName));
        return "geo-object-info";
    }
}
