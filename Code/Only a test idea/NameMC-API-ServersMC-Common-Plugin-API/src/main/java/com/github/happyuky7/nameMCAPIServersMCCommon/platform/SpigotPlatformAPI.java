package com.github.happyuky7.nameMCAPIServersMCCommon.platform;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotPlatformAPI implements PlatformAPI {

    private JavaPlugin plugin;

    public SpigotPlatformAPI(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {
        plugin.getLogger().info("");
        plugin.getLogger().info("NameMCAPI-CommonPluginAPI - 1.0.0");
        plugin.getLogger().info("Spigot, Bukkit, Paper, Purpur, etc. Enabled");
        plugin.getLogger().info("");

    }

    @Override
    public void onDisable() {
        plugin.getLogger().info("");
        plugin.getLogger().info("NameMCAPI-CommonPluginAPI - 1.0.0");
        plugin.getLogger().info("Spigot, Bukkit, Paper, Purpur, etc. Disabled");
        plugin.getLogger().info("");
    }
}