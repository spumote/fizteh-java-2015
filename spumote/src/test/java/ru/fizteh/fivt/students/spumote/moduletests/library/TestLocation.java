package ru.fizteh.fivt.students.spumote.moduletests.library;

/**
 * Created by spumote on 19.12.15.
 */

import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import com.google.maps.model.LatLng;
import static java.lang.Math.*;

public class TestLocation {
    public final double eps = 1e-5;

    @Test
    public void testCase1() {
        String place = "Kazan";
        LatLng expectedLocation = new LatLng(55.7887400, 49.1221400);
        LatLng testLocation = new Location(place).getLocation();
        Assert.assertEquals(true, abs(expectedLocation.lng - testLocation.lng) < eps);
        Assert.assertEquals(true, abs(expectedLocation.lat - testLocation.lat) < eps);
    }

    @Ignore
    @Test
    public void testCase2() {
        String place = "Moscow";
        LatLng expectedLocation = new LatLng(55.7522200, 37.6155600);
        LatLng testLocation = new Location(place).getLocation();
        Assert.assertEquals(expectedLocation, testLocation);
    }
}