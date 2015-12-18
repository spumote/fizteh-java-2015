package ru.fizteh.fivt.students.spumote.twitterstream;

/**
 * Created by spumote on 17.12.15.
 */

import com.beust.jcommander.Parameter;

public class CommandLineParser {
    @Parameter(names = { "--query", "-q"}, description = "Ключевое слово для поиска.")
    private String query = null;

    @Parameter(names = {"--place", "-p"}, description = "Искать только по заданному региону")
    private String place = null;

    @Parameter(names = {"--stream", "-s"}, description =
            "Выводить результаты поиска равномерно с задержкой в 1 секунду.")
    private boolean stream = false;

    @Parameter(names = {"--hideRetweets"}, description = "Не показывать ретвиты.")
    private boolean hideRetweets = false;

    @Parameter(names = {"--limit", "-l"}, description = "Выводить только заданное число твитов.")
    private int limitTweets = 5;

    @Parameter(names = {"--help", "-h"}, description = "Справка.")
    private boolean help = false;

    public String getQuery() {
        return query;
    }

    public String getPlace() {
        return place;
    }

    public boolean isStream() {
        return stream;
    }

    public boolean isHideRetweets() {
        return hideRetweets;
    }

    public int getLimitTweets() {
        return limitTweets;
    }

    public boolean isHelp() {
        return help;
    }
}

