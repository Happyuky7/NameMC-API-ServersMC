package com.github.happyrogelio7.namemcapi.commands.subcommandsadmin;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.commands.subcommands.SubCMDVerify;
import com.github.happyrogelio7.namemcapi.managers.MessagesManager;
import com.github.happyrogelio7.namemcapi.managers.MojangAPIManager;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SubCMDAdminCheck {

    public static void check(Player player, NameMCAPI plugin, String args){

        Player target = Bukkit.getPlayer(args);

        if (plugin.getConfig().getBoolean("General.Online-Mode", true)){

            Boolean verifyvote = plugin.getPlayerData().getBoolean("VOTE-ALLOW." + target.getUniqueId() + ".vote");

            if (verifyvote == null){

                player.sendMessage(MessagesManager.getSendMSG(plugin, "Check.PLAYER-ERROR-DATA")
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<otherplayer<", target.getName())
                        .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                        .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

            } else if (verifyvote){

                player.sendMessage(MessagesManager.getSendMSG(plugin, "Check.PLAYER-VOTING")
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<otherplayer<", target.getName())
                        .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                        .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

            } else {

                player.sendMessage(MessagesManager.getSendMSG(plugin, "Check.PLAYER-NO-VOTING")
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<otherplayer<", target.getName())
                        .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                        .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

            }

        }

        String uuid = MojangAPIManager.getDataMojanAPITarget(target.getName());

        Boolean verifyvote = plugin.getPlayerData().getBoolean("VOTE-ALLOW." + uuid + ".vote");

        if (verifyvote == null){

            player.sendMessage(MessagesManager.getSendMSG(plugin, "Check.PLAYER-ERROR-DATA")
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<otherplayer<", target.getName())
                    .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                    .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                    .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

        } else if (verifyvote){

            player.sendMessage(MessagesManager.getSendMSG(plugin, "Check.PLAYER-VOTING")
                    .replaceAll("<player<", player.getName())
                    .replaceAll("<otherplayer<", target.getName())
                    .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                    .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                    .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

        } else {

            player.sendMessage(MessagesManager.getSendMSG(plugin, "Check.PLAYER-NO-VOTING")
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<otherplayer<", target.getName())
                    .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                    .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                    .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

        }

    }
}
