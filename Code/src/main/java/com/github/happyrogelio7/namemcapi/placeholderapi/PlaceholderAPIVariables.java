package com.github.happyrogelio7.namemcapi.placeholderapi;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceholderAPIVariables extends PlaceholderExpansion {
    public NameMCAPI plugin;

    public PlaceholderAPIVariables(NameMCAPI plugin) {
        this.plugin = plugin;
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public String getAuthor() {
        return "HappyRogelio7";
    }

    public String getIdentifier() {
        return "namemcapi";
    }

    public String getVersion() {
        return this.plugin.getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player p, String identifier) {
        if (p == null)
            return "";
        if (identifier.equals("prefix"))
            return this.plugin.getConfig().getString("General.Prefix", "value doesnt exist");
        if (identifier.equals("namemcweb"))
            return this.plugin.getConfig().getString("General.NAMEMC-VOTE", "value doesnt exist");
        if (identifier.equals("ip"))
            return this.plugin.getConfig().getString("General.IP", "value doesnt exist");
        if (identifier.equals("votewebip")){
            return "Value not available in this plugin version.";
        }
        if (identifier.equals("votestatusplayer"))
            //return this.plugin.getConfig().getString("General.IP", "value doesnt exist");
            return "Value not available in this plugin version.";
        if (identifier.equals("player"))
            return p.getName();
        if (identifier.equals("playeruuid"))
            return String.valueOf(p.getUniqueId());
        return null;
    }
}

