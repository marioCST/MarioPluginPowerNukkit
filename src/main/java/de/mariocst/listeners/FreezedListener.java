package de.mariocst.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.level.Location;
import de.mariocst.MarioMain;

public class FreezedListener implements Listener {
    @EventHandler
    public void onFreeze(PlayerMoveEvent event) {
        for (Player freezed : MarioMain.getInstance().freezed) {
            if (event.getFrom().getX() != freezed.getX() || event.getFrom().getY() != freezed.getY() || event.getFrom().getZ() != freezed.getZ()) {
                event.setTo(new Location(event.getFrom().getX(), event.getFrom().getY(), event.getFrom().getZ(), freezed.getPitch(), freezed.getYaw()));
                event.setCancelled(true);
            }
        }
    }
}
