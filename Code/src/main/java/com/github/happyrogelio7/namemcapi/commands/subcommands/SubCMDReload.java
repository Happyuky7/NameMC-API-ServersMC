package com.github.happyrogelio7.namemcapi.commands.subcommands;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.entity.Player;

public class SubCMDReload {

    public static void reloadAll(Player player, NameMCAPI plugin){

        plugin.getConfig().reload();
        plugin.getPlayerData().reload();
        plugin.getPlayerData().reload();

        player.sendMessage(MessageColors.getMsgColor(plugin.getLangs().getString("General.Reload-MSG.RELOAD-ALL")));
    }

    public static void reloadConfig(Player player, NameMCAPI plugin){

        plugin.getConfig().reload();

        player.sendMessage(MessageColors.getMsgColor(plugin.getLangs().getString("General.Reload-MSG.RELOAD-CONFIG")));
    }

    public static void reloadLangs(Player player, NameMCAPI plugin){

        plugin.getLangs().reload();

        player.sendMessage(MessageColors.getMsgColor(plugin.getLangs().getString("General.Reload-MSG.RELOAD-CONFIG")));
    }

    public static void reloadPlayerData(Player player, NameMCAPI plugin){

        plugin.getPlayerData().reload();

        player.sendMessage(MessageColors.getMsgColor(plugin.getLangs().getString("General.Reload-MSG.RELOAD-CONFIG")));
    }
}
