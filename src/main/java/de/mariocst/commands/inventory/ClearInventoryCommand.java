package de.mariocst.commands.inventory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ClearInventoryCommand extends Command {
    public ClearInventoryCommand() {
        super("clearinventory", "Cleart dein Inventar", "clear", new String[]{"clear", "ci", "cleari"});
        this.setPermission("mario.clearinventory");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mario.clearinventory") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage(MarioMain.getPrefix() + "Dein Inventar wurde geleert!");
                    player.getInventory().clearAll();
                }
                else if (args.length == 1) {
                    Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                    if (t != null) {
                        t.sendMessage(MarioMain.getPrefix() + "Dein Inventar wurde geleert!");
                        t.getInventory().clearAll();
                        player.sendMessage(MarioMain.getPrefix() + "Das Inventar von " + t.getName() + " wurde geleert!");
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "/clearinventory [Spieler]!");
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
                    t.sendMessage(MarioMain.getPrefix() + "Dein Inventar wurde geleert!");
                    t.getInventory().clearAll();
                    sender.sendMessage(MarioMain.getPrefix() + "Das Inventar von " + t.getName() + " wurde geleert!");
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "/clearinventory [Spieler]!");
            }
        }
        return false;
    }

}
