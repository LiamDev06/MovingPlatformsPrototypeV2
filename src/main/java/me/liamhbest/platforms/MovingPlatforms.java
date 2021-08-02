package me.liamhbest.platforms;

import me.liamhbest.platforms.commands.PlatformCommand;
import me.liamhbest.platforms.utility.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MovingPlatforms extends JavaPlugin {

    public static MovingPlatforms INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Logger.log("Starting plugin...");

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        registerListeners();
        registerCommands();

        Logger.log("Plugin has been enabled without any errors.");
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
        Logger.log("Disabling plugin...");
        Logger.log("Plugin has been disabled without errors.");
    }

    public void registerListeners(){
        PluginManager pm = this.getServer().getPluginManager();
    }

    public void registerCommands(){
        getCommand("platform").setExecutor(new PlatformCommand());
    }

}









