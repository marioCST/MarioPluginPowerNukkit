package de.mariocst.listeners;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFoodLevelChangeEvent;

public class HungerListener implements Listener {
    @EventHandler
    public void onHungerLevelChange(PlayerFoodLevelChangeEvent event) {
        if (event.getFoodLevel() < 20) {
            event.setFoodLevel(20);
        }

        if (event.getFoodSaturationLevel() < 20.0F) {
            event.setFoodSaturationLevel(20.0F);
        }
    }
}
