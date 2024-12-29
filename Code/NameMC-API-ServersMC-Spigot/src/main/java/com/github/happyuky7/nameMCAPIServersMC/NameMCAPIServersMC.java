package com.github.happyuky7.nameMCAPIServersMC;

import com.github.happyuky7.nameMCAPIServersMC.commands.NameMCAPICMD;
import com.github.happyuky7.nameMCAPIServersMC.files.FileManager;
import com.github.happyuky7.nameMCAPIServersMC.utils.DownloadTranslations;
import com.github.happyuky7.nameMCAPIServersMC.utils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class NameMCAPIServersMC extends JavaPlugin {

    private static NameMCAPIServersMC instance;

    public static NameMCAPIServersMC getInstance() {
        return instance;
    }

    public FileManager config;
    public FileManager dataYaml;
    public FileManager langs;

    public static String typeData;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        config = new FileManager(this, "config");

        // Version config check
        if (!getConfig().getString("config-version").equalsIgnoreCase("2.0.0-DEV-107")) {

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

            System.out.println("Error ID: 005");
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c[NameMCAPIServersMC] Data type is invalid! Please set it to either 'yaml' or 'mongodb'!"));
            Bukkit.getPluginManager().disablePlugin(this);

        }

        if (getConfig().getBoolean("settings.langs.autoDownload")) {
            try {
                DownloadTranslations.downloadTranslations();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error ID: lang-002");
            }
        }

        // Check if the language file exists
        // BETA OPTION
        if (!checkLangFileExists(getConfig().getString("settings.langs.lang"))) {
            System.out.println("Error ID: lang-001");
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c[NameMCAPIServersMC] The language file with the ID '" + getConfig().getString("settings.lang.id") + "' does not exist!"));
            Bukkit.getPluginManager().disablePlugin(this);
        } else {

            langs = new FileManager(this, "langs/" + getConfig().getString("settings.langs.lang"));

        }

        getCommand("namemc").setExecutor(new NameMCAPICMD());



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

    public FileManager getLangs() {
        return langs;
    }

    /**
     * Checks if the language file exists in the langs folder.
     *
     * @param langId the language file ID.
     * @return true if the file exists, false otherwise.
     */
    public static boolean checkLangFileExists(String langId) {

        File langsDir = new File(instance.getDataFolder(), "langs");
        if (!langsDir.exists()) {
            langsDir.mkdirs();
        }

        File langFile = new File(langsDir, langId + ".yml");
        return langFile.exists();
    }

}
