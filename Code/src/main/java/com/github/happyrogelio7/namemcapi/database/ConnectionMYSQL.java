package com.github.happyrogelio7.namemcapi.database;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;

public class ConnectionMYSQL {

    private NameMCAPI plugin;

    public ConnectionMYSQL(NameMCAPI plugin) {
        this.plugin = plugin;
    }

    private Connection connection;
    private String host; //plugin.getConfig().getString("Save-Data.mysql-options.mysql-ip");
    private int port; //plugin.getConfig().getInt("Save-Data.mysql-options.mysql-port");
    private String database; //plugin.getConfig().getString("Save-Data.mysql-options.mysql-database");
    private String user; //plugin.getConfig().getString("Save-Data.mysql-options.mysql-user");
    private String password; //plugin.getConfig().getString("Save-Data.mysql-options.mysql-password");

    public ConnectionMYSQL (String host, int port, String database, String user, String password){

        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;

        try {
            synchronized(this) {
                if(connection != null && !connection.isClosed()) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&fNameMC&9]&r &cError connecting to MySQL."));
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database,this.user,this.password);

                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&fNameMC&9]&r &aPlugin connected with MySQL."));
            }
        }catch(SQLException e) {

        }catch(ClassNotFoundException e) {

        }


    }

    public Connection getConnection() {
        return connection;
    }
}

