package com.github.happyuky7.nameMCAPIServersMC.managers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NameMCAPIGET {

    public static final Gson gson = new Gson();

    public static String getURLVote() {
        return "https://api.namemc.com/server/<server-ip>/likes?profile=<uuid>";
    }

    public static String getURLALLVotes() {
        return "https://api.namemc.com/server/<server-ip>/likes";
    }

    public static String getVote(String uuid, String serverIP) {
        try {
            String url = getURLVote()
                    .replaceAll("<server-ip>", serverIP)
                    .replaceAll("<uuid>", uuid);
            HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                System.out.println("responseCode == 200");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

                boolean hasVoted = Boolean.parseBoolean(response.toString());
                System.out.println("hasVoted: " + hasVoted);

                return Boolean.valueOf(hasVoted).toString();

            } else {
                System.out.println("responseCode != 200");
                System.out.println("Error ID: 003");
                return Boolean.valueOf(false).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ID: 004");
        }

        return Boolean.valueOf(false).toString();
    }

    /*public static String getVotes(String uuid, String serverIP) {

        System.out.println("getVotes: " + uuid + " " + serverIP);
        if (uuid == null) return Boolean.valueOf(false).toString();

        System.out.println("start try");
        try {
            System.out.println("start try 2");
            String url = getURLVote()
                    .replaceAll("<server-ip>", serverIP)
                    .replaceAll("<uuid>", uuid);
            HttpURLConnection connection = (HttpURLConnection)(new URL(url)).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("responseCode: " + responseCode);
            if (responseCode == 200) {
                System.out.println("responseCode == 200");
                return Boolean.valueOf(true).toString();
            } else {
                System.out.println("responseCode != 200");
                return Boolean.valueOf(false).toString();
            }
        } catch (Exception e) {
            System.out.println("catch");
            e.printStackTrace();
        }

        System.out.println("return false");
        return Boolean.valueOf(false).toString();
    }*/

    public static String getAllVotesUUIDs(String serverIP) {
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

    }

}
