package ru.fizteh.fivt.students.spumote.moduletests.library;

/**
 * Created by spumote on 18.12.15.
 */

import com.beust.jcommander.JCommander;
import twitter4j.*;

import java.util.List;

public class TwitterStream {
    private CommandLineParser commandLineParser;

    public static void main(String[] args) {
        TwitterStream twitterStream = new TwitterStream(args);
        if (twitterStream.commandLineParser != null) {
            if (twitterStream.commandLineParser.isStream()) {
                runStream(twitterStream.commandLineParser);
            } else {
                runSearch(twitterStream.commandLineParser);
            }
        }
    }

    public TwitterStream(String[] args) {
        commandLineParser = new CommandLineParser();
        JCommander jCommander = new JCommander(commandLineParser, args);
        if (commandLineParser.isHelp()) {
            jCommander.usage();
        }
    }

    public static void runStream(CommandLineParser commandLineParser) {
        twitter4j.TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener statusListener = new StatusAdapter() {

            @Override
            public void onStatus(Status tweet) {
                if (!commandLineParser.isHideRetweets() || !tweet.isRetweet()) {
                    System.out.println(PrintTweet.printTweet(tweet));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                        System.err.println("Thread error: " + exception.toString());
                        exception.printStackTrace(System.err);
                        System.exit(1);
                    }
                }
            }

            @Override
            public void onException(Exception exception) {
                System.err.println("Stream error: " + exception.toString());
                exception.printStackTrace(System.err);
                System.exit(1);
            }
        };

        twitterStream.addListener(statusListener);
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(commandLineParser.getQuery());
        if (commandLineParser.getPlace() != null) {
            Location findPlace = new Location((commandLineParser.getPlace()));
            double[][] bounds = {{findPlace.getBounds().southwest.lng, findPlace.getBounds().southwest.lat},
                    {findPlace.getBounds().northeast.lng, findPlace.getBounds().northeast.lat}};
            filterQuery.locations(bounds);
        }
        twitterStream.filter(filterQuery);
    }

    public static void runSearch(CommandLineParser commandLineParser) {
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query();
        query.setQuery(commandLineParser.getQuery());
        query.setCount(commandLineParser.getLimitTweets());

        if (commandLineParser.getPlace() != null) {
            Location googleFindPlace;
            googleFindPlace = new Location(commandLineParser.getPlace());
            GeoLocation geoLocation = new GeoLocation(googleFindPlace.getLocation().lat,
                    googleFindPlace.getLocation().lng);
            query.setGeoCode(geoLocation, googleFindPlace.getRadius(), Query.KILOMETERS);
        }

        try {
            List<Status> tweets = twitter.search(query).getTweets();
            for (Status tweet: tweets) {
                System.out.print(PrintTweet.printDate(tweet.getCreatedAt()));
                System.out.println(PrintTweet.printTweet(tweet));
                System.out.println("----------------------------------"
                        + "------------------------------------------------------");
            }
        } catch (TwitterException exception) { }
    }
}
