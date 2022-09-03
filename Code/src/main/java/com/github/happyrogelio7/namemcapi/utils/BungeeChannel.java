package com.github.happyrogelio7.namemcapi.utils;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeChannel implements PluginMessageListener {

    private NameMCAPI plugin;

    public BungeeChannel(NameMCAPI plugin) {
        this.plugin = plugin;
    }

    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord"))
            return;
        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String subchannel = input.readUTF();
    }

    public void connect(Player p, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        p.sendPluginMessage((Plugin)this.plugin, "BungeeCord", output.toByteArray());
    }

}
