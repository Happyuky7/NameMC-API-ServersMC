package com.github.happyuky7.nameMCAPIServersMC.managers.data;

import com.github.happyuky7.nameMCAPIServersMC.NameMCAPIServersMC;
import com.github.happyuky7.nameMCAPIServersMCCommon.NameMCAPI;

public class YamlDataManager {

    public static void setVote(String username, String uuid, boolean voted, boolean claimReward) {

        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".username", username);
        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".voted", voted);
        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".claim-reward", claimReward);
        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".date", NameMCAPI.DATE_API.date());

        NameMCAPIServersMC.getInstance().getData().save();
    }

    public static void setVote(String username, String uuid, boolean voted) {
        YamlDataManager.setVote(username, uuid, voted, false);
    }

    public static boolean hasVoted(String uuid) {
        return NameMCAPIServersMC.getInstance().getData().getBoolean("players." + uuid + ".voted");
    }

    public static boolean hasClaimedReward(String uuid) {

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


    public static String getUsername(String uuid) {
        return NameMCAPIServersMC.getInstance().getData().getString("players." + uuid + ".username");
    }

    public static String getDate(String uuid) {
        return NameMCAPIServersMC.getInstance().getData().getString("players." + uuid + ".date");
    }

    public static void setClaimReward(String uuid, Boolean claimReward) {
        NameMCAPIServersMC.getInstance().getData().set("players." + uuid + ".claim-reward", claimReward);
    }

}


