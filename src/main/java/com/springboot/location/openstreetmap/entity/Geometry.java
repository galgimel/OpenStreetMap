package com.springboot.location.openstreetmap.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.springboot.location.openstreetmap.service.GeoJsonDeserializer;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoJson {
    @JsonProperty("type")
    private String type;
    @JsonProperty("coordinates")
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private Map<String, Double> coordinates;
}