package com.github.happyrogelio7.namemcapi.commands.subcommands;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.managers.MessagesManager;
import com.github.happyrogelio7.namemcapi.managers.MojangAPIManager;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class SubCMDVerify {

    public static void verifystatus(Player player, NameMCAPI plugin){

        String uuid = MojangAPIManager.getDataMojanAPI(player);

        try {
            Scanner scanner = new Scanner((new URL("https://api.namemc.com/server/" +
                    plugin.getConfig().getString("General.IP")
                    + "/likes?profile=" + uuid).openStream()));

            boolean status = Boolean.parseBoolean(scanner.next());

            if (status == true){

                player.sendMessage(MessagesManager.getSendMSG(plugin, "Langs.VOTING-PLAYER")
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

                plugin.getPlayerData().set("VOTE-ALLOW." + uuid + ".name", player.getName());
                plugin.getPlayerData().set("VOTE-ALLOW." + uuid + ".vote", true);
                plugin.getPlayerData().save();
                plugin.getPlayerData().reload();

            } else {

                player.sendMessage(MessagesManager.getSendMSG(plugin, "Langs.NO-VOTING")
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


    public static void verifystatusonline(Player player, NameMCAPI plugin){

        String uuid = String.valueOf(player.getUniqueId());

        try {
            Scanner scanner = new Scanner((new URL("https://api.namemc.com/server/" +
                    plugin.getConfig().getString("General.IP")
                    + "/likes?profile=" + uuid).openStream()));

            boolean status = Boolean.parseBoolean(scanner.next());

            if (status == true){

                player.sendMessage(MessagesManager.getSendMSG(plugin, "Langs.VOTING-PLAYER")
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

                plugin.getPlayerData().set("VOTE-ALLOW." + uuid + ".name", player.getName());
                plugin.getPlayerData().set("VOTE-ALLOW." + uuid + ".vote", true);
                plugin.getPlayerData().save();
                plugin.getPlayerData().reload();

            } else {

                player.sendMessage(MessagesManager.getSendMSG(plugin, "Langs.NO-VOTING")
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

    public static void verify0(Player player, NameMCAPI plugin){

        if (plugin.getConfig().getBoolean("General.Online-Mode", true)){

            String verifyvote = plugin.getPlayerData().getString("VOTE-ALLOW." + player.getUniqueId() + ".vote");

            if (!(verifyvote == null)){

                player.sendMessage(MessagesManager.getSendMSG(plugin, "Langs.VOTING-ALLOW").replaceAll("<player>", player.getName())
                        .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                        .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

            } else {

                SubCMDVerify.verifystatusonline(player, plugin);

            }

        }

        String uuid = MojangAPIManager.getDataMojanAPI(player);

        String verifyvote = plugin.getPlayerData().getString("VOTE-ALLOW." + uuid + ".vote");

        //Boolean verifyvote1 = plugin.getPlayerData().getBoolean("VOTE-ALLOW." + uuid + ".vote");

        if (uuid == null){
            System.out.println("Plugin ERROR x002");
            player.sendMessage(MessageColors.getMsgColor("&cPlugin ERROR x002"));
        }

        if (!(verifyvote == null)){

            player.sendMessage(MessagesManager.getSendMSG(plugin, "Langs.VOTING-ALLOW").replaceAll("<player>", player.getName())
                    .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                    .replaceAll(MessageColors.getMsgColor("<prefix>"), plugin.getLangs().getString("General.Prefix"))
                    .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));

        } else {

            SubCMDVerify.verifystatus(player, plugin);

        }

    }
}
