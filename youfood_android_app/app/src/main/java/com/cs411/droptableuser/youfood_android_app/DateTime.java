package com.cs411.droptableuser.youfood_android_app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by JunYoung on 2018. 4. 17..
 */

public class DateTime {
    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        // We want GMT since the server uses GMT for all its time stamps as well
        //dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        Date date = new Date();

        return dateFormat.format(date);
    }

    public static String getFirstDateOfWeek(String currentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar sunday = Calendar.getInstance();
        try {
            sunday.setTime(dateFormat.parse(currentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sunday.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        sunday.set(Calendar.HOUR_OF_DAY,0);
        sunday.set(Calendar.MINUTE,0);
        sunday.set(Calendar.SECOND,0);
        Date firstDateOfWeek = sunday.getTime();

        return dateFormat.format(firstDateOfWeek);
    }

    public static String getLastDateOfWeek(String currentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar saturday = Calendar.getInstance();
        try {
            saturday.setTime(dateFormat.parse(currentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        saturday.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
        saturday.set(Calendar.HOUR_OF_DAY,23);
        saturday.set(Calendar.MINUTE,59);
        saturday.set(Calendar.SECOND,59);
        Date lastDayMonthOfWeek = saturday.getTime();

        return dateFormat.format(lastDayMonthOfWeek);
    }


    public static String getFirstDayMonthOfWeek(String currentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar sunday = Calendar.getInstance();
        try {
            sunday.setTime(dateFormat.parse(currentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sunday.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        sunday.set(Calendar.HOUR_OF_DAY,0);
        sunday.set(Calendar.MINUTE,0);
        sunday.set(Calendar.SECOND,0);
        Date firstDayMonthOfWeek = sunday.getTime();

        return dateFormat.format(firstDayMonthOfWeek);
    }

    public static String getLastDayMonthOfWeek(String currentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar saturday = Calendar.getInstance();
        try {
            saturday.setTime(dateFormat.parse(currentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        saturday.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
        saturday.set(Calendar.HOUR_OF_DAY,23);
        saturday.set(Calendar.MINUTE,59);
        saturday.set(Calendar.SECOND,59);
        Date lastDayMonthOfWeek = saturday.getTime();

        return dateFormat.format(lastDayMonthOfWeek);
    }
}
