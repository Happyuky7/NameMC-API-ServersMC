package com.github.happyuky7.nameMCAPIServersMC.managers;

import com.github.happyuky7.nameMCAPIServersMC.NameMCAPIServersMC;
import org.bukkit.entity.Player;

import java.util.List;

public class RewardsManager {

    // Give rewards to the player
    public static void give(Player player) {

        if (NameMCAPIServersMC.getInstance().getConfig().getBoolean("rewards.console-cmd.enabled")) {
            consoleCMDSRewards(player);
        }

        if (NameMCAPIServersMC.getInstance().getConfig().getBoolean("rewards.player-cmd.enabled")) {
            playerCMDSRewards(player);
        }

        if (NameMCAPIServersMC.getInstance().getConfig().getBoolean("rewards.player-msg.enabled")) {
            playerMSGRewards(player);
        }

    }

    // Console commands rewards
    public static void consoleCMDSRewards(Player player) {
        List<String> commands = NameMCAPIServersMC.getInstance().getConfig().getStringList("rewards.console-cmd.commands");

        for (String command : commands) {
            NameMCAPIServersMC.getInstance().getServer().dispatchCommand(
                    NameMCAPIServersMC.getInstance().getServer().getConsoleSender(),
                    command
                            .replace("%player%", player.getName())
                            .replace("%uuid%", player.getUniqueId().toString()
                            ));
        }
    }

    // Player commands rewards
    public static void playerCMDSRewards(Player player) {
        List<String> commands = NameMCAPIServersMC.getInstance().getConfig().getStringList("rewards.player-cmd.commands");

        for (String command : commands) {
            player.performCommand(
                    command
                            .replace("%player%", player.getName())
                            .replace("%uuid%", player.getUniqueId().toString()
                            ));
        }
    }

    // Player messages rewards
    public static void playerMSGRewards(Player player) {
        List<String> messages = NameMCAPIServersMC.getInstance().getConfig().getStringList("rewards.player-msg.messages");

        for (String message : messages) {
            player.chat(
                    message
                            .replace("%player%", player.getName())
                            .replace("%uuid%", player.getUniqueId().toString()
                            ));
        }
    }

}
