package de.mariocst.commands.inventory;

import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class TNTCommand extends Command {
    public TNTCommand() {
        super("tnt", "Gibt dir TNT und Feuerzeug! DEIN INVENTAR UND DEIN HELM WIRD ERSETZT!", "tnt");
        this.setPermission("mario.tnt");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.tnt") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                try {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("remove")) {
                            for (int i = 0; i <= 36; i++) {
                                player.getInventory().clear(i);
                            }

                            player.sendMessage(MarioMain.getPrefix() + "Das TNT wurde dir entfernt!");
                        }
                        else if (args[0].equalsIgnoreCase("add")) {
                            player.getInventory().setItem(0, Item.get(ItemID.FLINT_AND_STEEL));

                            for (int i = 1; i <= 36; i++) {
                                player.getInventory().clear(i);
                            }

                            for (int i = 1; i <= 35; i++) {
                                player.getInventory().setItem(i, Item.get(BlockID.TNT));

                                for (int k = 1; k <= 63; k++) {
                                    player.getInventory().addItem(Item.get(BlockID.TNT));
                                }
                            }

                            player.getInventory().setItem(36, Item.get(BlockID.TNT));

                            player.sendMessage(MarioMain.getPrefix() + "Das TNT wurde dir hinzugefügt!");
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "/tnt <remove|add>");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else if (args.length == 2) {
                        Player t = MarioMain.getInstance().getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t != null) {
                                if (args[0].equalsIgnoreCase("remove")) {
                                    for (int i = 0; i <= 36; i++) {
                                        t.getInventory().clear(i);
                                    }

                                    t.sendMessage(MarioMain.getPrefix() + "TNT wurde dir entfernt!");
                                    player.sendMessage(MarioMain.getPrefix() + "Das TNT wurde " + t.getName() + " entfernt!");
                                }
                                else if (args[0].equalsIgnoreCase("add")) {
                                    t.getInventory().setItem(0, Item.get(ItemID.FLINT_AND_STEEL));

                                    for (int i = 1; i <= 36; i++) {
                                        t.getInventory().clear(i);
                                    }

                                    for (int i = 1; i <= 35; i++) {
                                        t.getInventory().setItem(i, Item.get(BlockID.TNT));

                                        for (int k = 1; k <= 63; k++) {
                                            t.getInventory().addItem(Item.get(BlockID.TNT));
                                        }
                                    }

                                    t.getInventory().setItem(36, Item.get(BlockID.TNT));

                                    t.sendMessage(MarioMain.getPrefix() + "TNT wurde dir hinzugefügt!");
                                    player.sendMessage(MarioMain.getPrefix() + "Das TNT wurde " + t.getName() + " hinzugefügt!");
                                }
                                else {
                                    player.sendMessage(MarioMain.getPrefix() + "/tnt <remove|add> <Name>");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                }
                            }
                            else {
                                player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                        catch (NullPointerException e) {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/tnt <remove|add> [Spieler]!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MarioMain.getPrefix() + "/tnt <remove|add> [Spieler]!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            try {
                if (args.length == 2) {
                    Player t = MarioMain.getInstance().getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
                            if (args[0].equalsIgnoreCase("remove")) {
                                for (int i = 0; i <= 36; i++) {
                                    t.getInventory().clear(i);
                                }

                                t.sendMessage(MarioMain.getPrefix() + "TNT wurde dir entfernt!");
                                sender.sendMessage(MarioMain.getPrefix() + "Das TNT wurde " + t.getName() + " entfernt!");
                            }
                            else if (args[0].equalsIgnoreCase("add")) {
                                t.getInventory().setItem(0, Item.get(ItemID.FLINT_AND_STEEL));

                                for (int i = 1; i <= 36; i++) {
                                    t.getInventory().clear(i);
                                }

                                for (int i = 1; i <= 35; i++) {
                                    t.getInventory().setItem(i, Item.get(BlockID.TNT));

                                    for (int k = 1; k <= 63; k++) {
                                        t.getInventory().addItem(Item.get(BlockID.TNT));
                                    }
                                }

                                t.getInventory().setItem(36, Item.get(BlockID.TNT));

                                t.sendMessage(MarioMain.getPrefix() + "TNT wurde dir hinzugefügt!");
                                sender.sendMessage(MarioMain.getPrefix() + "Das TNT wurde " + t.getName() + " hinzugefügt!");
                            }
                            else {
                                sender.sendMessage(MarioMain.getPrefix() + "/tnt <remove|add> <Name>");
                            }
                        }
                        else {
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/tnt <remove|add> <Spieler>!");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(MarioMain.getPrefix() + "/tnt <remove|add> <Spieler>!");
            }
        }
        return false;
    }
}
