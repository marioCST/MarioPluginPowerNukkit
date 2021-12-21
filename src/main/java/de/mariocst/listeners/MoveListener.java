package de.mariocst.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import de.mariocst.MarioMain;

public class MoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        for (Player ignored : MarioMain.getInstance().moveTroll) {
            event.setCancelled(true);
        }
    }
}
