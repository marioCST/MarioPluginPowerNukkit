package de.mariocst.commands.inventory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ClearEnderchestCommand extends Command {
    public ClearEnderchestCommand() {
        super("clearenderchest", "Cleart deine EnderChest", "clearenderchest", new String[]{"cec", "clearec"});
        this.setPermission("mario.clearenderchest");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.clearenderchest") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage(MarioMain.getPrefix() + "Deine Enderchest wurde geleert!");
                    player.getEnderChestInventory().clearAll();
                }
                else if (args.length == 1) {
                    Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                    if (t != null) {
                        t.sendMessage(MarioMain.getPrefix() + "Deine Enderchest wurde geleert!");
                        t.getEnderChestInventory().clearAll();
                        player.sendMessage(MarioMain.getPrefix() + "Die Enderchest von " + t.getName() + " wurde geleert!");
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "/clearenderchest [Spieler]!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            if (args.length == 1) {
                Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                if (t != null) {
                    t.sendMessage(MarioMain.getPrefix() + "Deine Enderchest wurde geleert!");
                    t.getEnderChestInventory().clearAll();
                    sender.sendMessage(MarioMain.getPrefix() + "Die Enderchest von " + t.getName() + " wurde geleert!");
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "/clearenderchest [Spieler]!");
            }
        }
        return false;
    }
}
