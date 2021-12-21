package de.mariocst.listeners;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.item.Item;

public class ScaffoldListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("mario.scaffold") && !player.hasPermission("mario.*") &&
                !player.hasPermission("*") && !player.isOp()) {
            return;
        }

        Block blockBelow = player.getLocation().getLevelBlock().getLocation().add(0, -1, 0).getLevelBlock().getBlock();
        if (blockBelow.getId() != 0 || player.isSneaking()) {
            return;
        }

        Item itemInHand = player.getInventory().getItemInHand();
        if (!itemInHand.hasCustomName() || itemInHand.getId() == 0 || !itemInHand.getCustomName().equalsIgnoreCase("§fScaffold Wool")) {
            return;
        }

        player.getLocation().getLevel().setBlock(player.getLocation().getLevelBlock().getLocation().add(0, -1, 0), Block.get(BlockID.WOOL));
    }
}
