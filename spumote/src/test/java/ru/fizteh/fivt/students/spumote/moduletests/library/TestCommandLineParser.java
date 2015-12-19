package ru.fizteh.fivt.students.spumote.moduletests.library;

/**
 * Created by spumote on 18.12.15.
 */

import com.beust.jcommander.JCommander;
import org.junit.Assert;
import org.junit.Test;

public class TestCommandLineParser {

    @Test
    public void testCaseIsHelp() {
        CommandLineParser commandLineParser = new CommandLineParser();
        new JCommander(commandLineParser, new String[]{"-h", "-q", "fivt"});
        Assert.assertEquals(true, commandLineParser.isHelp());
    }

    @Test
    public void testCaseIsNotHelp() {
        CommandLineParser commandLineParser = new CommandLineParser();
        new JCommander(commandLineParser, new String[]{"-q", "fivt"});
        Assert.assertEquals(false, commandLineParser.isHelp());
    }

    @Test
    public void testCaseIsStream() {
        CommandLineParser commandLineParser = new CommandLineParser();
        new JCommander(commandLineParser, new String[]{"--stream", "-q", "fivt"});
        Assert.assertEquals(true, commandLineParser.isStream());
    }

    @Test
    public void testCaseAllFirst() {
        CommandLineParser commandLineParser = new CommandLineParser();
        new JCommander(commandLineParser, new String[]{"-q", "MimimiCat", "-s", "--place", "Kazan", "--hideRetweets"});
        Assert.assertEquals(5, commandLineParser.getLimitTweets());
        Assert.assertEquals("MimimiCat", commandLineParser.getQuery());
        Assert.assertEquals(false, commandLineParser.isHelp());
        Assert.assertEquals(true, commandLineParser.isStream());
        Assert.assertEquals(true, commandLineParser.isHideRetweets());
        Assert.assertEquals("Kazan", commandLineParser.getPlace());
    }

    @Test
    public void testCaseAllSecond() {
        CommandLineParser commandLineParser = new CommandLineParser();
        new JCommander(commandLineParser, new String[]{"--limit", "3", "-q", "Java", "--place", "California"});
        Assert.assertEquals(3, commandLineParser.getLimitTweets());
        Assert.assertEquals("Java", commandLineParser.getQuery());
        Assert.assertEquals(false, commandLineParser.isHelp());
        Assert.assertEquals(false, commandLineParser.isStream());
        Assert.assertEquals(false, commandLineParser.isHideRetweets());
        Assert.assertEquals("California", commandLineParser.getPlace());
    }
}
