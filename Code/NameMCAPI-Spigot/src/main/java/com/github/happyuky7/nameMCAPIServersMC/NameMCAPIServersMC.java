package com.github.happyuky7.nameMCAPIServersMC;

import com.github.happyuky7.nameMCAPIServersMC.commands.NameMCAPICMD;
import com.github.happyuky7.nameMCAPIServersMC.files.FileManager;
import com.github.happyuky7.nameMCAPIServersMC.integration.PlaceholderAPIValues;
import com.github.happyuky7.nameMCAPIServersMC.integration.PlaceholderAPIValues2;
import com.github.happyuky7.nameMCAPIServersMC.utils.DownloadTranslations;
import com.github.happyuky7.nameMCAPIServersMC.utils.MessageColors;
import com.github.happyuky7.nameMCAPIServersMCCommon.NameMCAPI;
import com.github.happyuky7.nameMCAPIServersMCCommon.api.data.MongoDBManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.UUID;

public final class NameMCAPIServersMC extends JavaPlugin {

    private static NameMCAPIServersMC instance;
    private static MongoDBManager mongoDBManager;

    public static NameMCAPIServersMC getInstance() {
        return instance;
    }

    public static MongoDBManager getMongoDBManager() {
        return mongoDBManager;
    }

    public FileManager config;
    public FileManager dataYaml;
    public FileManager langs;

    public static String typeData;
    public static Boolean integrationPlaceholderAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        config = new FileManager(this, "config");

        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor(" "));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a NameMCAPI &7- &aEnabled!"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor(" "));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a Author: &f" + getDescription().getAuthors().get(0)));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a Version: &f" + getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a Github: &fhttps://github.com/Happyuky7/NameMC-API-ServersMC"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor(" "));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a Server Version: &f" + Bukkit.getVersion()));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor(" "));

        // Version config check
        if (!getConfig().getString("config-version").equalsIgnoreCase("2.0.0-Spigot-DEV-122")) {

            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c[NameMCAPIServersMC] Your config is outdated! Please delete your config.yml and restart the server!"));
            Bukkit.getPluginManager().disablePlugin(this);

        }
        
        // Check Integration
        
        // Integration PlaceholderAPI
        if (getConfig().getBoolean("integrations.placeholderapi")) {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                new PlaceholderAPIValues(this).register();
                new PlaceholderAPIValues2(this).register();
                integrationPlaceholderAPI = true;
                Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a[NameMCAPIServersMC] PlaceholderAPI has been integrated!"));
            } else {
                Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c[NameMCAPIServersMC] PlaceholderAPI is not installed!"));
            }
        }

        // Check Data type selected
        if (getConfig().getString("data.type").equalsIgnoreCase("yaml")) {

            dataYaml = new FileManager(this, "data");
            typeData = "YAML";
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a[NameMCAPIServersMC] Data type is set to YAML!"));

        } else if (getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {

            typeData = "MongoDB";

            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cWARNING: MongoDB is still in beta!"));
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cWARNING: MongoDB is still in beta!"));
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cWARNING: MongoDB is still in beta!"));
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cWARNING: MongoDB is still in beta!"));
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cWarning: It is NOT 100% proven to work correctly, function is BETA."));
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cWarning: It is NOT 100% proven to work correctly, function is BETA."));
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cWarning: It is NOT 100% proven to work correctly, function is BETA."));
            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&cWarning: It is NOT 100% proven to work correctly, function is BETA."));

            Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a[NameMCAPIServersMC] Setting up MongoDB..."));

            try {

                setupMongoDB();

                getMongoDBManager().getMongoClient().startSession();

                Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&a[NameMCAPIServersMC] MongoDB has been set up!"));

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error ID: 004");
                Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c[NameMCAPIServersMC] Error while setting up MongoDB!"));
                Bukkit.getPluginManager().disablePlugin(this);
            }

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

        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor(" "));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c NameMCAPI &7- &cDisabled!"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor(" "));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c Author: &f" + getDescription().getAuthors().get(0)));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c Version: &f" + getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&c Github: &fhttps://github.com/Happyuky7/NameMC-API-ServersMC"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor(" "));
    }


    private void setupMongoDB() {
        String mongoURI = getConfig().getString("data.mongodb.uri");
        String dbName = getConfig().getString("data.mongodb.database");
        String collectionName = getConfig().getString("data.mongodb.collection");

        mongoDBManager = new MongoDBManager(mongoURI, dbName, collectionName);
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

    public static UUID getUUIDOnlineMode(OfflinePlayer player) {
        if (NameMCAPIServersMC.getInstance().getConfig().getBoolean("settings.online-mode")) {
            return player.getUniqueId();
        } else {
            Integer timeoutMonjang = NameMCAPIServersMC.getInstance().getConfig().getInt("settings.timeout-mojang");
            if (NameMCAPI.getInstance().getMojangUUID(player.getName(), false, timeoutMonjang) != null) {
                return NameMCAPI.getInstance().getMojangUUID(player.getName(), false);
            } else {
                return player.getUniqueId();
            }
        }
    }

    public static UUID getUUIDOnlineMode(Player player) {
        if (NameMCAPIServersMC.getInstance().getConfig().getBoolean("settings.online-mode")) {
            return player.getUniqueId();
        } else {
            Integer timeoutMonjang = NameMCAPIServersMC.getInstance().getConfig().getInt("settings.timeout-mojang");
            if (NameMCAPI.getInstance().getMojangUUID(player.getName(), false, timeoutMonjang) != null) {
                return NameMCAPI.getInstance().getMojangUUID(player.getName(), false);
            } else {
                return player.getUniqueId();
            }
        }
    }
}
