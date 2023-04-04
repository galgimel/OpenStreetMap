package com.springboot.location.openstreetmap.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.springboot.location.openstreetmap.dto.CoordinatePoint;
import com.springboot.location.openstreetmap.entity.Geometry;

import java.io.IOException;
import java.util.*;

import static com.springboot.location.openstreetmap.constants.Constants.*;

public class GeometryDeserializer extends StdDeserializer<Geometry> {

    public GeometryDeserializer() {
        this(null);
    }

    public GeometryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public final Geometry deserialize(
        final JsonParser parser,
        final DeserializationContext deserializer
    ) throws IOException {

        final Geometry geometry = new Geometry();
        final JsonNode node = parser.getCodec().readTree(parser);
        final Iterator<JsonNode> coordinates = node.findValue("coordinates").iterator();
        final String geometryType = node.get("type").asText();

        final List<CoordinatePoint> exactCoordinates;

        switch (geometryType) {
            case TYPE_POINT: {
                exactCoordinates = List.of(getCoordinateFromPoint(coordinates));
                break;
            }
            case TYPE_LINE_STRING: {
                exactCoordinates = getCoordinateFromLineString(coordinates);
                break;
            }
            case TYPE_POLYGON:
            case TYPE_MULTI_LINE_STRING: {
                exactCoordinates = getCoordinateFromPolygon(coordinates);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + geometryType);
        }

        geometry.setType(geometryType);
        geometry.setCenter(getCenter(exactCoordinates));
        return geometry;
    }



    private List<CoordinatePoint> getCoordinateFromPolygon(final Iterator<JsonNode> nodeIterator) {
        final var coordinates = new ArrayList<CoordinatePoint>();

        while (nodeIterator.hasNext()) {
            coordinates.addAll(getCoordinateFromLineString(nodeIterator.next().iterator()));
        }
        return coordinates;
    }
    private List<CoordinatePoint> getCoordinateFromLineString(final Iterator<JsonNode> nodeIterator) {
        final var coordinates = new ArrayList<CoordinatePoint>();

        while (nodeIterator.hasNext()) {
            coordinates.add(getCoordinateFromPoint(nodeIterator.next().iterator()));
        }
        return coordinates;
    }
    private CoordinatePoint getCoordinateFromPoint(final Iterator<JsonNode> nodeIterator) {
        final var point = new CoordinatePoint();

        while (nodeIterator.hasNext()) {
            point.setLongitude(nodeIterator.next().asDouble());
            point.setLatitude(nodeIterator.next().asDouble());
        }
        return point;
    }

    private CoordinatePoint getCenter(final List<CoordinatePoint> coordinates) {
        return CoordinatePoint.builder()
            .longitude(coordinates.stream().mapToDouble(CoordinatePoint::getLongitude).sum() / coordinates.size())
            .latitude(coordinates.stream().mapToDouble(CoordinatePoint::getLatitude).sum() / coordinates.size())
            .build();
    }
}