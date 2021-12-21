package de.mariocst.commands.inventory;

import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class GiveItemsCommand extends Command {
    public GiveItemsCommand() {
        super("giveitems", "Gibt dir gute Items!", "giveitems", new String[]{"gi"});
        this.setPermission("mario.giveitems");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.giveitems") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                try {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("remove")) {
                            player.getInventory().clear(36);
                            player.getInventory().clear(37);
                            player.getInventory().clear(38);
                            player.getInventory().clear(39);

                            player.getInventory().clear(0);
                            player.getInventory().clear(1);
                            player.getInventory().clear(2);
                            player.getInventory().clear(3);
                            player.getInventory().clear(4);

                            player.sendMessage(MarioMain.getPrefix() + "Die Items wurden dir entfernt!");
                        }
                        else if (args[0].equalsIgnoreCase("add")) {
                            player.getInventory().clear(36);
                            player.getInventory().clear(37);
                            player.getInventory().clear(38);
                            player.getInventory().clear(39);

                            player.getInventory().clear(0);
                            player.getInventory().clear(1);
                            player.getInventory().clear(2);
                            player.getInventory().clear(3);
                            player.getInventory().clear(4);
                            player.getInventory().clear(5);

                            player.getInventory().setItem(36, Item.get(ItemID.NETHERITE_HELMET));
                            player.getInventory().setItem(37, Item.get(ItemID.NETHERITE_CHESTPLATE));
                            player.getInventory().setItem(38, Item.get(ItemID.NETHERITE_LEGGINGS));
                            player.getInventory().setItem(39, Item.get(ItemID.NETHERITE_BOOTS));

                            player.getInventory().setItem(0, Item.get(ItemID.NETHERITE_SWORD));
                            player.getInventory().setItem(1, Item.get(ItemID.BOW));
                            player.getInventory().setItem(2, Item.get(ItemID.NETHERITE_PICKAXE));
                            player.getInventory().setItem(3, Item.get(ItemID.ARROW));
                            player.getInventory().setItem(4, Item.get(BlockID.OBSIDIAN));

                            for (int i = 1; i <= 63; i++) {
                                player.getInventory().addItem(Item.get(ItemID.ARROW), Item.get(BlockID.OBSIDIAN));
                            }

                            player.sendMessage(MarioMain.getPrefix() + "Die Items wurden dir hinzugefügt!");
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "/giveitems <remove|add>");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else if (args.length == 2) {
                        Player t = MarioMain.getInstance().getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t != null) {
                                if (args[0].equalsIgnoreCase("remove")) {
                                    t.getInventory().clear(36);
                                    t.getInventory().clear(37);
                                    t.getInventory().clear(38);
                                    t.getInventory().clear(39);

                                    t.getInventory().clear(0);
                                    t.getInventory().clear(1);
                                    t.getInventory().clear(2);
                                    t.getInventory().clear(3);
                                    t.getInventory().clear(4);

                                    t.sendMessage(MarioMain.getPrefix() + "Gute Items wurden dir entfernt!");
                                    player.sendMessage(MarioMain.getPrefix() + "Die Items wurden " + t.getName() + " entfernt!");
                                }
                                else if (args[0].equalsIgnoreCase("add")) {
                                    t.getInventory().clear(36);
                                    t.getInventory().clear(37);
                                    t.getInventory().clear(38);
                                    t.getInventory().clear(39);

                                    t.getInventory().clear(0);
                                    t.getInventory().clear(1);
                                    t.getInventory().clear(2);
                                    t.getInventory().clear(3);
                                    t.getInventory().clear(4);

                                    t.getInventory().setItem(36, Item.get(ItemID.NETHERITE_HELMET));
                                    t.getInventory().setItem(37, Item.get(ItemID.NETHERITE_CHESTPLATE));
                                    t.getInventory().setItem(38, Item.get(ItemID.NETHERITE_LEGGINGS));
                                    t.getInventory().setItem(39, Item.get(ItemID.NETHERITE_BOOTS));

                                    t.getInventory().setItem(0, Item.get(ItemID.NETHERITE_SWORD));
                                    t.getInventory().setItem(1, Item.get(ItemID.BOW));
                                    t.getInventory().setItem(2, Item.get(ItemID.NETHERITE_PICKAXE));
                                    t.getInventory().setItem(3, Item.get(ItemID.ARROW));
                                    t.getInventory().setItem(4, Item.get(BlockID.OBSIDIAN));

                                    for (int i = 1; i <= 63; i++) {
                                        t.getInventory().addItem(Item.get(ItemID.ARROW), Item.get(BlockID.OBSIDIAN));
                                    }

                                    t.sendMessage(MarioMain.getPrefix() + "Gute Items wurden dir hinzugefügt!");
                                    player.sendMessage(MarioMain.getPrefix() + "Die Items wurden " + t.getName() + " hinzugefügt!");
                                }
                                else {
                                    player.sendMessage(MarioMain.getPrefix() + "/giveitems <remove|add> <Spieler>");
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
                        player.sendMessage(MarioMain.getPrefix() + "/giveitems <add|remove> [Spieler]!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MarioMain.getPrefix() + "/giveitems <add|remove> [Spieler]!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            assert false;
            sender.sendMessage(MarioMain.getPrefix() + "In Arbeit");
        }
        return false;
    }
}
