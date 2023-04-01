package com.springboot.location.openstreetmap.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.springboot.location.openstreetmap.entity.Geometry;

import java.io.IOException;
import java.util.*;
import java.util.stream.DoubleStream;

import static com.springboot.location.openstreetmap.constants.Constants.*;

public class GeometryDeserializer extends StdDeserializer<Geometry> {
    public GeometryDeserializer() {
        this(null);
    }

    public GeometryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Geometry deserialize(
        JsonParser parser,
        DeserializationContext deserializer
    ) throws IOException {

        Geometry geometry = new Geometry();
        Map<String, Double> geometryCoordinates = new HashMap<>();
        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode coordinates = node.get("coordinates");
        String geometryType = node.get("type").asText();


        switch (geometryType) {
            case TYPE_POINT: {
                geometryCoordinates.put(KEY_LONGITUDE, coordinates.get(0).asDouble());
                geometryCoordinates.put(KEY_LATITUDE, coordinates.get(1).asDouble());

                break;
            }
            case TYPE_LINE_STRING : {
                geometryCoordinates.put(KEY_LONGITUDE, averageValue(coordinates).get(KEY_LONGITUDE));
                geometryCoordinates.put(KEY_LATITUDE, averageValue(coordinates).get(KEY_LATITUDE));

                break;
            }
            case TYPE_POLYGON: {
                geometryCoordinates.put(KEY_LONGITUDE, averageValue(coordinates.get(0)).get(KEY_LONGITUDE));
                geometryCoordinates.put(KEY_LATITUDE, averageValue(coordinates.get(0)).get(KEY_LATITUDE));

                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + geometryType);
        }

        geometry.setType(geometryType);
        geometry.setCoordinates(geometryCoordinates);
        return geometry;
    }

    private Map<String, Double> averageValue(JsonNode coordinates) {
        Map<String, Double> result = new HashMap<>();
        double[] lon = new double[coordinates.size()];
        double[] lat = new double[coordinates.size()];

        for (int i = 0; i < coordinates.size(); i++) {
            lon[i] = coordinates.get(i).get(0).asDouble();
            lat[i] = coordinates.get(i).get(1).asDouble();
        }

        double longitude = DoubleStream.of(lon).sum() / coordinates.size();
        double latitude = DoubleStream.of(lat).sum() / coordinates.size();
        result.put(KEY_LONGITUDE, longitude);
        result.put(KEY_LATITUDE, latitude);

        return result;
    }
}