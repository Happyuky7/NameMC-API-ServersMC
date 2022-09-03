package com.github.happyrogelio7.namemcapi.commands.subcommandsadmin;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.managers.MessagesManager;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SubCMDAdminTestReward {

    public static void testreward(Player player, NameMCAPI plugin){

        player.sendMessage(MessagesManager.getSendMSG(plugin, "General.TestReward.Default")
                .replaceAll("<player>", player.getName())
                .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

        if (plugin.getConfig().getBoolean("General.Options.MSG-Player", true)) {
            if (plugin.getLangs().getBoolean("MSG-Player.MSG-TITLE.ENABLE", true)) {
                player.sendTitle(PlaceholderAPI.setPlaceholders(player, MessageColors.getMsgColor(plugin.getLangs().getString("MSG-Player.MSG-TITLE.VOTING-PLAYER.TITLE"))),
                        PlaceholderAPI.setPlaceholders(player, MessageColors.getMsgColor(plugin.getLangs().getString("MSG-Player.MSG-TITLE.VOTING-PLAYER.SUBTITLE"))));
            }
        }

        if (plugin.getConfig().getBoolean("Rewards.Commands.Console-CMD", true)) {
            ArrayList<String> rewardverify1 = (ArrayList<String>) plugin.getConfig().getStringList("Rewards.Commands.Console");
            for (String rconsole : rewardverify1) {
                Bukkit.dispatchCommand((CommandSender) Bukkit.getServer().getConsoleSender(), rconsole
                        .replaceAll("<player>", player.getName()));
            }
        }

        if (plugin.getConfig().getBoolean("Rewards.Commands.Player-CMD", true)) {
            ArrayList<String> rewardverify2 = (ArrayList<String>) plugin.getConfig().getStringList("Rewards.Commands.Player");
            for (String rplayer : rewardverify2) {
                player.chat(rplayer.replaceAll("<player>", player.getName()));
            }
        }
    }
}
