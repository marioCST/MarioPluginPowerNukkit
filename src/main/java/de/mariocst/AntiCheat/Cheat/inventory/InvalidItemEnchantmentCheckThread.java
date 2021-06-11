package de.mariocst.AntiCheat.Cheat.inventory;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.permission.BanEntry;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.ConfigSection;
import de.mariocst.Config.PlayerIllegalItems;
import de.mariocst.MarioMain;

import java.time.Instant;
import java.util.Date;

public class InvalidItemEnchantmentCheckThread extends Task {
    @Override
    public void onRun(int i) {
        try {
            for (Player player : Server.getInstance().getOnlinePlayers().values()) {
                if (!player.isOp() && MarioMain.getInstance().getMasterConfig().getInvalidItemEnchantmentCheck()) {
                    for (Item item : player.getInventory().slots.values()) {
                        if (item.hasEnchantments()) {
                            for (Enchantment e : item.getEnchantments()) {
                                if (e.getLevel() >= 10) {
                                    Server server = MarioMain.getInstance().getServer();

                                    player.getInventory().clearAll();
                                    String ban = "Invalid Enchantment Level detected! Please contact an Admin for a ban Appeal!";

                                    server.getNameBans().addBan(player.getName(), ban, Date.from(Instant.ofEpochSecond(7257600000L)), "Console");
                                    server.getNameBans().setEnable(true);
                                    server.getNameBans().save();
                                    server.getIPBans().addBan(player.getAddress(), ban, Date.from(Instant.ofEpochSecond(7257600000L)), "Console");
                                    server.getIPBans().setEnable(true);
                                    server.getIPBans().save();
                                    player.kick(ban);

                                    MarioMain.addIllegalPlayer(player);

                                    server.broadcastMessage("The Player " + player.getName() + " has been banned for an illegal Enchantment! " + e.getName() + " Lv. " + e.getLevel());
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
