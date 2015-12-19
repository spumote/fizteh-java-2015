package ru.fizteh.fivt.students.spumote.moduletests.library;

/**
 * Created by spumote on 18.12.15.
 */
import twitter4j.Status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class PrintTweet {

    public static String printDate(Date date) {

        String ans;

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime tweetTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        if (ChronoUnit.MINUTES.between(tweetTime, currentTime) < 2) {
            ans = "[только что]";
            return ans;
        }
        if (ChronoUnit.HOURS.between(tweetTime, currentTime) < 1) {
            ans = "[" + ChronoUnit.MINUTES.between(tweetTime, currentTime) + " минут назад]";
            return ans;
        }
        if (ChronoUnit.DAYS.between(tweetTime, currentTime) < 1) {
            ans = "[" + ChronoUnit.HOURS.between(tweetTime, currentTime) + " часов назад]";
            return ans;
        }
        if (ChronoUnit.DAYS.between(tweetTime, currentTime) == 1) {
            ans = "[вчера]";
            return ans;
        }
        ans = "[" + ChronoUnit.DAYS.between(tweetTime, currentTime) + " дней назад]";
        return ans;
    }

    public static String printTweet(Status status) {

        String ans = "@" + status.getUser().getScreenName() + ": ";
        String text = status.getText();
        if (status.isRetweet()) {
            int index = text.indexOf("@");
            text = text.substring(index, text.length());
            index = text.indexOf(" ");
            String retweetedName = text.substring(0, index - 1);
            text = text.substring(index + 1, text.length());
            ans += "ретвитнул " + retweetedName + ": ";
        }
        ans += text;
        if (!status.isRetweet() && status.getRetweetCount() > 0) {
            ans += " (" + status.getRetweetCount() + " ретвитов)";
        }

        return ans;
    }
}
