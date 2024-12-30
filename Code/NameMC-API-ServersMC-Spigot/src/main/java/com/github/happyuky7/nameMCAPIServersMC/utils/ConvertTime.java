package com.github.happyuky7.nameMCAPIServersMC.utils;

/*
 * Code by: Happyuky7
 * GitHub: https://github.com/Happyuky7
 * License: Custom
 * Link: https://github.com/Happyuky7/ConvertTimeJavaClass
 */

/**
 * A utility class for converting time values between different units.
 */

public class ConvertTime {

    /**
     * Converts a time value to milliseconds.
     *
     * This method takes an integer time value and a time unit (as a string letter),
     * and returns the equivalent time in milliseconds.
     *
     * Supported time units are:
     * - "s" for seconds → 1 second = 1000 milliseconds
     * - "m" for minutes → 1 minute = 60 seconds = 60000 milliseconds
     * - "h" for hours → 1 hour = 60 minutes = 3600000 milliseconds
     * - "d" for days → 1 day = 24 hours = 86400000 milliseconds
     *
     * If an invalid unit is passed, an exception will be thrown.
     *
     * @param time The numeric value of time (e.g., 5, 10, 1, etc.)
     * @param unit The time unit, which can be one of the following letters:
     *             - "s" for seconds
     *             - "m" for minutes
     *             - "h" for hours
     *             - "d" for days
     * @return The time converted to milliseconds
     * @throws IllegalArgumentException If the time unit is invalid
     */
    public static long convertTimeToMilliseconds(Integer time, String unit) {

        switch (unit.toLowerCase()) {
            case "s":
                // Seconds: 1 second = 1000 milliseconds
                return time * 1000;
            case "m":
                // Minutes: 1 minute = 60 seconds = 60000 milliseconds
                return time * 60000;
            case "h":
                // Hours: 1 hour = 60 minutes = 3600000 milliseconds
                return time * 3600000;
            case "d":
                // Days: 1 day = 24 hours = 86400000 milliseconds
                return time * 86400000;
            default:
                // Exception if the unit is invalid
                throw new IllegalArgumentException("Invalid time unit: " + unit.toLowerCase()
                        + ". Valid units are: 's' (seconds), 'm' (minutes), 'h' (hours), 'd' (days).");
        }
    }

    /**
     * Converts a time value to seconds.
     *
     * This method converts a given time value (in various units) to seconds.
     *
     * Supported time units are:
     * - "s" for seconds
     * - "m" for minutes → 1 minute = 60 seconds
     * - "h" for hours → 1 hour = 3600 seconds
     * - "d" for days → 1 day = 86400 seconds
     *
     * @param time The numeric value of time (e.g., 5, 10, 1, etc.)
     * @param unit The time unit, which can be one of the following letters:
     *             - "s" for seconds
     *             - "m" for minutes
     *             - "h" for hours
     *             - "d" for days
     * @return The time converted to seconds
     * @throws IllegalArgumentException If the time unit is invalid
     */
    public static long convertTimeToSeconds(Integer time, String unit) {

        switch (unit.toLowerCase()) {
            case "s":
                // Seconds: 1 second = 1 second
                return time;
            case "m":
                // Minutes: 1 minute = 60 seconds
                return time * 60;
            case "h":
                // Hours: 1 hour = 3600 seconds
                return time * 3600;
            case "d":
                // Days: 1 day = 86400 seconds
                return time * 86400;
            default:
                // Exception if the unit is invalid
                throw new IllegalArgumentException("Invalid time unit: " + unit.toLowerCase()
                        + ". Valid units are: 's' (seconds), 'm' (minutes), 'h' (hours), 'd' (days).");
        }
    }

    /**
     * Converts a time value to minutes.
     *
     * This method converts a given time value (in various units) to minutes.
     *
     * Supported time units are:
     * - "s" for seconds → 1 second = 1/60 minutes
     * - "m" for minutes → 1 minute = 1 minute
     * - "h" for hours → 1 hour = 60 minutes
     * - "d" for days → 1 day = 1440 minutes
     *
     * @param time The numeric value of time (e.g., 5, 10, 1, etc.)
     * @param unit The time unit, which can be one of the following letters:
     *             - "s" for seconds
     *             - "m" for minutes
     *             - "h" for hours
     *             - "d" for days
     * @return The time converted to minutes
     * @throws IllegalArgumentException If the time unit is invalid
     */
    public static long convertTimeToMinutes(Integer time, String unit) {

        switch (unit.toLowerCase()) {
            case "s":
                // Seconds: 1 second = 1/60 minutes
                return time / 60;
            case "m":
                // Minutes: 1 minute = 1 minute
                return time;
            case "h":
                // Hours: 1 hour = 60 minutes
                return time * 60;
            case "d":
                // Days: 1 day = 1440 minutes
                return time * 1440;
            default:
                // Exception if the unit is invalid
                throw new IllegalArgumentException("Invalid time unit: " + unit.toLowerCase()
                        + ". Valid units are: 's' (seconds), 'm' (minutes), 'h' (hours), 'd' (days).");
        }
    }
}
