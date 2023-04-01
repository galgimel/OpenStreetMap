package com.springboot.location.openstreetmap.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.springboot.location.openstreetmap.service.GeometryDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoObject {
    @JsonProperty("display_name")
    private String name;
    @JsonDeserialize(using = GeometryDeserializer.class)
    @JsonProperty("geojson")
    private Geometry geojson;
}