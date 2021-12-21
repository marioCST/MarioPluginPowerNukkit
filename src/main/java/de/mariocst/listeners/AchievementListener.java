package de.mariocst.listeners;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerAchievementAwardedEvent;

public class AchievementListener implements Listener {
    @EventHandler
    public void onAchievementAward(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }
}
