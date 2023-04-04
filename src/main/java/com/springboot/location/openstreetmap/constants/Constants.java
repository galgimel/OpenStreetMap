package com.springboot.location.openstreetmap.constants;

public class Constants {
    public static final String GEO_OBJECT_API =
        "https://nominatim.openstreetmap.org/search?q=%s&country=russia&format=json&polygon_geojson=1";
    public static final String TYPE_POINT = "Point";
    public static final String TYPE_LINE_STRING = "LineString";
    public static final String TYPE_POLYGON = "Polygon";
    public static final String TYPE_MULTI_LINE_STRING = "MultiLineString";
}
