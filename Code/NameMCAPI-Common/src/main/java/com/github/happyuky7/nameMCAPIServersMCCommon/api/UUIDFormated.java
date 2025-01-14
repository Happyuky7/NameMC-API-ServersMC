package com.github.happyuky7.nameMCAPIServersMCCommon.api;

import java.util.UUID;

public class UUIDFormated {

    public static UUID fromStringWithHyphens(String uuidString) {
        if (uuidString.length() == 32) {
            uuidString = uuidString.substring(0, 8) + "-" + uuidString.substring(8, 12) + "-" + uuidString.substring(12, 16) + "-" + uuidString.substring(16, 20) + "-" + uuidString.substring(20);
        }
        return UUID.fromString(uuidString);
    }

    public static String toStringWithHyphens(UUID uuid) {
        return uuid.toString();
    }

    public static String toStringWithoutHyphens(UUID uuid) {
        return uuid.toString().replace("-", "");
    }

    public static UUID fromStringWithoutHyphens(String uuidString) {
        return UUID.fromString(uuidString);
    }

    public static boolean isValidUUID(String uuidString) {
        if (uuidString.length() == 32) {
            uuidString = uuidString.substring(0, 8) + "-" + uuidString.substring(8, 12) + "-" + uuidString.substring(12, 16) + "-" + uuidString.substring(16, 20) + "-" + uuidString.substring(20);
        }
        try {
            UUID.fromString(uuidString);
            return true;
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public static boolean isValidUUIDDebug(String uuidString) {
        System.out.println("uuidString 1: " + uuidString);
        if (uuidString.length() == 32) {
            System.out.println("uuidString.length() == 32");
            uuidString = uuidString.substring(0, 8) + "-" + uuidString.substring(8, 12) + "-" + uuidString.substring(12, 16) + "-" + uuidString.substring(16, 20) + "-" + uuidString.substring(20);
            System.out.println("uuidString 2: " + uuidString);
        }
        System.out.println("Start try");
        try {
            System.out.println("Start UUID.fromString(uuidString)");
            UUID.fromString(uuidString);
            System.out.println("End UUID.fromString(uuidString)");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }


}
