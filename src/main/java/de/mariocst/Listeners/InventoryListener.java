package de.mariocst.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryClickEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import de.mariocst.MarioMain;

public class InventoryListener implements Listener {
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        Player player = event.getPlayer();

        for (Player trolled : MarioMain.getInstance().invTroll) {
            if (event.getInventory() == player.getInventory()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void PlayerDropItemEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        for (Player trolled : MarioMain.getInstance().invTroll) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void InventoryPickupItemEvent(InventoryPickupItemEvent event) {
        for (Player trolled : MarioMain.getInstance().invTroll) {
            for (Player viewer : event.getViewers()) {
                if (viewer == trolled) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
