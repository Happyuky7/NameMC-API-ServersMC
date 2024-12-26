package com.github.happyuky7.nameMCAPIServersMC;

import com.github.happyuky7.nameMCAPIServersMC.files.FileManager;
import com.github.happyuky7.nameMCAPIServersMC.utils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NameMCAPIServersMC extends JavaPlugin {

    private static NameMCAPIServersMC instance;

    public static NameMCAPIServersMC getInstance() {
        return instance;
    }

    public FileManager config;
    public FileManager dataYaml;

    public static String typeData;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        config = new FileManager(this, "config");

        // Version config check
        if (!getConfig().getString("config-version").equalsIgnoreCase("2.0.0-DEV-100")) {

            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c[NameMCAPIServersMC] Your config is outdated! Please delete your config.yml and restart the server!"));
            Bukkit.getPluginManager().disablePlugin(this);

        }

        // Check Data type selected
        if (getConfig().getString("data.type").equalsIgnoreCase("yaml")) {

            dataYaml = new FileManager(this, "data");
            typeData = "YAML";
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a[NameMCAPIServersMC] Data type is set to YAML!"));

        } else if (getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {

            //typeData = "MongoDB";
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a[NameMCAPIServersMC] Data type is set to MongoDB!"));

        } else {

            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c[NameMCAPIServersMC] Data type is invalid! Please set it to either 'yaml' or 'mongodb'!"));
            Bukkit.getPluginManager().disablePlugin(this);

        }



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c[NameMCAPIServersMC] Plugin has been disabled!"));
    }

    public FileManager getConfig() {
        return config;
    }

    public FileManager getData() {
        return dataYaml;
    }

}
