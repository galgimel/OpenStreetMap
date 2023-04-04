package com.springboot.location.openstreetmap.dto;

import com.springboot.location.openstreetmap.entity.GeoObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GeoObjectResponse {
    private final String name;
    private final String type;
    private final double longitude;
    private final double latitude;

    public static GeoObjectResponse of(final GeoObject geoObject) {
        return GeoObjectResponse.builder()
            .name(geoObject.getName())
            .longitude(geoObject.getGeojson().getCenter().getLongitude())
            .latitude(geoObject.getGeojson().getCenter().getLatitude())
            .type(geoObject.getGeojson().getType())
            .build();
    }
}
