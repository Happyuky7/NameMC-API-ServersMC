package com.github.happyuky7.nameMCAPIServersMCCommonPluginAPI.platform;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeePlatformAPI implements PlatformAPI {

    private Plugin plugin;

    public BungeePlatformAPI(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {
        plugin.getLogger().info(" ");
        plugin.getLogger().info("");
        plugin.getLogger().info("NameMCAPI-CommonPluginAPI - 1.0.0");
        plugin.getLogger().info("BungeeCord, Waterfall, etc. Enabled");
        plugin.getLogger().info("");
    }

    @Override
    public void onDisable() {
        plugin.getLogger().info(" ");
        plugin.getLogger().info("");
        plugin.getLogger().info("NameMCAPI-CommonPluginAPI - 1.0.0");
        plugin.getLogger().info("BungeeCord, Waterfall, etc. Disabled");
        plugin.getLogger().info("");
    }
}