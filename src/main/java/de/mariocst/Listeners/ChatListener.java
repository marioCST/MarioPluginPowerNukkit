package de.mariocst.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import de.mariocst.MarioMain;

public class ChatListener implements Listener {
    @EventHandler
    public void onStaffChat(PlayerChatEvent event) {
        Player player = event.getPlayer();

        if (MarioMain.getInstance().staffChat.contains(player)) {
            event.setCancelled(true);

            MarioMain.getInstance().getServer().getConsoleSender().sendMessage("§8[§6StaffChat§8] §7" + player.getName() + " §8>> §6" + event.getMessage().replaceAll("&", "§"));

            for (Player staff : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                if (staff.hasPermission("mario.staff") || staff.hasPermission("mario.*") || staff.hasPermission("*") || staff.isOp()) {
                    staff.sendMessage("§8[§6StaffChat§8] §7" + player.getName() + " §8>> §6" + event.getMessage().replaceAll("&", "§"));
                }
            }
        }
    }

    @EventHandler
    public void onMutedChat(PlayerChatEvent event) {
        Player player = event.getPlayer();

        if (MarioMain.getInstance().muted.contains(player)) {
            event.setCancelled(true);
        }
    }
}
