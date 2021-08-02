package me.liamhbest.platforms.utility;

import org.bukkit.entity.Player;

public class Translated {

    private final Player player;

    public Translated(Player player){
        this.player = player;
    }

    public void send(String message){
        player.sendMessage(CC.translate(message));
    }

}
