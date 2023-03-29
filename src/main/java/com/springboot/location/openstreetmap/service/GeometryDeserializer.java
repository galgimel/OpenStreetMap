package com.springboot.location.openstreetmap.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.springboot.location.openstreetmap.entity.Geometry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GeoJsonDeserializer extends StdDeserializer<Geometry> {
    public GeoJsonDeserializer() {
        this(null);
    }

    public GeoJsonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Geometry deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Geometry geometry = new Geometry();
        JsonNode node = parser.getCodec().readTree(parser);

        String geometryType = node.get("type").asText();
        Map<String, Double> geometryCoordinates = new HashMap<>();

        if (geometryType.equals("Point")) {

            
        } else if (geometryType.equals("LineString")) {

            
        } else if (geometryType.equals("Polygon")) {


        }

        geometry.setType(geometryType);
        geometry.setCoordinates(geometryCoordinates);
        return geometry;
    }
}
