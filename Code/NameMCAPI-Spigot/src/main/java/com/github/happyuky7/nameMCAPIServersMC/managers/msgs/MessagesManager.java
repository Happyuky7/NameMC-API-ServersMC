package com.github.happyuky7.nameMCAPIServersMC.managers.msgs;

import com.github.happyuky7.nameMCAPIServersMC.NameMCAPIServersMC;
import com.github.happyuky7.nameMCAPIServersMC.utils.MessageColors;
import org.bukkit.entity.Player;

import java.util.List;

public class MessagesManager {

    // Get the message from the language file
    public static String getMessage(String path) {
        String message = NameMCAPIServersMC.getInstance().getLangs().getString(path);

        if (message == null) {
            return logErrorPath(path);
        }

        return MessageColors.getMsgColor(
                message
                        .replace("%prefix%", NameMCAPIServersMC.getInstance().getLangs().getString("prefix"))
                        .replace("&version&", NameMCAPIServersMC.getInstance().getDescription().getVersion())
                        .replace("&author&", NameMCAPIServersMC.getInstance().getDescription().getAuthors().get(0))
                        .replace("&vote_link&", "https://namemc.com/server/" +
                                NameMCAPIServersMC.getInstance().getConfig().getString("settings.ip-server"))
        );
    }

    // Get the message from the language file with the player
    public static String getMessage(String path, Player player) {
        String message = NameMCAPIServersMC.getInstance().getLangs().getString(path);

        if (message == null) {
            return logErrorPath(path);
        }

        return MessageColors.getMsgColor(
                message
                        .replace("%prefix%", NameMCAPIServersMC.getInstance().getLangs().getString("prefix"))
                        .replace("%player%", player.getName())
                        .replace("%uuid%", player.getUniqueId().toString())
                        .replace("&version&", NameMCAPIServersMC.getInstance().getDescription().getVersion())
                        .replace("&author&", NameMCAPIServersMC.getInstance().getDescription().getAuthors().get(0))
                        .replace("&vote_link&", "https://namemc.com/server/" +
                                NameMCAPIServersMC.getInstance().getConfig().getString("settings.ip-server"))
        );
    }

    // Get the message list from the language file
    public static String getMessageList(String path) {
        List<String> messages = NameMCAPIServersMC.getInstance().getLangs().getStringList(path);

        if (messages == null) {
            return logErrorPath(path);
        }

        // Corregir aquí el reemplazo para cada mensaje
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            messages.set(i, MessageColors.getMsgColor(
                    message
                            .replace("%prefix%", NameMCAPIServersMC.getInstance().getLangs().getString("prefix"))
                            .replace("&version&", NameMCAPIServersMC.getInstance().getDescription().getVersion())
                            .replace("&author&", NameMCAPIServersMC.getInstance().getDescription().getAuthors().get(0))
                            .replace("&vote_link&", "https://namemc.com/server/" +
                                    NameMCAPIServersMC.getInstance().getConfig().getString("settings.ip-server"))
            ));
        }

        return String.join("\n", messages);
    }

    // Get the message list from the language file with the player
    public static String getMessageList(String path, Player player) {
        List<String> messages = NameMCAPIServersMC.getInstance().getLangs().getStringList(path);

        if (messages == null) {
            return logErrorPath(path);
        }

        // Corregir aquí el reemplazo para cada mensaje
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            messages.set(i, MessageColors.getMsgColor(
                    message
                            .replace("%prefix%", NameMCAPIServersMC.getInstance().getLangs().getString("prefix"))
                            .replace("%player%", player.getName())
                            .replace("%uuid%", player.getUniqueId().toString())
                            .replace("&version&", NameMCAPIServersMC.getInstance().getDescription().getVersion())
                            .replace("&author&", NameMCAPIServersMC.getInstance().getDescription().getAuthors().get(0))
                            .replace("&vote_link&", "https://namemc.com/server/" +
                                    NameMCAPIServersMC.getInstance().getConfig().getString("settings.ip-server"))
            ));
        }

        return String.join("\n", messages);
    }

    // Get the message from the language file
    public static String logErrorPath(String path) {
        System.out.println("[NameMCAPIServersMC] [ERROR] [MSG] " + path + " is not found in the language file.");
        return MessageColors.getMsgColor("&8[&9NameMCAPIServersMC&8] &c[ERROR] &7(&f" + path + "&7) &cis not found in the language file.");
    }

}
