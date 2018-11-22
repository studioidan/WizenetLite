package com.studioidan.popapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by PopApp_laptop on 13/01/2016.
 */
public class DateUtils {

    /**
     * example for input: "yyyy-MM-dd'T'HH:mm:ss"
     * example for output: "yyyy-MM-dd HH:mm:ss"
     */
    public static String convertDateFormat(String date, String inputFormatStr, String outputFormatStr) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputFormatStr);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputFormatStr);
            Date d = inputFormat.parse(date);
            String answer = outputFormat.format(d);
            return answer;
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getCurrentDate(String outputFormatStr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(outputFormatStr);
            String answer = df.format(Calendar.getInstance().getTime());
            return answer;
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getDateFromNumber(long time, String outFormat) {
        SimpleDateFormat df = new SimpleDateFormat(outFormat);
        String answer = df.format(new Date(time));
        return answer;
    }
}
