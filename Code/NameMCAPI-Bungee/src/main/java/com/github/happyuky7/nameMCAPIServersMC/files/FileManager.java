package com.github.happyuky7.nameMCAPIServersMC.files;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class FileManager {

    /*
     Code by: HappyRogelio7
     Github: https://github.com/HappyRogelio7
     License: Custom
     Link: https://github.com/HappyRogelio7/FileManagerBungee1
     */

    /*
    FileManager Link: https://github.com/HappyRogelio7/FileManagerBungee1
    */

    private final String fileName;
    private Configuration config;
    private ConfigurationProvider provider = ConfigurationProvider.getProvider(YamlConfiguration.class);
    private final Plugin plugin;
    private final File folder;


    public FileManager(Plugin plugin, String fileName, File folder) {
        this(plugin, fileName, ".yml", folder);
    }

    public FileManager(Plugin plugin, String filename, String fileextension, File folder) {
        this.folder = folder;
        this.plugin = plugin;
        this.fileName = filename + (filename.endsWith(fileextension) ? "" : fileextension);
        createFile(plugin,fileName);
    }

    public FileManager(Plugin plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }
    public Configuration getDataConfig() {
        return this.config;
    }

    public FileManager(Plugin plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }
    public Plugin getPlugin() {
        return this.plugin;
    }

    public String getColouredString(String path) {
        String getted;
        try {
            getted = this.config.getString(path);
        } catch (NullPointerException e) {
            getted = path;
        }
        return ChatColor.translateAlternateColorCodes('&', getted);
    }

    public List<String> getColouredStringList(String path) {
        List<String> getted;
        try {
            getted = this.config.getStringList(path);
        } catch (NullPointerException e) {
            getted = new ArrayList<>();
        }
        List<String> coloured = new ArrayList<>();
        for (String s : getted) {
            coloured.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return coloured;
    }

    public void createFile(Plugin plugin, String resource) {

        if (!folder.exists())
            folder.mkdirs();
        File resourceFile = new File(folder, resource);
        try {
            if (!resourceFile.exists()) {

                resourceFile.createNewFile();
                try(InputStream in = plugin.getResourceAsStream(resource);
                    OutputStream out = new FileOutputStream(resourceFile)) {
                    ByteStreams.copy(in, out);
                }
                load();
            } else{
                load();
                save();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void load() {
        try {
            config = provider.load(new File(this.folder, this.fileName));
        } catch (IOException e) {
            e.printStackTrace();
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Error occurred while loading files.");
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, " ");
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Error: " + e.getMessage());
            return;
        }
    }
    public void save() {
        try {
            provider.save(config, new File(this.folder, this.fileName));
        } catch (IOException e) {
            e.printStackTrace();
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Error occurred while saving files.");
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, " ");
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Error: " + e.getMessage());
            return;
        }
    }

    public void reload() {
        try {
            config = provider.load(new File(this.folder, this.fileName));
        } catch (IOException e) {
            e.printStackTrace();
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Error occurred while reloading files.");
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, " ");
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Error: " + e.getMessage());
            return;
        }
    }

}