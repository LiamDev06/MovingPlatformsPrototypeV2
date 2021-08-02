package me.liamhbest.platforms.utility;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {

    public static void playSound(Player player, Sound sound){
        player.playSound(player.getLocation(), sound, 10, 1);
    }

    public static void playSound(Player player, Sound sound, float volume, float pitch){
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public static void playSound(Player player, Sound sound, float volume){
        player.playSound(player.getLocation(), sound, volume, 1);
    }

    public static void playClickSound(Player player){
        player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 10, 2);
    }

    public static void playErrorSound(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, -2);
    }

}
