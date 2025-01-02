package com.github.happyuky7.nameMCAPIServersMCCommon.api;

import java.time.LocalDate;

public class DateInfo {

    public static String getSystemDate() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();
        String formattedDate = String.format("%02d", new Object[] { Integer.valueOf(day) }) + "/" + String.format("%02d", new Object[] { Integer.valueOf(month) }) + "/" + year;
        return formattedDate;
    }

    public static String getSystemDate(String format) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();
        String formattedDate = format.replace("D", String.format("%02d", new Object[] { Integer.valueOf(day) })).replace("M", String.format("%02d", new Object[] { Integer.valueOf(month) })).replace("Y", String.valueOf(year));
        return formattedDate;
    }

    public static String getSystemDateDay() {
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        String formattedDate = String.format("%02d", new Object[] { Integer.valueOf(day) });
        return formattedDate;
    }

    public static String getSystemDateMonth() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        String formattedDate = String.format("%02d", new Object[] { Integer.valueOf(month) });
        return formattedDate;
    }

    public static String getSystemDateYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        String formattedDate = String.valueOf(year);
        return formattedDate;
    }

    public static String getSystemDateDay(String format) {
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        String formattedDate = format.replace("D", String.format("%02d", new Object[] { Integer.valueOf(day) }));
        return formattedDate;
    }

    public static String getSystemDateMonth(String format) {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        String formattedDate = format.replace("M", String.format("%02d", new Object[] { Integer.valueOf(month) }));
        return formattedDate;
    }

    public static String getSystemDateYear(String format) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        String formattedDate = format.replace("Y", String.valueOf(year));
        return formattedDate;
    }

}

