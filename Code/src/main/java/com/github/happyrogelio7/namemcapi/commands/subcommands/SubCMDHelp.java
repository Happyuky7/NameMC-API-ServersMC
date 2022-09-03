package com.github.happyrogelio7.namemcapi.commands.subcommands;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.entity.Player;

public class SubCMDHelp {

    public static void help0(Player player, NameMCAPI plugin){
        String helpcmd =

                "&8===================&9[&fNameMC&9]&8===================" + "\n"
                        + "&r " + "\n"
                        + "&a&l Author:&r " + plugin.creator + "\n"
                        + "&9&l Version:&r " + plugin.version + "\n"
                        + "&d&l GitHub:&r https://github.com/HappyRogelio7/" + "\n"
                        + "&6&l Web:&r https://happyrogelio7.github.io/HappyRogelio7/index" + "\n"
                        + "&b&l Plugin:&r https://github.com/HappyRogelio7/NameMC-API-ServersMC" + "\n"
                        + "&r " + "\n"
                        + "&c&l Usage: &6/namemc help &7(Help Command plugin.)" + "\n"
                        + "&c&l Usage: &6/namemc admin &7(Admin Command plugin.)" + "\n"
                        + "&c&l Usage: &6/namemc info &7(information about the creator of the plugin and plugin and the NameMC public api.)" + "\n"
                        + "&c&l Usage: &6/namemc vote &7(Vote Command.)" + "\n"
                        + "&c&l Usage: &6/namemc verify &7(Check your vote.)" + "\n"
                        + "&r " + "\n"
                        + "&8===================&9[&fNameMC&9]&8===================";

        player.sendMessage(MessageColors.getMsgColor(helpcmd));
    }
}
