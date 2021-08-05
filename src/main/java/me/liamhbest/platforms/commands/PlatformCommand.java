package me.liamhbest.platforms.commands;

import me.liamhbest.platforms.MovingPlatforms;
import me.liamhbest.platforms.data.PlatformData;
import me.liamhbest.platforms.utility.Logger;
import me.liamhbest.platforms.utility.Translated;
import me.liamhbest.platforms.utility.YawCalc;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

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

                if (!PlatformData.platformExists(name)) {
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

            if (args.length > 1){
                String name;
                try {
                    name = args[1];
                } catch (Exception e){
                    message.send("&cInvalid name!");
                    return true;
                }

                if (!PlatformData.platformExists(name)) {
                    message.send("&cThis does not exist!");
                    return true;
                }

                PlatformData.startPlatform(name);
                Location startLoc = PlatformData.getStartLocation(name);
                Location endLoc = PlatformData.getEndLocation(name);
                YawCalc yawCalc = YawCalc.getYaw(startLoc);
                startLoc.add(0, -1, 0);

                if (yawCalc == YawCalc.NORTH) {
                    Location a = startLoc.clone().add(-1, 0, -1);
                    Location b = startLoc.clone().add(0, 0, -1);
                    Location c = startLoc.clone().add(1, 0, -1);

                    int distance;
                    int startLocZ = Integer.parseInt(String.valueOf(startLoc.getBlockZ()).replace("-", ""));
                    int endLocZ = Integer.parseInt(String.valueOf(endLoc.getBlockZ()).replace("-", ""));
                    if (startLocZ > endLocZ) {
                        distance = startLocZ - endLocZ;
                    } else {
                        distance = endLocZ - startLocZ;
                    }

                    new BukkitRunnable(){
                        boolean first = true;
                        int times = 0;

                        @Override
                        public void run(){
                            if (!PlatformData.isActive(name)) this.cancel();

                            if (first) {
                                first = false;
                            } else {
                                a.add(0, 0, -3).getBlock().setType(Material.GOLD_BLOCK);
                                b.add(0, 0, -3).getBlock().setType(Material.GOLD_BLOCK);
                                c.add(0, 0, -3).getBlock().setType(Material.GOLD_BLOCK);

                                a.add(0, 0, 2);
                                b.add(0, 0, 2);
                                c.add(0, 0, 2);
                            }

                            new BukkitRunnable(){
                                @Override
                                public void run(){
                                    a.getBlock().setType(Material.AIR);
                                    b.getBlock().setType(Material.AIR);
                                    c.getBlock().setType(Material.AIR);
                                }
                            }.runTaskLater(MovingPlatforms.INSTANCE, 19);

                            times++;
                        }

                    }.runTaskTimer(MovingPlatforms.INSTANCE, 20, 20);

                }

                message.send("&bThe platform has been started.");

            } else {
                message.send("&cPlease enter a name!");
            }

        }

        if (args[0].equalsIgnoreCase("stop")) {

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

                PlatformData.endPlatform(name);
                message.send("&dPlatform has stopped.");
                PlatformData.launchPlatform(PlatformData.getStartLocation(name));

            } else {
                message.send("&cPlease enter a name!");
            }

        }

        if (args[0].equalsIgnoreCase("list")) {
            File[] files = new File(MovingPlatforms.INSTANCE.getDataFolder() + "/Platforms").listFiles();
            message.send("&7------------ &6&lPlatform List &7------------");

            for (File file : files){
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                message.send("&8- &a" + config.getString("name"));
            }

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













