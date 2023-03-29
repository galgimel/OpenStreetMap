package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("geojson")
    private GeoJson geoJson;
}
