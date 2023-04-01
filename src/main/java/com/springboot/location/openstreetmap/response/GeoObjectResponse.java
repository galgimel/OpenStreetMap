package com.springboot.location.openstreetmap.response;

import com.springboot.location.openstreetmap.entity.GeoObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.springboot.location.openstreetmap.constants.Constants.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GeoObjectResponse {
    private final String name;
    private final String type;
    private final double longitude;
    private final double latitude;
    public static GeoObjectResponse of(GeoObject geoObject) {
        return GeoObjectResponse.builder()
            .name(geoObject.getName())
            .longitude(geoObject.getGeojson().getCoordinates().get(KEY_LONGITUDE))
            .latitude(geoObject.getGeojson().getCoordinates().get(KEY_LATITUDE))
            .type(geoObject.getGeojson().getType())
            .build();
    }
}
