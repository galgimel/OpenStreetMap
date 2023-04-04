package com.springboot.location.openstreetmap.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoordinatePoint {
    private double longitude;
    private double latitude;
}
