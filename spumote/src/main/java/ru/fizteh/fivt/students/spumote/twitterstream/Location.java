package ru.fizteh.fivt.students.spumote.twitterstream;

/**
 * Created by spumote on 17.12.15.
 */

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.Bounds;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.io.*;
import java.util.Scanner;
import static java.lang.Math.*;

public class Location {

    private static final double RADIUS_OF_EARTH = 6371;

    private GeocodingResult geocodingResults;
    private double radius;

    Location(String place) {
        String apiKey = null;
        try {
            File keyFile = new File("googlemaps.properties");
            apiKey = new Scanner(keyFile).useDelimiter("\\Z").next();
        } catch (FileNotFoundException exception) {
            System.err.println("Can't find or read googleApiKey " + exception.toString());
            exception.printStackTrace(System.err);
            System.exit(1);
        }

        GeoApiContext context = new GeoApiContext().setApiKey(apiKey);

        try {
            geocodingResults = GeocodingApi.geocode(context, place).await()[0];
        } catch (Exception exception) {
            System.err.println("Break in get geocoding: " + exception.toString());
            exception.printStackTrace(System.err);
            System.exit(1);
        }

        radius = calculateRadius();
    }

    private double calculateRadius() {
        LatLng point1 = geocodingResults.geometry.bounds.northeast;
        LatLng point2 = geocodingResults.geometry.bounds.southwest;
        double rad = 180.0 / PI;
        double a = cos(point1.lat / rad) * cos(point1.lng / rad) * cos(point2.lat / rad) * cos(point2.lng / rad);
        double b = cos(point1.lat / rad) * sin(point1.lng / rad) * cos(point2.lat / rad) * sin(point2.lng / rad);
        double c = sin(point1.lat / rad) * sin(point2.lat / rad);

        return RADIUS_OF_EARTH * acos(a + b + c);
    }

    public LatLng getLocation() {
        return geocodingResults.geometry.location;
    }

    public double getRadius() {
        return radius;
    }

    public final Bounds getBounds() {
        return geocodingResults.geometry.bounds;
    }
}
