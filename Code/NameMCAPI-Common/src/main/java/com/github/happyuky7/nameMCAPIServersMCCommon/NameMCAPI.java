package com.github.happyuky7.nameMCAPIServersMCCommon;

import com.github.happyuky7.nameMCAPIServersMCCommon.api.NameMCAPIGET;
import com.github.happyuky7.nameMCAPIServersMCCommon.api.MojangAPIManager;
import com.github.happyuky7.nameMCAPIServersMCCommon.api.DateInfo;

/**
 * The main class of the NameMCAPI which provides various utility methods to interact with external APIs
 * such as retrieving vote information, Mojang UUIDs, and date information.
 * <p>
 * This class follows the Singleton pattern to ensure there is only one instance of the API in use.
 * </p>
 */
public class NameMCAPI {

    private static NameMCAPI instance;

    /**
     * Returns the singleton instance of the NameMCAPI class.
     * If the instance is not yet created, it will be instantiated.
     *
     * @return the singleton instance of the NameMCAPI class.
     */
    public static NameMCAPI getInstance() {
        if (instance == null) {
            instance = new NameMCAPI();
        }
        return instance;
    }

    /**
     * Returns a new instance of the DateAPI class, which provides methods to get date information.
     *
     * @return a new DateAPI instance.
     */
    public DateAPI getDate() {
        return new DateAPI();
    }

    /**
     * Retrieves vote information for a player based on their UUID and the server IP.
     *
     * @param uuid the UUID of the player.
     * @param serverip the IP address of the server.
     */
    public void getVote(String uuid, String serverip) {
        try {
            NameMCAPIGET.getVote(uuid, serverip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the Mojang UUID for a given Minecraft player name.
     *
     * @param name the name of the Minecraft player.
     */
    public void getMojangUUID(String name) {
        try {
            MojangAPIManager.getUUID(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The DateAPI class provides methods for retrieving various date-related information.
     * The date methods are based on the current system date and time.
     */
    public class DateAPI {

        /**
         * Returns the current system date as a string.
         *
         * @return the current system date.
         */
        public String date() {
            return DateInfo.getSystemDate();
        }

        /**
         * Returns the current system date formatted according to the specified format.
         *
         * @param format the date format.
         * @return the formatted date string.
         */
        public String date(String format) {
            return DateInfo.getSystemDate(format);
        }

        /**
         * Returns the current system day as a string.
         *
         * @return the current system day.
         */
        public String dateDay() {
            return DateInfo.getSystemDateDay();
        }

        /**
         * Returns the current system month as a string.
         *
         * @return the current system month.
         */
        public String dateMonth() {
            return DateInfo.getSystemDateMonth();
        }

        /**
         * Returns the current system year as a string.
         *
         * @return the current system year.
         */
        public String dateYear() {
            return DateInfo.getSystemDateYear();
        }

        /**
         * Returns the current system day formatted according to the specified format.
         *
         * @param format the date format.
         * @return the formatted system day string.
         */
        public String dateDay(String format) {
            return DateInfo.getSystemDateDay(format);
        }

        /**
         * Returns the current system month formatted according to the specified format.
         *
         * @param format the date format.
         * @return the formatted system month string.
         */
        public String dateMonth(String format) {
            return DateInfo.getSystemDateMonth(format);
        }

        /**
         * Returns the current system year formatted according to the specified format.
         *
         * @param format the date format.
         * @return the formatted system year string.
         */
        public String dateYear(String format) {
            return DateInfo.getSystemDateYear(format);
        }
    }
}
