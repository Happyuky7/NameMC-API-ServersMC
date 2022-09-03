package com.github.happyrogelio7.namemcapi.commands.subcommandsadmin;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.entity.Player;

public class SubCMDAdminHelp {

    public static void help0(Player player, NameMCAPI plugin){
        String admincmd =

                "&8===================&9[&fNameMC&9]&8===================" + "\n"
                        + "&r " + "\n"
                        + "&a&l Author:&r " + plugin.creator + "\n"
                        + "&9&l Version:&r " + plugin.version + "\n"
                        + "&d&l GitHub:&r https://github.com/HappyRogelio7/" + "\n"
                        + "&6&l Web:&r https://happyrogelio7.github.io/HappyRogelio7/index" + "\n"
                        + "&b&l Plugin:&r https://github.com/HappyRogelio7/NameMC-API-ServersMC" + "\n"
                        + "&r " + "\n"
                        + "&c&l Usage: &6/namemcapiadmin help &7(Help Command plugin.)" + "\n"
                        + "&c&l Usage: &6/namemcapiadmin placeholders &7(PlaceholderAPI Variables.)" + "\n"
                        + "&c&l Usage: &6/namemcapiadmin reload <args> &7(Reload configs plugin.)" + "\n"
                        + "&c&l Usage: &6/namemcapiadmin testreward &7(Test if he gives you the award | Command especially if you are a &cNon Premium player&7.)" + "\n"
                        + "&c&l Usage: &6/namemcapiadmin check <player> &7(Check manually if a player has voted on the server.)" + "\n"
                        + "&c&l Usage: &6/namemcapiadmin removeplayer <player> &7(Remove player vote list)" + "\n"
                        + "&r " + "\n"
                        + "&8===================&9[&fNameMC&9]&8==================="

                ;

        player.sendMessage(MessageColors.getMsgColor(admincmd));
    }
}
