package de.mariocst.Listeners;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerAchievementAwardedEvent;

public class AchievementListener implements Listener {
    @EventHandler
    public void PlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }
}
