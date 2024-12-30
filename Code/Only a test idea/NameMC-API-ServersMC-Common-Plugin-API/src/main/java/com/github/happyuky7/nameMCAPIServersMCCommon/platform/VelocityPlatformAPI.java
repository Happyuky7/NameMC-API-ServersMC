/*package com.github.happyuky7.nameMCAPIServersMCCommonPluginAPI.platform;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(id = "NameMCAPI-CommonPluginAPI",
        name = "NameMCAPI-CommonPluginAPI",
        version = "1.0.0",
        description = "API común para plugins de Velocity que usan la API de NameMC y ServersMC.",
        authors = {"happyuky7"},
        url = "https://github.com/Happyuky7/NameMC-API-ServersMC")
public class VelocityPlatformAPI implements PlatformAPI {

    private final Plugin plugin;
    private final ProxyServer proxyServer;
    private final Logger logger;

    @Inject
    public VelocityPlatformAPI(Plugin plugin, ProxyServer proxyServer, Logger logger) {
        this.plugin = plugin;
        this.proxyServer = proxyServer;
        this.logger = logger;

        this.logger.info(" ");
        this.logger.info("NameMCAPI-CommonPluginAPI - 1.0.0");
        this.logger.info("Velocity Enabled");
        this.logger.info(" ");
    }

    public void onEnable() {
        logger.info(" ");
        logger.info("NameMCAPI-CommonPluginAPI - 1.0.0");
        logger.info("Velocity Enabled");
        logger.info(" ");
    }

    public void onDisable() {
        logger.info(" ");
        logger.info("NameMCAPI-CommonPluginAPI - 1.0.0");
        logger.info("Velocity Disabled");
        logger.info(" ");
    }
}
*/