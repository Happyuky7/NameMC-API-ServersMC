package com.github.happyrogelio7.namemcapi;

import com.github.happyrogelio7.namemcapi.commands.NameMCAPIAdminCMD;
import com.github.happyrogelio7.namemcapi.commands.NameMCAPICMD;
import com.github.happyrogelio7.namemcapi.database.ConnectionMYSQL;
import com.github.happyrogelio7.namemcapi.filemanagers.FileManager;
import com.github.happyrogelio7.namemcapi.placeholderapi.PlaceholderAPIVariables;
import com.github.happyrogelio7.namemcapi.utils.BungeeChannel;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.sql.Connection;

public final class NameMCAPI extends JavaPlugin {

    private FileManager config;

    private FileManager langs;
    private FileManager langsload;

    private FileManager playerdata;

    PluginDescriptionFile pdffile = getDescription();

    public String version = this.pdffile.getVersion();
    public String creator = String.valueOf(this.pdffile.getAuthors());

    private ConnectionMYSQL conexion;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&b--------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&fNameMC-API &aEnable"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&f "));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&aAuthor: &f" + creator));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&9Version: &f" + version));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&f "));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&dGitHub: &fhttps://github.com/HappyRogelio7/NameMC-API-ServersMC"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&6Web: &fhttps://github.com/HappyRogelio7/NameMC-API-ServersMC"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&b--------------------------------------------"));
        
        getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        BungeeChannel bungeeChannel = new BungeeChannel(this);
        getServer().getMessenger().registerIncomingPluginChannel((Plugin)this, "BungeeCord", (PluginMessageListener)bungeeChannel);
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&9[&fNameMC&9]&r &9BungeeCord Channel."));

        registerFiles();
        registerListeners();
        registerCMDS();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            (new PlaceholderAPIVariables(this)).register();


        if (this.getConfig().getBoolean("Save-Data.mysql-options.mysql-enable", true)) {

            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&fNameMC&9]&r &aPlugin data is saved in format: YAML."));
            this.getConfig().set("Save-Data.db-type", "YAML");
            
        } else if (this.getConfig().getBoolean("Save-Data.mysql-options.mysql-enable", true)) {

            this.conexion = new ConnectionMYSQL(this.getConfig().getString("Save-Data.mysql-options.mysql-ip"),
                    this.getConfig().getInt("Save-Data.mysql-options.mysql-port"),
                    this.getConfig().getString("Save-Data.mysql-options.mysql-database"),
                    this.getConfig().getString("Save-Data.mysql-options.mysql-user"),
                    this.getConfig().getString("Save-Data.mysql-options.mysql-password"));

            this.getConfig().set("Save-Data.db-type", "MYSQL");

        } else if (this.getConfig().getBoolean("Save-Data.mongodb-options.mongodb-enable", true)) {

            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&fNameMC&9]&r &cMongoDB is not available in this version of the plugin."));
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            this.onDisable();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&b--------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&fNameMC-API &cDisable"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&f "));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&aAuthor: &f" + creator));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&9Version: &f" + version));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&f "));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&dGitHub: &fhttps://github.com/HappyRogelio7/"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&6Web: &fhttps://happyrogelio7.github.io/HappyRogelio7/index"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&bPlugin:&r https://github.com/HappyRogelio7/NameMC-API-ServersMC"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.getMsgColor("&b--------------------------------------------"));
    }


    public void registerCMDS() {
        getCommand("namemc").setExecutor((CommandExecutor) new NameMCAPICMD(this));
        getCommand("namemcapiamdin").setExecutor((CommandExecutor) new NameMCAPIAdminCMD(this));
    }

    public void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        // pm.registerEvents((Listener) new OnJoinListener(this), (Plugin) this);
    }

    public void registerFiles() {
        this.config = new FileManager(this, "config");
        this.langs = new FileManager(this, "langs/lang");
        this.playerdata = new FileManager(this, "data/playerdata");

        ///Load others Languages:
        this.langsload = new FileManager(this, "langs/lang-en");
        this.langsload = new FileManager(this, "langs/lang-es");
        this.langsload = new FileManager(this, "langs/lang-de");
        this.langsload = new FileManager(this, "langs/lang-jp");
    }

    public FileManager getConfig() {
        return this.config;
    }

    public FileManager getLangs() {
        return this.langs;
    }

    public FileManager getPlayerData() {
        return this.playerdata;
    }

    public Connection getMYSQL(){
        return conexion.getConnection();
    }

}
