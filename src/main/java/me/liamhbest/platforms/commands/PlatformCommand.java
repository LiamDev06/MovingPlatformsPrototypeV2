package me.liamhbest.platforms.commands;

import me.liamhbest.platforms.data.PlatformData;
import me.liamhbest.platforms.utility.Logger;
import me.liamhbest.platforms.utility.Translated;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlatformCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.warning("Only a player can perform this command!");
            return true;
        }
        Player player = (Player) sender;
        Translated message = new Translated(player);

        if (args.length == 0){
            message.send("&aPlease enter the right arguments!");
            return true;
        }

        if (args[0].equalsIgnoreCase("create")) {

            if (args.length > 1){
                String name;

                try {
                    name = args[1];
                } catch (Exception e){
                    message.send("&cInvalid name!");
                    return true;
                }

                if (PlatformData.platformExists(name)) {
                    message.send("&cThis platform already exists!");
                    return true;
                }

                PlatformData.createPlatform(name);
                message.send("&aThe platform has been created. Please enter a start and end location.");

            } else {
                message.send("&cPlease specify a name with /platform create [name]");
            }

        }

        if (args[0].equalsIgnoreCase("delete")) {

            if (args.length > 1){
                String name;
                try {
                    name = args[1];
                } catch (Exception e){
                    message.send("&cInvalid name!");
                    return true;
                }

                if (PlatformData.platformExists(name)) {
                    message.send("&cThis platform does not exist!");
                    return true;
                }

                PlatformData.deletePlatform(name);

                message.send("&bPlatform deleted.");
            } else {
                message.send("&cPlease specify the name.");
            }

        }

        if (args[0].equalsIgnoreCase("start")) {

            if (args.length > 1){
                Location location = player.getLocation();
                String name;

                try {
                    name = args[1];
                } catch (Exception e){
                    message.send("&cInvalid name!");
                    return true;
                }

                if (!PlatformData.platformExists(name)) {
                    message.send("&cThis platform does not exist!");
                    return true;
                }

                PlatformData.setStartLocation(name, location);
                message.send("&aYou set the start location.");

            } else {
                message.send("&cPlease specify which platform. Use /platform start [name]");
            }

        }

        if (args[0].equalsIgnoreCase("end")) {

            if (args.length > 1){
                Location location = player.getLocation();
                String name;

                try {
                    name = args[1];
                } catch (Exception e){
                    message.send("&cInvalid name!");
                    return true;
                }

                if (!PlatformData.platformExists(name)) {
                    message.send("&cThis platform does not exist!");
                    return true;
                }

                PlatformData.setEndLocation(name, location);
                message.send("&aYou set the end location.");

            } else {
                message.send("&cPlease specify which platform. Use /platform end [name]");
            }

        }

        if (args[0].equalsIgnoreCase("activate")) {

        }

        if (args[0].equalsIgnoreCase("stop")) {

        }

        if (args[0].equalsIgnoreCase("launch")) {

            if (args.length > 1){
                String name;
                try {
                    name = args[1];
                } catch (Exception e){
                    message.send("&cInvalid name!");
                    return true;
                }

                if (!PlatformData.platformExists(name)) {
                    message.send("&cThis platform does not exist!");
                    return true;
                }

                if (!PlatformData.hasStartEndLocation(name)) {
                    message.send("&cYou need to set both the start and end location first!");
                    return true;
                }

                Location location = PlatformData.getStartLocation(name);
                PlatformData.launchPlatform(location);

                message.send("&6This platform has been launched!");
            } else {
                message.send("&cPlease specify a name!");
            }

        }

        return false;
    }




}













