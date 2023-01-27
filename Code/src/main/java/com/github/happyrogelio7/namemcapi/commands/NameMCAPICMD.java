package com.github.happyrogelio7.namemcapi.commands;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.commands.subcommands.*;
import com.github.happyrogelio7.namemcapi.managers.MessagesManager;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameMCAPICMD implements CommandExecutor {

    private NameMCAPI plugin;

    public NameMCAPICMD(NameMCAPI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cThis command cannot be executed from console."));
            return true;
        }

        Player player = (Player)sender;

        if (args.length == 0){
            player.sendMessage(MessagesManager.getSendMSG(plugin, "Langs.CUSTOM-ERROR"));
            return true;
        }


        if (args.length > 0){

            if (args[0].equalsIgnoreCase("help")) {

                SubCMDHelp.help0(player, plugin);
                return true;

            } else if (args[0].equalsIgnoreCase("admin")){

                if (!player.hasPermission("namemcapi.cmd.admin")){
                    player.sendMessage(MessagesManager.getSendMSG(plugin, "General.No-Permission")
                            .replaceAll("<player>", player.getName())
                            .replaceAll("<namemcwebvote>", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                            .replaceAll(MessageColors.getMsgColor("<prefix>"), MessageColors.getMsgColor(plugin.getLangs().getString("General.Prefix")))
                            .replaceAll("<serverip>", plugin.getConfig().getString("General.IP")));
                    return true;
                }

                player.sendMessage(MessageColors.getMsgColor("&9[&fNameMC&9]&r &cUsage: &f/namemcapiadmin help"));
                return true;

            } else if (args[0].equalsIgnoreCase("info")){

                SubCMDInfo.info0(player, plugin);
                return true;

            } else  if (args[0].equalsIgnoreCase("vote")){

                SubCMDVote.vote(player, plugin);
                return true;

            } else  if (args[0].equalsIgnoreCase("verify")){

                SubCMDVerify.verify0(player, plugin);
                return true;

            }

            return true;
        }


        return true;
    }
}
