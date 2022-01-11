package de.mariocst.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import de.mariocst.MarioMain;

public class FreezedListener implements Listener {
    @EventHandler
    public void onFreeze(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        for (Player ignored : MarioMain.getInstance().freezed.keySet()) {
            if (player.getLocation().getX() > MarioMain.getInstance().freezed.get(player).getX() + 0.5 ||
                    player.getLocation().getX() < MarioMain.getInstance().freezed.get(player).getX() - 0.5 ||
                    player.getLocation().getY() > MarioMain.getInstance().freezed.get(player).getY() + 0.5 ||
                    player.getLocation().getY() < MarioMain.getInstance().freezed.get(player).getY() - 0.5 ||
                    player.getLocation().getZ() > MarioMain.getInstance().freezed.get(player).getZ() + 0.5 ||
                    player.getLocation().getZ() < MarioMain.getInstance().freezed.get(player).getZ() - 0.5) {
                player.teleport(MarioMain.getInstance().freezed.get(player));
                event.setCancelled(true);
                player.kick("AntiImmobile", false);
            }
        }
    }
}
