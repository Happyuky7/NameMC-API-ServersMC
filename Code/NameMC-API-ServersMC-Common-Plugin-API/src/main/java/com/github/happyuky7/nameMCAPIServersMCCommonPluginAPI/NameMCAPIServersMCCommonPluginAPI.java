package com.github.happyuky7.nameMCAPIServersMCCommonPluginAPI;

import com.github.happyuky7.nameMCAPIServersMCCommonPluginAPI.platform.PlatformAPI;

public class NameMCAPIServersMCCommonPluginAPI {

    private static PlatformAPI platformAPI;

    public static void onEnableSpigot(org.bukkit.plugin.java.JavaPlugin plugin) {
        platformAPI = new com.github.happyuky7.nameMCAPIServersMCCommonPluginAPI.platform.SpigotPlatformAPI(plugin);
        platformAPI.onEnable();
    }

    public static void onEnableBungee(net.md_5.bungee.api.plugin.Plugin plugin) {
        platformAPI = new com.github.happyuky7.nameMCAPIServersMCCommonPluginAPI.platform.BungeePlatformAPI(plugin);
        platformAPI.onEnable();
    }

    /*public static void onEnableVelocity(com.velocitypowered.api.plugin.Plugin plugin, com.velocitypowered.api.proxy.ProxyServer proxyServer, org.slf4j.Logger logger) {
        platformAPI = new com.github.happyuky7.nameMCAPIServersMCCommonPluginAPI.platform.VelocityPlatformAPI(plugin, proxyServer, logger);
        platformAPI.onEnable();
    }*/

    public static void onDisable() {
        if (platformAPI != null) {
            platformAPI.onDisable();
        }
    }

}