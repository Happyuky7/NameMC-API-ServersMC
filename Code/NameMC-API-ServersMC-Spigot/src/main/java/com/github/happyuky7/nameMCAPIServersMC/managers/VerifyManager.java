package com.github.happyuky7.nameMCAPIServersMC.managers;

import com.github.happyuky7.nameMCAPIServersMC.managers.data.YamlDataManager;

public class VerifyManager {

    // Verify the vote
    public static boolean verify(String type, String username, String uuid, String ip) {

        if (type.equalsIgnoreCase("YAML")) {
            return YamlVerify(username, uuid, ip);
        } else if (type.equalsIgnoreCase("MongoDB")) {
            // MongoDB code here
        } else {
            return false;
        }

        return false;
    }

    // Verify the vote using YAML
    public static boolean YamlVerify(String username, String uuid, String ip) {
        if (YamlDataManager.hasVoted(uuid)) {
            return true;
        }

        if (NameMCAPIGET.getVotes(uuid, ip).equals(Boolean.valueOf(true).toString())) {
            YamlDataManager.setVote(username, uuid, true);
            return true;
        }

        return false;
    }

    // Verify the vote using MongoDB
    public static boolean MongoDBVerify(String username, String uuid, String ip) {
        // MongoDB code here
        return false;
    }


}
