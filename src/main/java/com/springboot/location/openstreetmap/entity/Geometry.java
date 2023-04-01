package com.springboot.location.openstreetmap.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Geometry {
    @JsonProperty("type")
    private String type;
    private Map<String, Double> coordinates;
}