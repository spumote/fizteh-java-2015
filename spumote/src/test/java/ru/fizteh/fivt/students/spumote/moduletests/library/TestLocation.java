package ru.fizteh.fivt.students.spumote.moduletests.library;

/**
 * Created by spumote on 19.12.15.
 */

import org.junit.Assert;
import org.junit.Test;
import com.google.maps.model.LatLng;


public class TestLocation {
    @Test
    public void testCase1() {
        String place = "Kazan";
        LatLng expectedLocation = new LatLng(55.7887400, 49.1221400);
        LatLng testLocation = new Location(place).getLocation();
        Assert.assertEquals(expectedLocation, testLocation);
    }

    @Test
    public void testCase2() {
        String place = "Moscow";
        LatLng expectedLocation = new LatLng(55.7522200, 37.6155600);
        LatLng testLocation = new Location(place).getLocation();
        Assert.assertEquals(expectedLocation, testLocation);
    }
}
