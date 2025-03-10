package com.github.happyuky7.nameMCAPIServersMCCommon.api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class NameMCAPIGET {

    public static final Gson gson = new Gson();

    public static String getURLVote() {
        return "https://api.namemc.com/server/<server-ip>/likes?profile=<uuid>";
    }

    public static String getURLALLVotes() {
        return "https://api.namemc.com/server/<server-ip>/likes";
    }

    // Get the vote of a player
    public static boolean getVote(UUID uuid, String serverIP) {
        try {

            if (uuid == null || serverIP == null) {
                throw new IllegalArgumentException("UUID or Server IP cannot be null.");
                //return false;
            }

            String url = getURLVote()
                    .replaceAll("<server-ip>", serverIP)
                    .replaceAll("<uuid>", String.valueOf(uuid));
            HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                //System.out.println("responseCode == 200");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

                boolean hasVoted = Boolean.parseBoolean(response.toString());
                //System.out.println("hasVoted: " + hasVoted);

                return hasVoted;

            } else {
                //System.out.println("responseCode != 200");
                //System.out.println("Error ID: 003");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Error ID: 004");
        }

        return false;
    }

    // Get the vote of a player with timeout
    public static boolean getVote(UUID uuid, String serverIP, int timeout) {
        try {
            if (uuid == null || serverIP == null) {
                throw new IllegalArgumentException("UUID or Server IP cannot be null.");
            }

            String url = getURLVote()
                    .replaceAll("<server-ip>", serverIP)
                    .replaceAll("<uuid>", String.valueOf(uuid));
            HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                boolean hasVoted = Boolean.parseBoolean(response.toString());
                return hasVoted;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    // in the future and for now, this method is not used
    // Recode this method to found correct.
    /*public static String getAllVotesUUIDs(String serverIP) {
        try {

            String url = getURLALLVotes()
                    .replaceAll("<server-ip>", serverIP);
            HttpURLConnection connection = (HttpURLConnection)(new URL(url)).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            } else {
                return "Error: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred!";
        }
    }

    public static Integer getAllVotesNumber(String serverIP) {
        try {

            String url = getURLALLVotes()
                    .replaceAll("<server-ip>", serverIP);
            HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JsonArray jsonArray = (JsonArray) new JsonParser().parse(response.toString());

                return jsonArray.size();

            } else {
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }*/

}

