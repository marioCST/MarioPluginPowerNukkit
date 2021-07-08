package de.mariocst.Listeners;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.level.Location;
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
