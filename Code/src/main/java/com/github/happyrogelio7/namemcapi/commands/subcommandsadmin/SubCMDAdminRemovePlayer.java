package com.github.happyrogelio7.namemcapi.commands.subcommandsadmin;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.managers.MessagesManager;
import com.github.happyrogelio7.namemcapi.managers.MojangAPIManager;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class SubCMDAdminRemovePlayer {

    public static void removeplayerstatus(Player player, NameMCAPI plugin){

        String uuid = MojangAPIManager.getDataMojanAPI(player);

        try {
            Scanner scanner = new Scanner((new URL("https://api.namemc.com/server/" +
                    plugin.getConfig().getString("General.IP")
                    + "/likes?profile=" + uuid).openStream()));

            boolean status = Boolean.parseBoolean(scanner.next());

            if (status == true){

                if (plugin.getConfig().getBoolean("Remove-CMD.Commands.Console-CMD", true)) {
                    ArrayList<String> rewardverify1 = (ArrayList<String>) plugin.getConfig().getStringList("Rewards.Commands.Console");
                    for (String rconsole : rewardverify1) {
                        Bukkit.dispatchCommand((CommandSender) Bukkit.getServer().getConsoleSender(), rconsole
                                .replaceAll("<player>", player.getName()));
                    }
                }

                if (plugin.getConfig().getBoolean("Remove-CMD.Commands.Player-CMD", true)) {
                    ArrayList<String> rewardverify2 = (ArrayList<String>) plugin.getConfig().getStringList("Rewards.Commands.Player");
                    for (String rplayer : rewardverify2) {
                        player.chat(rplayer.replaceAll("<player>", player.getName()));
                    }
                }

                plugin.getPlayerData().set("VOTE-ALLOW." + uuid + ".name", player.getName());
                plugin.getPlayerData().set("VOTE-ALLOW." + uuid + ".vote", false);
                plugin.getPlayerData().save();
                plugin.getPlayerData().reload();

            } else {

                player.sendMessage(MessagesManager.getSendMSG(plugin, "RemovePlayer.NO-VOTING")
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                        .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

            }

        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(MessageColors.getMsgColor("&cError: User No Premium or Error Connecting NameMC WEB."));
        }
    }


    public static void removeplayerstatusonline(Player player, NameMCAPI plugin){

        String uuid = String.valueOf(player.getUniqueId());

        try {
            Scanner scanner = new Scanner((new URL("https://api.namemc.com/server/" +
                    plugin.getConfig().getString("General.IP")
                    + "/likes?profile=" + uuid).openStream()));

            boolean status = Boolean.parseBoolean(scanner.next());

            if (status == true){

                if (plugin.getConfig().getBoolean("Remove-CMD.Commands.Console-CMD", true)) {
                    ArrayList<String> rewardverify1 = (ArrayList<String>) plugin.getConfig().getStringList("Rewards.Commands.Console");
                    for (String rconsole : rewardverify1) {
                        Bukkit.dispatchCommand((CommandSender) Bukkit.getServer().getConsoleSender(), rconsole
                                .replaceAll("<player>", player.getName()));
                    }
                }

                if (plugin.getConfig().getBoolean("Remove-CMD.Commands.Player-CMD", true)) {
                    ArrayList<String> rewardverify2 = (ArrayList<String>) plugin.getConfig().getStringList("Rewards.Commands.Player");
                    for (String rplayer : rewardverify2) {
                        player.chat(rplayer.replaceAll("<player>", player.getName()));
                    }
                }

                plugin.getPlayerData().set("VOTE-ALLOW." + uuid + ".name", player.getName());
                plugin.getPlayerData().set("VOTE-ALLOW." + uuid + ".vote", false);
                plugin.getPlayerData().save();
                plugin.getPlayerData().reload();

            } else {

                player.sendMessage(MessagesManager.getSendMSG(plugin, "RemovePlayer.NO-VOTING")
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                        .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

            }

        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(MessageColors.getMsgColor("&cError: Error Connecting NameMC WEB or User No Premium."));
        }
    }

    public static void removeplayer(Player player, NameMCAPI plugin, String args){

        Player target = Bukkit.getPlayer(args);

        /*if (plugin.getConfig().getBoolean("General.Online-Mode", true)){

            Boolean verifyvote = plugin.getPlayerData().getBoolean("VOTE-ALLOW." + target.getUniqueId() + ".vote");

            if (verifyvote == null){

                player.sendMessage(MessagesManager.getSendMSG(plugin, "RemovePlayer.PLAYER-ERROR-DATA")
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<otherplayer<", target.getName())
                        .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                        .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

            } else if (verifyvote){

                SubCMDAdminRemovePlayer.removeplayerstatusonline(target, plugin);

                player.sendMessage(MessagesManager.getSendMSG(plugin, "RemovePlayer.PLAYER-REMOVE")
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<otherplayer<", target.getName())
                        .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                        .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

            } else {

                player.sendMessage(MessagesManager.getSendMSG(plugin, "RemovePlayer.PLAYER-NO-VOTING")
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<otherplayer<", target.getName())
                        .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                        .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

            }

        }*/

        String uuid = MojangAPIManager.getDataMojanAPITarget(target.getName());

        Boolean verifyvote = plugin.getPlayerData().getBoolean("VOTE-ALLOW." + uuid + ".vote");

        if (verifyvote == null){

            player.sendMessage(MessagesManager.getSendMSG(plugin, "RemovePlayer.PLAYER-ERROR-DATA")
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<otherplayer<", target.getName())
                    .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                    .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                    .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

        } else if (verifyvote){

            SubCMDAdminRemovePlayer.removeplayerstatus(target, plugin);

            player.sendMessage(MessagesManager.getSendMSG(plugin, "RemovePlayer.PLAYER-REMOVE")
                    .replaceAll("<player<", player.getName())
                    .replaceAll("<otherplayer<", target.getName())
                    .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                    .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                    .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

        } else {

            player.sendMessage(MessagesManager.getSendMSG(plugin, "RemovePlayer.PLAYER-NO-VOTING")
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<otherplayer<", target.getName())
                    .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                    .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                    .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

        }

    }
}
