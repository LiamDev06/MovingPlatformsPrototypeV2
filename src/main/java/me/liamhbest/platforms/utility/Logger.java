package me.liamhbest.platforms.utility;

import org.bukkit.Bukkit;

public class Logger {

    public static void log(String logMessage){
        final String prefix = "&7[&bMovingPlatforms Prototype&7] &f";
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + logMessage));
    }

    public static void warning(String warningMessage){
        final String prefix = "&c&l[MovingPlatforms Prototype] &c";
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + warningMessage));
    }

}
