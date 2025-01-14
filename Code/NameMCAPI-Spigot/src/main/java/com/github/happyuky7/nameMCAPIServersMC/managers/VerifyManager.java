package com.github.happyuky7.nameMCAPIServersMC.managers;

import com.github.happyuky7.nameMCAPIServersMC.NameMCAPIServersMC;
import com.github.happyuky7.nameMCAPIServersMC.managers.data.YamlDataManager;
import com.github.happyuky7.nameMCAPIServersMCCommon.NameMCAPI;
import com.github.happyuky7.nameMCAPIServersMCCommon.api.data.MongoDBManager;

import java.util.HashMap;
import java.util.UUID;

public class VerifyManager {

    public static HashMap<UUID, Boolean> voted = new HashMap<>();

    // Verify the vote
    public static boolean verifyVote(String type, String username, UUID uuid, String ip) {

        if (type.equalsIgnoreCase("YAML")) {
            return YamlVerifyVote(username, uuid, ip);
        } else if (type.equalsIgnoreCase("MongoDB")) {
            return MongoDBVerifyVote(username, uuid, ip);
        } else {
            return false;
        }
    }

    public static boolean hasVoted(UUID uuid, String type) {
        if (type.equalsIgnoreCase("YAML")) {
            return YamlDataManager.hasVoted(uuid);
        } else if (type.equalsIgnoreCase("MongoDB")) {
            return voted.containsKey(uuid) ? voted.get(uuid) : MongoDBManager.getVote(uuid);
            /*if (voted.containsKey(uuid)) {
                if (voted.get(uuid)) {
                    return true;
                } else {
                    return MongoDBManager.getVote(uuid);
                }
            } else {
                return MongoDBManager.getVote(uuid);
            }*/
        } else {
            return false;
        }
    }

    // Verify the vote using YAML
    public static boolean YamlVerifyVote(String username, UUID uuid, String ip) {
        //System.out.println("YAML Verify");
        if (uuid == null || ip == null) {
            throw new IllegalArgumentException("UUID or Server IP cannot be null.");
            //return false;
        }

        if (YamlDataManager.hasVoted(uuid)) {
            //System.out.println("Already Voted");
            voted.put(uuid, true);
            return true;
        }

        //System.out.println("NameMCAPI.getInstance().getVotes(uuid, ip): " + NameMCAPI.getInstance().getVote(uuid, ip));
        if (NameMCAPI.getInstance().getVote(uuid, ip)) {
            YamlDataManager.setVote(username, uuid, true);
            //System.out.println("Voted");
            voted.put(uuid, true);
            return true;
        }

        //System.out.println("Not Voted");
        return false;
    }

    // Verify the vote using MongoDB
    public static boolean MongoDBVerifyVote(String username, UUID uuid, String ip) {

        if (uuid == null || ip == null) {
            throw new IllegalArgumentException("UUID or Server IP cannot be null.");
            //return false;
        }

        if (MongoDBManager.getVote(uuid)) {
            voted.put(uuid, true);
            return true;
        }

        if (NameMCAPI.getInstance().getVote(uuid, ip)) {
            MongoDBManager.setVote(uuid, true);
            voted.put(uuid, true);
            return true;
        }

        return false;
    }


    // Verify claim reward
    public static boolean verifyClaimReward(String type, UUID uuid) {

        if (type.equalsIgnoreCase("YAML")) {
            return YamlVerifyClaimReward(uuid);
        } else if (type.equalsIgnoreCase("MongoDB")) {
            return MongoDBVerifyClaimReward(uuid);
        } else {
            return false;
        }
    }

    // Verify the claim reward using YAML
    public static boolean YamlVerifyClaimReward(UUID uuid) {
        if (YamlDataManager.hasClaimedReward(uuid)) {
            return true;
        }

        return false;
    }

    // Verify the claim reward using MongoDB
    public static boolean MongoDBVerifyClaimReward(UUID uuid) {

        if (MongoDBManager.getReward(uuid)) {
            return true;
        }

        return false;
    }


    // Set the claim reward
    public static void setClaimReward(String type, UUID uuid, boolean claimReward) {

        if (type.equalsIgnoreCase("YAML")) {
            YamlDataManager.setClaimReward(uuid, claimReward);
        } else if (type.equalsIgnoreCase("MongoDB")) {
            MongoDBSetClaimReward(uuid, claimReward);
        }
    }

    // Set the claim reward using MongoDB
    public static void MongoDBSetClaimReward(UUID uuid, boolean claimReward) {
        MongoDBManager.setReward(uuid, claimReward);
    }
}
