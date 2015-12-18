package ru.fizteh.fivt.students.spumote.twitterstream;

/**
 * Created by spumote on 17.12.15.
 */

import twitter4j.Status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class PrintTweet {

    public static void printDate(Date date) {

        System.out.print("[");

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime tweetTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        if (ChronoUnit.MINUTES.between(tweetTime, currentTime) < 2) {
            System.out.print("только что]");
            return;
        }
        if (ChronoUnit.HOURS.between(tweetTime, currentTime) < 1) {
            System.out.print(ChronoUnit.MINUTES.between(tweetTime, currentTime) + " минут назад]");
            return;
        }
        if (ChronoUnit.DAYS.between(tweetTime, currentTime) < 1) {
            System.out.print(ChronoUnit.HOURS.between(tweetTime, currentTime) + " чаов назад]");
            return;
        }
        if (ChronoUnit.DAYS.between(tweetTime, currentTime) == 1) {
            System.out.print("вчера]");
            return;
        }
        System.out.print(ChronoUnit.DAYS.between(tweetTime, currentTime) + " дней назад]");
    }

    public static void printTweet(Status status) {

        System.out.print("@" + status.getUser().getScreenName() + ": ");
        String text = status.getText();
        if (status.isRetweet()) {
            int index = text.indexOf("@");
            text = text.substring(index, text.length());
            index = text.indexOf(" ");
            String retweetedName = text.substring(0, index - 1);
            text = text.substring(index + 1, text.length());
            System.out.print("ретвитнул " + retweetedName + ": ");
        }
        System.out.print(text);
        if (!status.isRetweet() && status.getRetweetCount() > 0) {
            System.out.print(" (" + status.getRetweetCount() + " ретвитов)");
        }
        System.out.println();

        System.out.println("----------------------------------------------------------------------------------------");
    }
}
