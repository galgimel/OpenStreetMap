package service;

import entity.GeoObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class GeoObjectService {
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GEO_OBJECT_API =
        "https://nominatim.openstreetmap.org/search?q=%s&country=russia&format=json&polygon_geojson=1";

    public GeoObject getGeoObjectInfo(String geoObjectName) {
        return restTemplate.getForEntity(String.format(GEO_OBJECT_API, geoObjectName), GeoObject.class).getBody();
    }
}
