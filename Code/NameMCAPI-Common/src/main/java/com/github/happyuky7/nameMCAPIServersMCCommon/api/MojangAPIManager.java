package com.github.happyuky7.nameMCAPIServersMCCommon.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * A utility class that interacts with the Mojang API to retrieve information
 * about Minecraft users, specifically their UUID based on the player's username.
 * <p>
 * This class is designed to provide a simple method for obtaining the UUID of a player
 * using the Mojang API endpoint.
 * </p>
 */
public final class MojangAPIManager {

    /**
     * Retrieves the Mojang UUID for a Minecraft player based on their username.
     * This method sends a request to the Mojang API and parses the response to get
     * the UUID of the player.
     *
     * @param name the Minecraft username for which to retrieve the UUID.
     * @return the UUID of the player as a String, or null if there is an error.
     */
    public static String getUUID(String name) {
        try {

            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonObject object = (new JsonParser()).parse(reader).getAsJsonObject();
            return object.get("id").getAsString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
