package com.studioidan.popapp.utils;

/**
 * Created by PopApp_laptop on 20/03/17.
 */

public class TimeUtils {
    public static final String HOURS = "hours";
    public static final String MINUTES = "MINUTES";
    public static final String SECONDS = "seconds";

    /**
     * example int hours = secondsTo(2000,TimeUtils.HOURS);
     *
     * @param totalSeconds
     * @param output
     * @return
     */
    public static int secondsTo(int totalSeconds, String output) {
        int hours = totalSeconds / 60 / 60;
        int minutes = (totalSeconds % hours) / 60;
        int seconds = totalSeconds % 60;
        switch (output.toLowerCase()) {
            case HOURS:
                return hours;
            case MINUTES:
                return minutes;
            case SECONDS:
                return seconds;
        }
        return 0;
    }
}
