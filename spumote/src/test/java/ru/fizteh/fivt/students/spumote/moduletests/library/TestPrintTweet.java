package ru.fizteh.fivt.students.spumote.moduletests.library;

/**
 * Created by spumote on 19.12.15.
 */

import org.junit.Assert;
import org.junit.Test;
import twitter4j.Status;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TestPrintTweet {
    @Test
    public void testDateCase1() {
        String needed = "[только что]";
        Date date = new Date(System.currentTimeMillis());
        Assert.assertEquals(needed, PrintTweet.printDate(date));
    }
    @Test
    public void testDateCase2() {
        String needed = "[4 минут назад]";
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 4);
        Assert.assertEquals(needed, PrintTweet.printDate(date));
    }
    @Test
    public void testDateCase3() {
        String needed = "[1 часов назад]";
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60);
        Assert.assertEquals(needed, PrintTweet.printDate(date));
    }
    @Test
    public void testDateCase4() {
        String needed = "[вчера]";
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 30);
        Assert.assertEquals(needed, PrintTweet.printDate(date));
    }
    @Test
    public void testDateCase5() {
        String needed = "[3 дней назад]";
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 80);
        Assert.assertEquals(needed, PrintTweet.printDate(date));
    }

    @Test
    public void testPrintCase() {
        String needed = "[3 дней назад]";
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 80);
        Assert.assertEquals(needed, PrintTweet.printDate(date));
    }

}
