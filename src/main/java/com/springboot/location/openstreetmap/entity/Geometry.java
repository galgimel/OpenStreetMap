package com.springboot.location.openstreetmap.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.location.openstreetmap.dto.CoordinatePoint;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Geometry {
    @JsonProperty("type")
    private String type;
    private CoordinatePoint center;
}