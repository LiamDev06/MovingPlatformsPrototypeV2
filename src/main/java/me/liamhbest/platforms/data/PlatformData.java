package me.liamhbest.platforms.data;

import me.liamhbest.platforms.MovingPlatforms;
import me.liamhbest.platforms.utility.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlatformData {

    public static void createPlatform(String name){
        File file = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms", name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("name", name);

        config.set("start", "");

        config.set("end", "");

        config.set("active", false);

        try {
            config.save(file);
        } catch (IOException ignored){
            Logger.warning("Something went wrong while trying to create the platform!");
        }
    }

    public static void setStartLocation(String name, Location location){
        File file = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms", name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("start.world", location.getWorld().getName());
        config.set("start.x", location.getBlockX());
        config.set("start.y", location.getBlockY());
        config.set("start.z", location.getBlockZ());
        config.set("start.yaw", location.getYaw());

        try {
            config.save(file);
        } catch (IOException ignored){
            Logger.warning("Something went wrong while trying to set the start location of the platform!");
        }
    }

    public static void setEndLocation(String name, Location location){
        File file = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms", name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("end.world", location.getWorld().getName());
        config.set("end.x", location.getBlockX());
        config.set("end.y", location.getBlockY());
        config.set("end.z", location.getBlockZ());
        config.set("end.yaw", location.getYaw());

        try {
            config.save(file);
        } catch (IOException ignored){
            Logger.warning("Something went wrong while trying to set the end location of the platform!");
        }
    }

    public static void startPlatform(String name){
        File file = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms", name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("active", true);

        try {
            config.save(file);
        } catch (IOException ignored){
            Logger.warning("Something went wrong while trying to start the platform!");
        }
    }

    public static void endPlatform(String name){
        File file = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms", name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("active", false);

        try {
            config.save(file);
        } catch (IOException ignored){
            Logger.warning("Something went wrong while trying to stop the platform!");
        }
    }

    public static void deletePlatform(String name){
        File file = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms", name + ".yml");

        if (file.exists()) {
            file.delete();
        }
    }

    public static boolean hasStartEndLocation(String name){
        File file = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms", name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (!config.getString("start").equalsIgnoreCase("")
        && !config.getString("end").equalsIgnoreCase("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean platformExists(String name){
        File[] files = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms").listFiles();
        if (files == null) return false;
        boolean exists = false;

        for (File file : files){
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            if (configuration.getString("name").equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    public static Location getStartLocation(String name){
        File file = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms", name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Location location = new Location(
                Bukkit.getWorld(config.getString("start.world")),
                config.getInt("start.x"),
                config.getInt("start.y"),
                config.getInt("start.z"),
                (float) config.getDouble("start.yaw"),
                (float) 0.0
        );

        return location;
    }

    public static void launchPlatform(Location location){

    }

}









