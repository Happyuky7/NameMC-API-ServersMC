package com.github.happyrogelio7.namemcapi.managers;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MojangAPIManager {

    private NameMCAPI plugin;

    public MojangAPIManager(NameMCAPI plugin){
        this.plugin = plugin;
    }

    public static String getDataMojanAPI(Player player){

        try {

            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + player.getName());
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            JsonObject object = (new JsonParser()).parse(reader.readLine()).getAsJsonObject();
            return object.get("id").getAsString();

        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("Plugin ERROR x001");
            return "Plugin ERROR x001";

        }

    }

    public static String getDataMojanAPITarget(String args){

        try {

            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + args);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            JsonObject object = (new JsonParser()).parse(reader.readLine()).getAsJsonObject();
            return object.get("id").getAsString();

        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("Plugin ERROR x003");
            return "Plugin ERROR x003";

        }

    }
}
