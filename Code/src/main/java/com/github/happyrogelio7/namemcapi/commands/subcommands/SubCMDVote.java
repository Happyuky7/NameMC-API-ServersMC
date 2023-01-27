package com.github.happyrogelio7.namemcapi.commands.subcommands;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.managers.MessagesManager;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class SubCMDVote {

    public static void vote(Player player, NameMCAPI plugin){

        player.sendMessage(MessagesManager.getSendMSG(plugin, "Langs.NO-VOTING")
                .replaceAll("<player>", player.getName())
                .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

        if (plugin.getConfig().getBoolean("General.Options.MSG-Player", true)) {
            if (plugin.getLangs().getBoolean("MSG-Player.MSG-TITLE.ENABLE", true)) {
                player.sendTitle(PlaceholderAPI.setPlaceholders(player, MessageColors.getMsgColor(plugin.getLangs().getString("MSG-Player.MSG-TITLE.VOTING-CMD.TITLE")
                                .replaceAll("<player>", player.getName())
                                .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                                .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                                .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")))),
                        PlaceholderAPI.setPlaceholders(player, MessageColors.getMsgColor(plugin.getLangs().getString("MSG-Player.MSG-TITLE.VOTING-CMD.SUBTITLE"))
                                .replaceAll("<player>", player.getName())
                                .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                                .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                                .replaceAll("<serverip>", plugin.getConfig().getString("General.IP"))));
            }

        }

    }
}
