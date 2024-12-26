package com.github.happyuky7.nameMCAPIServersMC.commands;

import com.github.happyuky7.nameMCAPIServersMC.NameMCAPIServersMC;
import com.github.happyuky7.nameMCAPIServersMC.managers.VerifyManager;
import com.github.happyuky7.nameMCAPIServersMC.managers.data.YamlDataManager;
import com.github.happyuky7.nameMCAPIServersMC.utils.MessageColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NameMCAPICMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageColors.getMsgColor("&cYou must be a player to execute this command!"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &9&lNameMCAPIServersMC &7- &fHelp"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc &7- &fVote for the server!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc help &7- &fHelp command!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc info &7- &fPlugin information!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc vote &7- &fVote for the server!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc verify &7- &fVerify your vote!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc reload &7- &fReload the plugin!"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &9&lNameMCAPIServersMC &7- &fHelp"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc &7- &fVote for the server!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc help &7- &fHelp command!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc vote &7- &fVote for the server!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc verify &7- &fVerify your vote!"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            return true;
        }

        if (args[0].equalsIgnoreCase("info")) {
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &9&lNameMCAPIServersMC &7- &fInformation"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &7- &aVersion:&f 2.0.0-DEV-100"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &aAuthor:&f Happyuky7"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &aGithub:&f https://github.com/Happyuky7/NameMC-API-ServersMC"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            return true;
        }

        if (args[0].equalsIgnoreCase("vote")) {
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &9&lNameMCAPIServersMC &7- &fVote"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &7- &fClick the link below to vote for the server!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &fhttps://namemc.com/server/" +
                    NameMCAPIServersMC.getInstance().getConfig().getString("settings.ip-server")
            ));
            player.sendMessage(MessageColors.getMsgColor(" "));
            return true;
        }

        if (args[0].equalsIgnoreCase("verify")) {

            if (VerifyManager.verify(NameMCAPIServersMC.typeData, player.getName(), player.getUniqueId().toString(), NameMCAPIServersMC.getInstance().getConfig().getString("settings.ip-server"))) {

                player.sendMessage(MessageColors.getMsgColor(" "));
                player.sendMessage(MessageColors.getMsgColor(" &9&lNameMCAPIServersMC &7- &fVerify"));
                player.sendMessage(MessageColors.getMsgColor(" "));
                player.sendMessage(MessageColors.getMsgColor(" &7- &fYou have successfully verified your vote!"));
                player.sendMessage(MessageColors.getMsgColor(" "));
            } else {
                player.sendMessage(MessageColors.getMsgColor(" "));
                player.sendMessage(MessageColors.getMsgColor(" &9&lNameMCAPIServersMC &7- &fVerify"));
                player.sendMessage(MessageColors.getMsgColor(" "));
                player.sendMessage(MessageColors.getMsgColor(" &7- &fYou have not voted yet!"));
                player.sendMessage(MessageColors.getMsgColor(" "));
            }

            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {

            if (!player.hasPermission("namemc.reload")) {
                player.sendMessage(MessageColors.getMsgColor("&cYou do not have permission to execute this command!"));
            }

            NameMCAPIServersMC.getInstance().getConfig().reload();
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &9&lNameMCAPIServersMC &7- &fReload"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &7- &fThe plugin has been reloaded!"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            return true;
        }

        return true;

    }
}
