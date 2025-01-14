package com.github.happyuky7.nameMCAPIServersMC.commands;

import com.github.happyuky7.nameMCAPIServersMC.NameMCAPIServersMC;
import com.github.happyuky7.nameMCAPIServersMC.managers.RewardsManager;
import com.github.happyuky7.nameMCAPIServersMC.managers.VerifyManager;
import com.github.happyuky7.nameMCAPIServersMC.managers.msgs.MessagesManager;
import com.github.happyuky7.nameMCAPIServersMC.utils.MessageColors;
import com.github.happyuky7.nameMCAPIServersMCCommon.NameMCAPI;
import com.github.happyuky7.nameMCAPIServersMCCommon.api.CooldownManager;
import com.github.happyuky7.nameMCAPIServersMCCommon.api.MojangAPIManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class NameMCAPICMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageColors.getMsgColor("&cYou must be a player to execute this command!"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(MessagesManager.getMessageList("commands.default", player));
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &aNameMCAPIServersMC &7- &fHelp"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &8| &aVersion:&f 2.0.0-DEV-120"));
            player.sendMessage(MessageColors.getMsgColor(" &8| &aAuthor:&f Happyuky7"));
            player.sendMessage(MessageColors.getMsgColor(" &8| &aGithub:&f https://github.com/Happyuky7/NameMC-API-ServersMC"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc &7- &fVote for the server!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc help &7- &fHelp command!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc admin &7- &fAdmin commands!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc info &7- &fPlugin information!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc vote &7- &fVote for the server!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc verify &7- &fVerify your vote!"));
            player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc reload &7- &fReload the plugin!"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            return true;
        }

        if (args[0].equalsIgnoreCase("admin")) {

            if (!player.hasPermission("namemc.admin")) {
                player.sendMessage(MessagesManager.getMessage("no-permission", player));
                return true;
            }

            if (args.length == 1) {
                player.sendMessage(MessageColors.getMsgColor("&8[&9NameMCAPIServersMC&8] &fIn Development."));
                /*player.sendMessage(MessageColors.getMsgColor(" "));
                player.sendMessage(MessageColors.getMsgColor(" &9NameMCAPIServersMC &7- &fAdmin Help"));
                player.sendMessage(MessageColors.getMsgColor(" "));
                player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemc reload &7- &fReload the plugin!"));
                player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemcadmin help &7- &fAdmin Help command!"));
                player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemcadmin checkvote <name/uuid> &7- &fCheck if a player has voted! (Name using MojangAPI check)"));
                player.sendMessage(MessageColors.getMsgColor(" &7- &f/namemcadmin checkreward <name/uuid> &7- &fCheck if a player has claimed their reward! (Name using MojangAPI check)"));
                player.sendMessage(MessageColors.getMsgColor(" "));*/
                return true;
            }

        }

        if (args[0].equalsIgnoreCase("info")) {
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &aNameMCAPIServersMC &7- &fInformation"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            player.sendMessage(MessageColors.getMsgColor(" &8| &aVersion:&f 2.0.0-DEV-120"));
            player.sendMessage(MessageColors.getMsgColor(" &8| &aAuthor:&f Happyuky7"));
            player.sendMessage(MessageColors.getMsgColor(" &8| &aGithub:&f https://github.com/Happyuky7/NameMC-API-ServersMC"));
            player.sendMessage(MessageColors.getMsgColor(" "));
            return true;
        }

        if (args[0].equalsIgnoreCase("vote")) {
            player.sendMessage(MessagesManager.getMessageList("commands.vote", player));
            return true;
        }

        if (args[0].equalsIgnoreCase("verify")) {

            if (!NameMCAPI.COOLDOWN_API.checkCooldown(player.getUniqueId())) {
                player.sendMessage(MessagesManager.getMessageList("commands.verify.cooldown", player)
                        .replaceAll("%time%", String.valueOf(NameMCAPI.COOLDOWN_API.getCooldown(player.getUniqueId()))));
                return true;
            }

            UUID uuid;
            if (NameMCAPIServersMC.getInstance().getConfig().getString("settings.online-mode").equalsIgnoreCase("true")) {
                uuid = player.getUniqueId();
            } else {
                try {
                    uuid = NameMCAPI.getInstance().getMojangUUID(player.getName(), false);
                } catch (Exception e) {
                    player.sendMessage(MessageColors.getMsgColor(" "));
                    player.sendMessage(MessageColors.getMsgColor(" &9NameMCAPIServersMC &7- &fVerify"));
                    player.sendMessage(MessageColors.getMsgColor(" "));
                    player.sendMessage(MessageColors.getMsgColor(" &7- &fAn error occurred while verifying your vote!"));
                    player.sendMessage(MessageColors.getMsgColor(" "));
                    player.sendMessage(MessageColors.getMsgColor("&cError ID: 002"));
                    return true;
                }
            }

            if (uuid == null) {
                player.sendMessage(MessagesManager.getMessageList("commands.verify.error-onlinemode", player));
                return true;
            }

            if (!NameMCAPI.UUID_FORMATER.isValidUUID(uuid.toString())) {
                player.sendMessage(MessagesManager.getMessageList("commands.verify.invalid-uuid", player));
                return true;
            }

            if (NameMCAPIServersMC.getInstance().getConfig().getBoolean("settings.cooldown.enabled")) {
                CooldownManager.setCooldown(player.getUniqueId(), NameMCAPIServersMC.getInstance().getConfig().getInt("settings.cooldown.time"));
            }

            if (VerifyManager.hasVoted(player.getUniqueId(), NameMCAPIServersMC.typeData)) {
                player.sendMessage(MessagesManager.getMessageList("commands.verify.vote.already-voted", player));
                return true;
            }

            if (VerifyManager.verifyVote(
                    NameMCAPIServersMC.typeData,
                    player.getName(),
                    uuid,
                    NameMCAPIServersMC.getInstance().getConfig().getString("settings.ip-server"))) {

                player.sendMessage(MessagesManager.getMessageList("commands.verify.vote.success", player));


                if (NameMCAPIServersMC.getInstance().getConfig().getBoolean("rewards.enabled")) {

                    if (!VerifyManager.verifyClaimReward(
                            NameMCAPIServersMC.typeData,
                            uuid)) {

                        RewardsManager.give(player);
                        VerifyManager.setClaimReward(
                                NameMCAPIServersMC.typeData,
                                uuid,
                                true
                        );

                        player.sendMessage(MessagesManager.getMessageList("commands.verify.rewards.success", player));

                    } else {
                        player.sendMessage(MessagesManager.getMessageList("commands.verify.rewards.already-claimed", player));
                    }

                }

            } else {
                player.sendMessage(MessagesManager.getMessageList("commands.verify.vote.notvoted", player));
            }

            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {

            if (!player.hasPermission("namemc.reload")) {
                player.sendMessage(MessagesManager.getMessage("no-permission", player));
            }

            NameMCAPIServersMC.getInstance().getConfig().reload();
            player.sendMessage(MessagesManager.getMessageList("commands.reload", player));
            return true;
        }

        return true;

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            if (sender.hasPermission("namemc.admin")) {
                return List.of("help", "admin", "info", "vote", "verify", "reload");
            } else {
                return List.of("help", "info", "vote", "verify");
            }
        }

        return null;

    }
}
