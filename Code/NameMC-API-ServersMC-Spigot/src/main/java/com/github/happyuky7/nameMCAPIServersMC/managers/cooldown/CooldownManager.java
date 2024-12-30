package com.github.happyuky7.nameMCAPIServersMC.managers.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {

    private static final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public static void setCooldown(Player player, int seconds) {
        long delay = System.currentTimeMillis() + (seconds * 1000);
        cooldowns.put(player.getUniqueId(), delay);
    }

    public static boolean checkCooldown(Player player) {
        if (!cooldowns.containsKey(player.getUniqueId()) || cooldowns.get(player.getUniqueId()) <= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public static long getCooldown(Player player) {
        if (cooldowns.containsKey(player.getUniqueId())) {
            if (cooldowns.get(player.getUniqueId()) <= System.currentTimeMillis()) {
                return 0;
            }
            return (cooldowns.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000;
        }
        return 0;
    }

    public static void removeCooldown(Player player) {
        cooldowns.remove(player.getUniqueId());
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
