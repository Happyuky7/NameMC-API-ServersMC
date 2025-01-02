package com.github.happyuky7.nameMCAPIServersMCCommon.api;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {

    public static final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public static void setCooldown(UUID uuid, int seconds) {
        long delay = System.currentTimeMillis() + (seconds * 1000);
        cooldowns.put(uuid, delay);
    }

    public static boolean checkCooldown(UUID uuid) {
        if (!cooldowns.containsKey(uuid) || cooldowns.get(uuid) <= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public static long getCooldown(UUID uuid) {
        if (cooldowns.containsKey(uuid)) {
            if (cooldowns.get(uuid) <= System.currentTimeMillis()) {
                return 0;
            }
            return (cooldowns.get(uuid) - System.currentTimeMillis()) / 1000;
        }
        return 0;
    }

    public static void removeCooldown(UUID uuid) {
        cooldowns.remove(uuid);
    }

    public static void clearCooldowns() {
        cooldowns.clear();
    }

    public static String getFormattedCooldown(long time) {
        long hours = time / 3600;
        long minutes = time / 60;
        long seconds = time % 60;

        if (hours > 0) {
            return hours + "h " + minutes + "m " + seconds + "s";
        } else if (minutes > 0) {
            return minutes + "m " + seconds + "s";
        } else if (seconds > 0) {
            return seconds + "s";
        } else {
            return "Invalid time: " + time;
        }
    }



}
