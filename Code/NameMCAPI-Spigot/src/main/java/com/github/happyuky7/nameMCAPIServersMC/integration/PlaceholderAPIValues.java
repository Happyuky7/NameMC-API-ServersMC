package com.github.happyuky7.nameMCAPIServersMC.integration;

import com.github.happyuky7.nameMCAPIServersMC.NameMCAPIServersMC;
import com.github.happyuky7.nameMCAPIServersMC.managers.data.YamlDataManager;
import com.github.happyuky7.nameMCAPIServersMC.managers.msgs.MessagesManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PlaceholderAPIValues extends PlaceholderExpansion {

    private final NameMCAPIServersMC plugin;

    public PlaceholderAPIValues(NameMCAPIServersMC plugin){
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Happyuky7";
    }

    @Override
    public String getIdentifier() {
        return "namemcapi";
    }

    @Override
    public String getVersion() {
        return "2.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if(player == null){
            return "";
        }

        if (params.equals("player")) {
            return player.getName();
        }

        if (params.equals("ip")) {
            return plugin.getConfig().getString("settings.ip-server");
        }

        if (params.equals("vote_link")) {
            return "https://namemc.com/server/" + plugin.getConfig().getString("settings.ip-server");
        }

        if (params.equals("uuid") || params.equals("uniqueid") || params.equals("playeruuid")) {
            return player.getUniqueId().toString();
        }

        if (params.equals("voted_boolean")) {
            if (NameMCAPIServersMC.typeData.equalsIgnoreCase("yaml")) {
                if (YamlDataManager.hasVoted(NameMCAPIServersMC.getUUIDOnlineMode(player))) {
                    return "true";
                } else {
                    return "false";
                }
            } else if (NameMCAPIServersMC.typeData.equalsIgnoreCase("mongodb")) {
                return "false";
                //return String.valueOf(VerifyManager.MongoDBVerifyVote(player.getName(), player.getUniqueId(), plugin.getConfig().getString("settings.ip-server")));
            }
        }

        if (params.equals("voted")) {
            if (NameMCAPIServersMC.typeData.equalsIgnoreCase("yaml")) {
                if (YamlDataManager.hasVoted(NameMCAPIServersMC.getUUIDOnlineMode(player))) {
                    return MessagesManager.getMessage("integrations.placeholderapi.voted-true");
                } else {
                    return MessagesManager.getMessage("integrations.placeholderapi.voted-false");
                }
            } else if (NameMCAPIServersMC.typeData.equalsIgnoreCase("mongodb")) {
                return "MongoDB VOTED VALUE, is not implemented in this version (BETA)";
                //return String.valueOf(VerifyManager.MongoDBVerifyVote(player.getName(), player.getUniqueId(), plugin.getConfig().getString("settings.ip-server")));
            }
        }

        if (params.equals("reward_boolean")) {
            if (NameMCAPIServersMC.typeData.equalsIgnoreCase("yaml")) {
                if (YamlDataManager.hasClaimedReward(NameMCAPIServersMC.getUUIDOnlineMode(player))) {
                    return "true";
                } else {
                    return "false";
                }
            } else if (NameMCAPIServersMC.typeData.equalsIgnoreCase("mongodb")) {
                return "false";
            }
        }

        if (params.equals("reward")) {
            if (NameMCAPIServersMC.typeData.equalsIgnoreCase("yaml")) {
                if (YamlDataManager.hasClaimedReward(NameMCAPIServersMC.getUUIDOnlineMode(player))) {
                    return MessagesManager.getMessage("integrations.placeholderapi.reward-true");
                } else {
                    return MessagesManager.getMessage("integrations.placeholderapi.reward-false");
                }
            } else if (NameMCAPIServersMC.typeData.equalsIgnoreCase("mongodb")) {
                return "MongoDB REWARD VALUE, is not implemented in this version (BETA)";
            }
        }



        return null; // Placeholder is unknown by the Expansion
    }
}
