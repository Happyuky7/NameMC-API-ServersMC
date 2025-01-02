package com.github.happyuky7.nameMCAPIServersMC.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.MemoryConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileManager extends YamlConfiguration {

    /*
     Code by: Happyuky7
     Github: https://github.com/Happyuky7
     License: MIT
     Link: https://github.com/Happyuky7/FileManagerBukkit1
     */

    /*
    FileManager Link: https://github.com/Happyuky7/FileManagerBukkit1
    */

    private final String fileName;

    private final JavaPlugin plugin;

    private final File folder;

    public FileManager(JavaPlugin plugin, String fileName, File folder) {
        this(plugin, fileName, ".yml", folder);
    }

    public FileManager(JavaPlugin plugin, String filename, String fileextension, File folder) {
        this.folder = folder;
        this.plugin = plugin;
        this.fileName = filename + (filename.endsWith(fileextension) ? "" : fileextension);
        createFile();
    }

    public FileManager(JavaPlugin plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }

    public FileManager(JavaPlugin plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public <T> T get(Class<T> clazz, String path) {
        Object obj = get(path);
        return clazz.cast(obj);
    }

    private void createFile() {
        try {
            File file = new File(this.folder, this.fileName);
            if (!file.exists()) {
                if (this.plugin.getResource(this.fileName) != null) {
                    this.plugin.saveResource(this.fileName, false);
                } else {
                    save(file);
                }
                load(file);
            } else {
                load(file);
                save(file);
            }
        } catch (Exception ex) {
            this.plugin.getLogger().log(Level.SEVERE, "error occurred creating the " + this.fileName + " file");
            this.plugin.getLogger().log(Level.SEVERE, "");
            this.plugin.getLogger().log(Level.SEVERE, "Error:" + ex.getMessage());
        }
    }

    public void save() {
        File folder = this.plugin.getDataFolder();
        try {
            save(new File(folder, this.fileName));
        } catch (Exception ex) {
            this.plugin.getLogger().log(Level.SEVERE, "error occurred while saving the " + this.fileName + " file");
            this.plugin.getLogger().log(Level.SEVERE, "");
            this.plugin.getLogger().log(Level.SEVERE, "Error:" + ex.getMessage());
        }
    }

    public void reload() {
        File folder = this.plugin.getDataFolder();
        File file = new File(folder, this.fileName);
        try {
            load(file);
        } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
            this.plugin.getLogger().log(Level.SEVERE, "error occurred while reloading the " + this.fileName + " file");
            this.plugin.getLogger().log(Level.SEVERE, "");
            this.plugin.getLogger().log(Level.SEVERE, "Error:" + e.getMessage());
        }
    }

    // You can remove this if you are using versions higher than 1.16,
    // soon I will implement a new improved FileManager stay tuned to the
    // Github repository: https://github.com/Happyuky7/FileManagerBukkit1
    // START | Use only for legacy colors i.e. below Minecraft version 1.16.X
    /*public String getColouredString(String path) {
        String getted;
        try {
            getted = getString(path);
        } catch (NullPointerException e) {
            getted = path;
        }
        return ChatColor.translateAlternateColorCodes('&', getted);
    }

    public List<String> getColouredStringList(String path) {
        List<String> f = new ArrayList<>();
        for (String l : getStringList(path))
            f.add(l.replace('&', '&'));
        return f;
    }*/
    // END | Use only for legacy colors i.e. below Minecraft version 1.16.X


}