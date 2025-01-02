package com.github.happyuky7.nameMCAPIServersMC;

import com.github.happyuky7.nameMCAPIServersMC.files.FileManager;
import com.github.happyuky7.nameMCAPIServersMCCommon.NameMCAPI;
import com.github.happyuky7.nameMCAPIServersMCCommon.api.data.MongoDBManager;
import net.md_5.bungee.api.plugin.Plugin;

public final class NameMCAPIServersMC extends Plugin {

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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
