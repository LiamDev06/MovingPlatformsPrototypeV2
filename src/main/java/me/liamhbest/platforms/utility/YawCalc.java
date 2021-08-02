package me.liamhbest.platforms.utility;

import org.bukkit.entity.Player;

public enum YawCalc {

    NORTH, SOUTH, EAST, WEST;

    public static YawCalc getYaw(Player p) {
        float yaw = p.getLocation().getYaw();
        yaw = (yaw % 360 + 360) % 360;
        if (yaw > 135 || yaw < -135) {
            return YawCalc.NORTH;
        } else if (yaw < -45) {
            return YawCalc.EAST;
        } else if (yaw > 45) {
            return YawCalc.WEST;
        } else {
            return YawCalc.SOUTH;
        }
    }

}
