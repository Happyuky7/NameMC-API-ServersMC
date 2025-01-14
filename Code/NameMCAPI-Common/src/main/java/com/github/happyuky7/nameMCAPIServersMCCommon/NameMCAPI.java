package com.github.happyuky7.nameMCAPIServersMCCommon;

import com.github.happyuky7.nameMCAPIServersMCCommon.api.*;

import java.util.HashMap;
import java.util.UUID;

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
     * The DateAPI instance which provides methods to get date information.
     */

    public static final DateAPI DATE_API = new NameMCAPI().new DateAPI();

    /**
     * The CooldownAPI instance which provides methods to manage cooldowns.
     */

    public static final CooldownAPI COOLDOWN_API = new NameMCAPI().new CooldownAPI();

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
     * The UUIDFormater instance which provides methods to format UUIDs.
     *
     * @return the UUIDFormater instance.
     */
    public static final UUIDFormater UUID_FORMATER = new NameMCAPI().new UUIDFormater();

    /**
     * Returns a new instance of the DateAPI class, which provides methods to get date information.
     *
     * @return a new DateAPI instance.
     */
    public DateAPI getDate() {
        return new DateAPI();
    }

    /**
     * Returns a new instance of the CooldownAPI class, which provides methods to manage cooldowns.
     *
     * @return a new CooldownAPI instance.
     */
    public CooldownAPI getCooldown() {
        return new CooldownAPI();
    }

    /**
     * Retrieves vote information for a player based on their UUID and the server IP.
     *
     * @param uuid the UUID of the player.
     * @param serverip the IP address of the server.
     */
    public boolean getVote(UUID uuid, String serverip) {
        try {
            if (uuid == null || serverip == null) {
                throw new IllegalArgumentException("UUID or Server IP cannot be null.");
                //return false;
            }
            return NameMCAPIGET.getVote(uuid, serverip);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the Mojang UUID for a given Minecraft player name.
     *
     * @param name the name of the Minecraft player.
     */
    public String getMojangUUID(String name) {
        try {
            return MojangAPIManager.getUUID(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the Mojang UUID for a given Minecraft player name.
     *
     * @param name the name of the Minecraft player.
     * @return
     */
    public UUID getMojangUUID(String name, boolean error) {
        try {
            return UUIDFormated.fromStringWithHyphens(MojangAPIManager.getUUID(name, error));
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }


    /**
     * The UUIDFormater class provides methods to format UUIDs.
     */
    public class UUIDFormater {

        public UUID fromStringWithHyphens(String uuid) {
            return UUIDFormated.fromStringWithHyphens(uuid);
        }

        public UUID fromStringWithoutHyphens(String uuid) {
            return UUIDFormated.fromStringWithoutHyphens(uuid);
        }

        public String toStringWithHyphens(UUID uuid) {
            return UUIDFormated.toStringWithHyphens(uuid);
        }

        public String toStringWithoutHyphens(UUID uuid) {
            return UUIDFormated.toStringWithoutHyphens(uuid);
        }

        public boolean isValidUUID(String uuid) {
            return UUIDFormated.isValidUUID(uuid);
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


    /**
     * The CooldownAPI class provides methods for managing cooldowns.
     */
    public class CooldownAPI {

        /**
         * HashMap containing the cooldowns for each player.
         */

        public HashMap<UUID, Long> cooldowns = CooldownManager.cooldowns;

        /**
         * Sets a cooldown for a player based on their UUID.
         *
         * @param uuid the UUID of the player.
         * @param seconds the duration of the cooldown in seconds.
         */
        public void setCooldown(UUID uuid, int seconds) {
            CooldownManager.setCooldown(uuid, seconds);
        }

        /**
         * Checks if a player has a cooldown based on their UUID.
         *
         * @param uuid the UUID of the player.
         * @return true if the player has no cooldown, false otherwise.
         */
        public boolean checkCooldown(UUID uuid) {
            return CooldownManager.checkCooldown(uuid);
        }

        /**
         * Gets the remaining time of a player's cooldown based on their UUID.
         *
         * @param uuid the UUID of the player.
         * @return the remaining time of the player's cooldown in seconds.
         */
        public long getCooldown(UUID uuid) {
            return CooldownManager.getCooldown(uuid);
        }

        /**
         * Removes the cooldown of a player based on their UUID.
         *
         * @param uuid the UUID of the player.
         */
        public void removeCooldown(UUID uuid) {
            CooldownManager.removeCooldown(uuid);
        }

        /**
         * Clears all cooldowns.
         */
        public void clearCooldowns() {
            CooldownManager.clearCooldowns();
        }

        /**
         * Returns the formatted cooldown time in hours, minutes, and seconds.
         *
         * @param time the time in seconds.
         * @return the formatted cooldown time.
         */
        public String getFormattedCooldown(long time) {
            return CooldownManager.getFormattedCooldown(time);
        }
    }
}
