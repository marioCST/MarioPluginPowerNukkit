package de.mariocst.Commands.Inventory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ClearEnderchestCommand extends Command {
    private MarioMain plugin;

    public ClearEnderchestCommand(MarioMain plugin) {
        super("clearenderchest", "Cleart deine EnderChest", "clearenderchest", new String[]{"cec", "clearec"});
        this.setPermission("mario.clearenderchest");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.clearenderchest") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage(MarioMain.getPrefix() + "Deine Enderchest wurde geleert!");
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
                        MarioMain.unknownPlayer(player);
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/clearenderchest [Spieler]!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            if (args.length == 1) {
                Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                if (t != null) {
                    t.sendMessage(MarioMain.getPrefix() + "Deine Enderchest wurde geleert!");
                    t.getEnderChestInventory().clearAll();
                    sender.sendMessage(MarioMain.getPrefix() + "Die Enderchest von " + t.getName() + " wurde geleert!");
                }
                else {
                    MarioMain.unknownPlayer(sender);
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "/clearenderchest [Spieler]!");
            }
        }
        return false;
    }
}
