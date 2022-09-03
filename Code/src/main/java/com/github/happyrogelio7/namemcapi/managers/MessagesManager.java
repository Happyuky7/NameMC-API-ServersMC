package com.github.happyrogelio7.namemcapi.managers;

import com.github.happyrogelio7.namemcapi.NameMCAPI;
import com.github.happyrogelio7.namemcapi.utils.MessageColors;

public class MessagesManager {

    public static String getSendMSG(NameMCAPI plugin, String path){
        return MessageColors.getMsgColor(plugin.getLangs().getString(path));
    }
    
}
