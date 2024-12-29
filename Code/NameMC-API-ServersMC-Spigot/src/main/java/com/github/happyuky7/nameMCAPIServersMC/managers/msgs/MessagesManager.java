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

        return MessageColors.getMsgColor(message);
    }

    // Get the message from the language file with the player
    public static String getMessage(String path, Player player) {
        String message = NameMCAPIServersMC.getInstance().getLangs().getString(path);

        if (message == null) {
            return logErrorPath(path);
        }

        return MessageColors.getMsgColor(
                message
                        .replace("%player%", player.getName())
                        .replace("%uuid%", player.getUniqueId().toString())
        );
    }

    // Get the message list from the language file
    public static String getMessageList(String path) {
        List<String> messages = NameMCAPIServersMC.getInstance().getLangs().getStringList(path);

        if (messages == null) {
            return logErrorPath(path);
        }

        for (String message : messages) {
            message = MessageColors.getMsgColor(message);
        }

        return String.join("\n", (Iterable) messages);
    }

    // Get the message list from the language file with the player
    public static String getMessageList(String path, Player player) {
        List<String> messages = NameMCAPIServersMC.getInstance().getLangs().getStringList(path);

        if (messages == null) {
            return logErrorPath(path);
        }

        for (String message : messages) {
            message = MessageColors.getMsgColor(
                    message
                            .replace("%player%", player.getName())
                            .replace("%uuid%", player.getUniqueId().toString())
            );
        }

        return String.join("\n", (Iterable) messages);
    }

    // Get the message from the language file
    public static String logErrorPath(String path) {
        System.out.println("[NameMCAPIServersMC] [ERROR] [MSG] " + path + " is not found in the language file.");
        return MessageColors.getMsgColor("&8[&9NameMCAPIServersMC&8] &c[ERROR] &7(&f" + path + "&7) &cis not found in the language file.");
    }

}
