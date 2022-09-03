package com.github.happyrogelio7.namemcapi.commands.subcommandsadmin;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.entity.Player;

public class SubCMDAdminPlaceholders {

    public static void placeholders(Player player, NameMCAPI plugin){

        String placeholders =

                "&8===================&9[&fNameMC&9]&8===================" + "\n"
                        + "&r " + "\n"
                        + "&a&l Author:&r " + plugin.creator + "\n"
                        + "&9&l Version:&r " + plugin.version + "\n"
                        + "&d&l GitHub:&r https://github.com/HappyRogelio7/" + "\n"
                        + "&6&l Web:&r https://happyrogelio7.github.io/HappyRogelio7/index" + "\n"
                        + "&b&l Plugin:&r https://github.com/HappyRogelio7/NameMC-API-ServersMC" + "\n"
                        + "&r " + "\n"
                        + "&d PlaceholderAPI or Placeholders Plugin: &rhttps://github.com/PlaceholderAPI/PlaceholderAPI/wiki/Placeholders#namemc-api-serversmc" + "\n"
                        + "&r " + "\n"
                        + "&&d Placeholders:" + "\n"
                        + "&r " + "\n"
                        + "&r - https://github.com/HappyRogelio7/NameMC-API-ServersMC/wiki/Placeholder-and-PlaceholderAPI#placeholder" + "\n"
                        + "&r " + "\n"
                        + "&d PlaceholderAPI:" + "\n"
                        + "&r " + "\n"
                        + "&c&l - &f%namemcapi_prefix%" + "\n"
                        + "&c&l - &f%namemcapi_namemcweb%" + "\n"
                        + "&c&l - &f%namemcapi_ip%" + "\n"
                        + "&c&l - &f%namemcapi_votewebip%" + "\n"
                        + "&c&l - &f%namemcapi_player%" + "\n"
                        + "&c&l - &f%namemcapi_playeruuid%" + "\n"
                        + "&c&l - &f%namemcapi_votestatusplayer%" + "\n"
                        + "&r " + "\n"
                        + "&8===================&9[&fNameMC&9]&8==================="

                ;

        player.sendMessage(MessageColors.getMsgColor(placeholders));

    }
}
