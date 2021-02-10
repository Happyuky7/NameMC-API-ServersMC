package namemcapi.happyrogelio7.Commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import namemcapi.happyrogelio7.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameMCAPICommands implements CommandExecutor {

    private Main plugin;

    public NameMCAPICommands(Main plugin) {
        this.plugin = plugin;
    }

    private String msgcolor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0){

            if (args[0].equalsIgnoreCase("help")) {

                sender.sendMessage(msgcolor("&8===================&9[&fNameMC&9]&8==================="));
                sender.sendMessage(msgcolor("&r "));
                sender.sendMessage(msgcolor("&a&lAuthor:&r " + this.plugin.creator));
                sender.sendMessage(msgcolor("&9&lVersion:&r " + this.plugin.version));
                sender.sendMessage(msgcolor("&d&lGitHub:&r https://github.com/HappyRogelio7/NameMC-API-ServersMC"));
                sender.sendMessage(msgcolor("&6&lWeb:&r https://github.com/HappyRogelio7/NameMC-API-ServersMC"));
                sender.sendMessage(msgcolor("&r "));
                sender.sendMessage(msgcolor("&c&lUsage: &6/namemc &7(Help Command plugin.)"));
                sender.sendMessage(msgcolor("&c&lUsage: &6/namemc admin &7(Admin Command plugin.)"));
                sender.sendMessage(msgcolor("&c&lUsage: &6/namemc verify &7(Verify your Vote.)"));
                sender.sendMessage(msgcolor("&r "));
                sender.sendMessage(msgcolor("&8===================&9[&fNameMC&9]&8==================="));

                return true;

            }else if (args[0].equalsIgnoreCase("admin")) {

                Player p = (Player) sender;

                if (!p.isOp() && !p.hasPermission("namemcapi.admin.cmd.admin")) {
                    String msg = plugin.getLangs().getString("General.No-Permission");
                    msg.replaceAll("[%player%]", p.getName())
                            .replaceAll("[%namemcwebvote%]", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                            .replaceAll("[%prefix%]", plugin.getLangs().getString("General.Prefix"))
                            .replaceAll("[%serverip%]", plugin.getConfig().getString("General.IP"));
                    System.out.println(plugin.getLangs().getString("General.No-Permission"));
                    System.out.println(msg);
                    p.sendMessage(msgcolor(plugin.getLangs().getString("General.No-Permission"))

                            .replaceAll("[%player%]", p.getName())
                            .replaceAll("[%namemcwebvote%]", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                            .replaceAll("[%prefix%]", plugin.getLangs().getString("General.Prefix"))
                            .replaceAll("[%serverip%]", plugin.getConfig().getString("General.IP")));

                    return true;
                }

                p.sendMessage(msgcolor("&cComming Soon..."));

                return true;

            }else if (args[0].equalsIgnoreCase("vote")){

                Player p = (Player) sender;

               p.sendMessage(msgcolor("error"));
                return true;

            }else if (args[0].equalsIgnoreCase("verify")){

                Player p = (Player) sender;


                try {
                    Scanner scanner = new Scanner((new URL("https://api.namemc.com/server/" + this.plugin.getConfig().getString("General.IP")
                            + "/likes?profile=" + p.getUniqueId())).openStream());
                    boolean status = Boolean.parseBoolean(scanner.next());
                    if (status) {

                        List<String> VERIFYMSG1 = this.plugin.getLangs().getStringList("Langs.VOTING-PLAYER");
                        for (String item : VERIFYMSG1) {
                            item = ChatColor.translateAlternateColorCodes('&', item);
                            p.sendMessage(item

                                    .replaceAll("[%player%]", p.getName())
                                    .replaceAll("[%namemcwebvote%]", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                                    .replaceAll("[%prefix%]", plugin.getLangs().getString("General.Prefix"))
                                    .replaceAll("[%serverip%]", plugin.getConfig().getString("General.IP")));

                            System.out.println("errrot1");
                        }

                        if (this.plugin.getConfig().getBoolean("Rewards.Commands.Console-CMD", true)){

                            List<String> REWARDVERIFY1 = this.plugin.getConfig().getStringList("Rewards.Commands.Console");

                            Bukkit.dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), String.valueOf(REWARDVERIFY1)
                                    .replaceAll("[%player%]", p.getName()));

                            System.out.println("errrot2");
                        }

                        if (this.plugin.getConfig().getBoolean("Rewards.Commands.Player-CMD", true)){

                            List<String> REWARDVERIFY2 =this.plugin.getConfig().getStringList("Rewards.Commands.Player");

                            p.chat(String.valueOf(REWARDVERIFY2).replaceAll("[%player%]", p.getName()));

                            System.out.println("errrot3");
                        }


                    } else {

                        List<String> NOVOTINGMSG1 = this.plugin.getConfig().getStringList("Langs.NO-VOTING");
                        for (String item : NOVOTINGMSG1) {
                            item = ChatColor.translateAlternateColorCodes('&', item);
                            p.sendMessage(item

                                    .replaceAll("[%player%]", p.getName())
                                    .replaceAll("[%namemcwebvote%]", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                                    .replaceAll("[%prefix%]", plugin.getLangs().getString("General.Prefix"))
                                    .replaceAll("[%serverip%]", plugin.getConfig().getString("General.IP")));

                            System.out.println("errrot4");
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }






                return true;
            }

            Player p = (Player) sender;

            List<String> ERRORHELP1 = this.plugin.getConfig().getStringList("Langs.CUSTOM-ERROR");
            for (String item : ERRORHELP1) {
                item = ChatColor.translateAlternateColorCodes('&', item);

                p.sendMessage(item



                        .replaceAll("[%player%]", p.getName())
                        .replaceAll("[%namemcwebvote%]", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                        .replaceAll("[%prefix%]", plugin.getLangs().getString("General.Prefix"))
                        .replaceAll("[%serverip%]", plugin.getConfig().getString("General.IP")));
            }
            return true;
        }

        Player p = (Player) sender;

        List<String> ERRORHELP1 = this.plugin.getConfig().getStringList("Langs.CUSTOM-ERROR");
        for (String item : ERRORHELP1) {
            item = ChatColor.translateAlternateColorCodes('&', item);

            p.sendMessage(item



                    .replaceAll("[%player%]", p.getName())
                    .replaceAll("[%namemcwebvote%]", plugin.getLangs().getString("General.NAMEMC-VOTE"))
                    .replaceAll("[%prefix%]", plugin.getLangs().getString("General.Prefix"))
                    .replaceAll("[%serverip%]", plugin.getConfig().getString("General.IP")));
        }
        return true;
    }
}
