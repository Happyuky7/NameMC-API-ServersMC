package com.github.happyuky7.nameMCAPIServersMC.managers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public final class MojangAPIManager {

    public static String getUUID(String name) {
        try {

            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonObject object = (new JsonParser()).parse(reader).getAsJsonObject();
            return object.get("id").getAsString();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ID: 001");
            return null;
        }

    }

}
