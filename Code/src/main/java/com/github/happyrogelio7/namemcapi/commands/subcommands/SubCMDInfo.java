package com.github.happyrogelio7.namemcapi.commands.subcommands;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.entity.Player;

public class SubCMDInfo {

    public static void info0(Player player, NameMCAPI plugin){

        String infocmd =

                "&8===================&9[&fNameMC&9]&8===================" + "\n"
                        + "&r " + "\n"
                        + "&a    Creator and Plugin:" + "\n"
                        + "&a&l Author:&r " + plugin.creator + "\n"
                        + "&9&l Version:&r " + plugin.version + "\n"
                        + "&d&l GitHub:&r https://github.com/HappyRogelio7/" + "\n"
                        + "&6&l Web:&r https://happyrogelio7.github.io/HappyRogelio7/index" + "\n"
                        + "&r " + "\n"
                        + "&b    Plugin:" + "\n"
                        + "&d PlaceholderAPI: &rhttps://github.com/PlaceholderAPI/PlaceholderAPI/wiki/Placeholders#namemc-api-serversmc" + "\n"
                        + "&b&l Plugin Download:&r https://github.com/HappyRogelio7/NameMC-API-ServersMC" + "\n"
                        + "&r " + "\n"
                        + "&9      NameMC API:" + "\n"
                        + "&d&l GitHub:&r https://github.com/jaqobb/namemc-api" + "\n"
                        + "&c&l Web:&r https://namemc.com" + "\n"
                        + "&2&l Servers:&r https://namemc.com/server/" + "\n"
                        + "&b&l Profile:&r https://namemc.com/profile/" + "\n"
                        + "&e&l Skins:&r https://namemc.com/skin/" + "\n"
                        + "&r " + "\n"
                        + "&8===================&9[&fNameMC&9]&8==================="

                ;
        player.sendMessage(MessageColors.getMsgColor(infocmd));
    }
}
