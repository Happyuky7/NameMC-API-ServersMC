package com.github.happyuky7.nameMCAPIServersMC.managers.data;

import com.github.happyuky7.nameMCAPIServersMC.NameMCAPIServersMC;
import com.github.happyuky7.nameMCAPIServersMCCommon.NameMCAPI;

import java.util.UUID;

public class YamlDataManager {

    public static void setVote(String username, UUID uuid, boolean voted, boolean claimReward) {

        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".username", username);
        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".voted", voted);
        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".claim-reward", claimReward);
        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".date", NameMCAPI.DATE_API.date());

        NameMCAPIServersMC.getInstance().getData().save();
    }

    public static void setVote(String username, UUID uuid, boolean voted) {
        YamlDataManager.setVote(username, uuid, voted, false);
    }

    public static boolean hasVoted(UUID uuid) {

        if (!NameMCAPIServersMC.getInstance().getData().contains("players." + uuid + ".voted")) {
            return false;
        } else {

            Object voted = NameMCAPIServersMC.getInstance().getData().get("players." + uuid + ".voted");

            if (voted == null) {
                return false;
            } else {
                return (Boolean) voted;
            }
        }
    }

    public static boolean hasClaimedReward(UUID uuid) {

        if (!NameMCAPIServersMC.getInstance().getData().contains("players." + uuid + ".claim-reward")) {
            return false;
        } else {

            Object claimReward = NameMCAPIServersMC.getInstance().getData().get("players." + uuid + ".claim-reward");

            if (claimReward == null) {
                return false;
            } else {
                return (Boolean) claimReward;
            }
        }
    }


    public static String getUsername(UUID uuid) {
        return NameMCAPIServersMC.getInstance().getData().getString("players." + uuid + ".username");
    }

    public static String getDate(UUID uuid) {
        return NameMCAPIServersMC.getInstance().getData().getString("players." + uuid + ".date");
    }

    public static void setClaimReward(UUID uuid, Boolean claimReward) {
        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".claim-reward", claimReward);
        NameMCAPIServersMC.getInstance().getData().save();
    }

}


