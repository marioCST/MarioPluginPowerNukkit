package de.mariocst.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import de.mariocst.MarioMain;

public class FakeHackListener implements Listener {
    @EventHandler
    public void noFall(PlayerMoveEvent event) {
        for (Player trolled : MarioMain.getInstance().noFallHack) {
            if (trolled.fallDistance > 2) {
                trolled.resetFallDistance();
            }
        }
    }
}
