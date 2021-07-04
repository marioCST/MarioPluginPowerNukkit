package de.mariocst.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import de.mariocst.MarioMain;

public class MoveListener implements Listener {
    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        for (Player trolled : MarioMain.getInstance().moveTroll) {
            event.setCancelled(true);
        }
    }
}
