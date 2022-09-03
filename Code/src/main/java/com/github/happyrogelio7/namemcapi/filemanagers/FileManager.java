package com.github.happyrogelio7.namemcapi.filemanagers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileManager extends YamlConfiguration {

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

    public String getColouredString(String path) {
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
            this.plugin.getLogger().log(Level.SEVERE, "Error occurred creating the " + this.fileName + " file.");
            this.plugin.getLogger().log(Level.SEVERE, "");
            this.plugin.getLogger().log(Level.SEVERE, "Error:" + ex.getMessage());
        }
    }

    public void save() {
        File folder = this.plugin.getDataFolder();
        try {
            save(new File(folder, this.fileName));
        } catch (Exception ex) {
            this.plugin.getLogger().log(Level.SEVERE, "Error occurred while saving the " + this.fileName + " file.");
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
            this.plugin.getLogger().log(Level.SEVERE, "Error occurred while reloading the " + this.fileName + " file.");
            this.plugin.getLogger().log(Level.SEVERE, "");
            this.plugin.getLogger().log(Level.SEVERE, "Error:" + e.getMessage());
        }
    }
}

