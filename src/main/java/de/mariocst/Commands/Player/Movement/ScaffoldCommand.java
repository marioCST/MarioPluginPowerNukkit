package de.mariocst.Commands.Player.Movement;

import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ScaffoldCommand extends Command {
    private MarioMain plugin;

    public ScaffoldCommand(MarioMain plugin) {
        super("scaffold", "Platziere Blöcke unter dir, wenn du die Scaffold Wolle \"ausgerüstet\" hast!", "scaffold");
        this.setPermission("mario.scaffold");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.scaffold") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 1) {
                        switch (args[0].toLowerCase()) {
                            case "giveblock" -> {
                                Item wool = Item.get(BlockID.WOOL);
                                wool.setCustomName("§fScaffold Wool");
                                player.getInventory().addItem(wool);
                                player.sendMessage(MarioMain.getPrefix() + "Du hast die Scaffold Wolle bekommen!");
                            }
                            case "removeblock" -> {
                                for (int i = 0; i <= 35; i++) {
                                    if (player.getInventory().getItem(i).getCustomName().equalsIgnoreCase("§fScaffold Wool")) {
                                        player.getInventory().clear(i);
                                    }
                                }

                                player.sendMessage(MarioMain.getPrefix() + "Dir wurde die Scaffold Wolle entfernt!");
                            }
                            default -> {
                                player.sendMessage(MarioMain.getPrefix() + "/scaffold giveblock|removeblock");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/scaffold giveblock|removeblock");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(MarioMain.getPrefix() + "/scaffold giveblock|removeblock");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            sender.sendMessage("Bitte führe den Command InGame aus!");
        }

        return false;
    }
}
