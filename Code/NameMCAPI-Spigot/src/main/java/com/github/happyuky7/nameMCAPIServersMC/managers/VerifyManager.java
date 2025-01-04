package com.github.happyuky7.nameMCAPIServersMC.managers;

import com.github.happyuky7.nameMCAPIServersMC.managers.data.YamlDataManager;
import com.github.happyuky7.nameMCAPIServersMCCommon.NameMCAPI;

import java.util.HashMap;
import java.util.UUID;

public class VerifyManager {

    public static HashMap<UUID, Boolean> voted = new HashMap<>();

    // Verify the vote
    public static boolean verifyVote(String type, String username, String uuid, String ip) {

        if (type.equalsIgnoreCase("YAML")) {
            return YamlVerifyVote(username, uuid, ip);
        } else if (type.equalsIgnoreCase("MongoDB")) {
            return MongoDBVerifyVote(username, uuid, ip);
        } else {
            return false;
        }
    }

    // Verify the vote using YAML
    public static boolean YamlVerifyVote(String username, String uuid, String ip) {
        System.out.println("YAML Verify");
        if (YamlDataManager.hasVoted(uuid)) {
            System.out.println("Already Voted");
            voted.put(UUID.fromString(uuid), true);
            return true;
        }

        if (uuid == null || ip == null) {
            throw new IllegalArgumentException("UUID or Server IP cannot be null.");
            //return false;
        }

        //System.out.println("NameMCAPI.getInstance().getVotes(uuid, ip): " + NameMCAPI.getInstance().getVote(uuid, ip));
        if (NameMCAPI.getInstance().getVote(uuid, ip)) {
            YamlDataManager.setVote(username, uuid, true);
            System.out.println("Voted");
            voted.put(UUID.fromString(uuid), true);
            return true;
        }

        System.out.println("Not Voted");
        return false;
    }

    // Verify the vote using MongoDB
    public static boolean MongoDBVerifyVote(String username, String uuid, String ip) {
        // MongoDB code here
        return false;
    }


    // Verify claim reward
    public static boolean verifyClaimReward(String type, String uuid) {

        if (type.equalsIgnoreCase("YAML")) {
            return YamlVerifyClaimReward(uuid);
        } else if (type.equalsIgnoreCase("MongoDB")) {
            return MongoDBVerifyClaimReward(uuid);
        } else {
            return false;
        }
    }

    // Verify the claim reward using YAML
    public static boolean YamlVerifyClaimReward(String uuid) {
        if (YamlDataManager.hasClaimedReward(uuid)) {
            return true;
        }

        return false;
    }

    // Verify the claim reward using MongoDB
    public static boolean MongoDBVerifyClaimReward(String uuid) {
        // MongoDB code here
        return false;
    }


    // Set the claim reward
    public static void setClaimReward(String type, String uuid, boolean claimReward) {

        if (type.equalsIgnoreCase("YAML")) {
            YamlDataManager.setClaimReward(uuid, claimReward);
        } else if (type.equalsIgnoreCase("MongoDB")) {
            MongoDBSetClaimReward(uuid, claimReward);
        }
    }

    // Set the claim reward using MongoDB
    public static void MongoDBSetClaimReward(String uuid, boolean claimReward) {
        // MongoDB code here
    }
}
