package de.mariocst.Commands.Inventory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ClearInventoryCommand extends Command {
    private MarioMain plugin;

    public ClearInventoryCommand(MarioMain plugin) {
        super("clearinventory", "Cleart dein Inventar", "clear", new String[]{"clear", "ci", "cleari"});
        this.setPermission("mario.clearinventory");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.clearinventory") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage(MarioMain.getPrefix() + "Dein Inventar wurde geleert!");
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
                        MarioMain.unknownPlayer(player);
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/clearinventory [Spieler]!");
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
                    t.sendMessage(MarioMain.getPrefix() + "Dein Inventar wurde geleert!");
                    t.getInventory().clearAll();
                    sender.sendMessage(MarioMain.getPrefix() + "Das Inventar von " + t.getName() + " wurde geleert!");
                }
                else {
                    MarioMain.unknownPlayer(sender);
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "/clearinventory [Spieler]!");
            }
        }
        return false;
    }

}
