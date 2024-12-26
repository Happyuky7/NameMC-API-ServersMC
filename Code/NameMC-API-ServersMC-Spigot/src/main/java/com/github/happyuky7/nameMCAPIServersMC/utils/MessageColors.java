package com.github.happyuky7.nameMCAPIServersMC.utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageColors {

    /*
     Code by: Happyuky7
     Github: https://github.com/Happyuky7
     License: Custom
     Link: https://github.com/Happyuky7/getMsgColorSpigotClass1
     */

    public static String getMsgColor(String text) {

        if (Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.16.1")
                || Bukkit.getVersion().contains("1.16.2") || Bukkit.getVersion().contains("1.16.3")
                || Bukkit.getVersion().contains("1.16.4") || Bukkit.getVersion().contains("1.16.5")
                || Bukkit.getVersion().contains("1.17") || Bukkit.getVersion().contains("1.17.1")
                || Bukkit.getVersion().contains("1.18") || Bukkit.getVersion().contains("1.18.1")
                || Bukkit.getVersion().contains("1.18.2") || Bukkit.getVersion().contains("1.19")
                || Bukkit.getVersion().contains("1.19.1") || Bukkit.getVersion().contains("1.19.2")
                || Bukkit.getVersion().contains("1.19.3") || Bukkit.getVersion().contains("1.19.4")
                || Bukkit.getVersion().contains("1.20") || Bukkit.getVersion().contains("1.20.1")
                || Bukkit.getVersion().contains("1.20.2") || Bukkit.getVersion().contains("1.20.3")
                || Bukkit.getVersion().contains("1.20.4") || Bukkit.getVersion().contains("1.20.5")
                || Bukkit.getVersion().contains("1.20.6") || Bukkit.getVersion().contains("1.21")
                || Bukkit.getVersion().contains("1.21.1") || Bukkit.getVersion().contains("1.21.2")
                || Bukkit.getVersion().contains("1.21.3") || Bukkit.getVersion().contains("1.21.4")
                || Bukkit.getVersion().contains("1.21.5") || Bukkit.getVersion().contains("1.21.6")
        ) {

            Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                String color = text.substring(matcher.start(), matcher.end());

                text = text.replace("&" + color, ChatColor.of(color) + "");
                text = text.replace(color, ChatColor.of(color) + "");

                matcher = pattern.matcher(text);
            }
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }
}