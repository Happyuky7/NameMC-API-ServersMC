package com.github.happyuky7.nameMCAPIServersMCCommon.api;

public class NameMCAPI {

    private static NameMCAPI instance;

    public static NameMCAPI getInstance() {
        if (instance == null) {
            instance = new NameMCAPI();
        }
        return instance;
    }

    public DateAPI getDate() {
        return new DateAPI();
    }

    public void getVote(String uuid, String serverip) {
        NameMCAPIGET.getVote(uuid, serverip);
    }

    public void getMojangUUID(String name) {
        MojangAPIManager.getUUID(name);
    }

    /*public void getColor(String message) {
        MessageColors.getMsgColor(message);
    }*/

    public void getServerType() {
        if (isSpigot()) {
            System.out.println("Server Type: Spigot");
        } else if (isBungee()) {
            System.out.println("Server Type: Bungee");
        } else if (isVelocity()) {
            System.out.println("Server Type: Velocity");
        } else {
            System.out.println("Server Type: Unknown");
        }
    }

    public boolean isSpigot() {
        return org.bukkit.Bukkit.getServer() != null;
    }

    public boolean isBungee() {
        try {
            Class.forName("net.md_5.bungee.api.ProxyServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public boolean isVelocity() {
        try {
            Class.forName("com.velocitypowered.api.proxy.ProxyServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public class DateAPI {

        public String date() {
            return DateInfo.getSystemDate();
        }

        public String date(String format) {
            return DateInfo.getSystemDate(format);
        }

        public String dateDay() {
            return DateInfo.getSystemDateDay();
        }

        public String dateMonth() {
            return DateInfo.getSystemDateMonth();
        }

        public String dateYear() {
            return DateInfo.getSystemDateYear();
        }

        public String dateDay(String format) {
            return DateInfo.getSystemDateDay(format);
        }

        public String dateMonth(String format) {
            return DateInfo.getSystemDateMonth(format);
        }

        public String dateYear(String format) {
            return DateInfo.getSystemDateYear(format);
        }
    }

}
