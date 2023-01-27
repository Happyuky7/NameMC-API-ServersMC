package com.github.happyrogelio7.namemcapi.commands;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.commands.subcommands.SubCMDReload;
import com.github.happyrogelio7.namemcapi.commands.subcommandsadmin.*;
import com.github.happyrogelio7.namemcapi.managers.MessagesManager;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameMCAPIAdminCMD implements CommandExecutor {

    private NameMCAPI plugin;

    public NameMCAPIAdminCMD(NameMCAPI plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cThis command cannot be executed from console"));
            return true;
        }

        Player player = (Player)sender;

        if (!player.hasPermission("namemcapi.cmd.admin")){
            player.sendMessage(MessagesManager.getSendMSG(plugin, "General.No-Permission")
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                    .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                    .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));
            return true;
        }

        if (args.length == 0){
            player.sendMessage(MessageColors.getMsgColor("&9[&fNameMC&9]&r &cUsage: &f/namemcapiadmin help"));
            return true;
        }

        if (args.length > 0){

            if (args[0].equalsIgnoreCase("help")){

                SubCMDAdminHelp.help0(player, plugin);
                return true;

            } else  if (args[0].equalsIgnoreCase("placeholders")){

                SubCMDAdminPlaceholders.placeholders(player, plugin);
                return true;

            } else if (args[0].equalsIgnoreCase("reload")) {

                if (args.length > 1) {

                    if (args[1].equalsIgnoreCase("all")) {
                        SubCMDReload.reloadAll(player, plugin);
                        return true;
                    } else if (args[1].equalsIgnoreCase("config")) {
                        SubCMDReload.reloadConfig(player, plugin);
                        return true;
                    } else if (args[1].equalsIgnoreCase("msglang")) {
                        SubCMDReload.reloadLangs(player, plugin);
                        return true;
                    } else if (args[1].equalsIgnoreCase("playerdata")) {
                        SubCMDReload.reloadPlayerData(player, plugin);
                        return true;
                    }

                } else {
                    player.sendMessage(MessagesManager.getSendMSG(plugin, "Reload-MSG.RELOAD-LIST"));
                    return true;
                }

            } else  if (args[0].equalsIgnoreCase("testreward")){

                SubCMDAdminTestReward.testreward(player, plugin);
                return true;

            } else if (args[0].equalsIgnoreCase("check")){

                if (args.length == 2){
                    SubCMDAdminCheck.check(player, plugin, args[1]);
                } else  {
                    player.sendMessage(MessagesManager.getSendMSG(plugin, "Check.PLAYER-NULL"));
                }
                return true;

            } else if (args[0].equalsIgnoreCase("removeplayer")){

                if (args.length == 2){
                    SubCMDAdminRemovePlayer.removeplayer(player, plugin, args[1]);
                } else  {
                    player.sendMessage(MessagesManager.getSendMSG(plugin, "RemovePlayer.PLAYER-NULL"));
                }
                return true;

            }

        }

        return true;
    }
}
