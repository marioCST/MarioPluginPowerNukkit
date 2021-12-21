package de.mariocst.commands.inventory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import com.nukkitx.fakeinventories.inventory.ChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.DoubleChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.FakeSlotChangeEvent;
import de.mariocst.MarioMain;

public class InvseeCommand extends Command {
    public InvseeCommand() {
        super("invsee", "Öffnet das Inventar eines Anderen Spielers", "invsee");
        this.setPermission("mario.invsee");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.invsee") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                try {
                    if (args.length == 2) {
                        Player t = player.getLevel().getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t == null) {
                                player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                            else {
                                if (t == sender) {
                                    player.sendMessage(MarioMain.getPrefix() + "Du kannst diesen Command nicht für dein eigenes Inventar nutzen");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                }
                                else {
                                    DoubleChestFakeInventory inv = new DoubleChestFakeInventory();
                                    ChestFakeInventory echest = new ChestFakeInventory();
                                    inv.addListener(this::onSlotChange);
                                    echest.addListener(this::onSlotChange);

                                    if (args[0].equalsIgnoreCase("echest")) {
                                        echest.setContents(t.getEnderChestInventory().getContents());
                                        echest.setName(t.getName() + "'s Ender Chest");
                                        player.addWindow(echest);
                                        return true;
                                    }
                                    else if (args[0].equalsIgnoreCase("inv")){
                                        inv.setContents(t.getInventory().getContents());
                                        inv.setName(t.getName() + "'s Inventar");
                                        player.addWindow(inv);
                                        return true;
                                    }
                                    else {
                                        player.sendMessage(MarioMain.getPrefix() + "/invsee <inv|echest> <Spieler>");
                                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                    }
                                }
                            }
                        }
                        catch (NullPointerException e) {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/invsee <inv/echest> <Spieler>");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MarioMain.getPrefix() + "/invsee <inv/echest> <Spieler>");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            sender.sendMessage(MarioMain.getPrefix() + "Bitte führe den Befehl InGame aus!");
        }
        return false;
    }

    private void onSlotChange(FakeSlotChangeEvent e) {
        if (e.getInventory() instanceof DoubleChestFakeInventory) {
            if (e.getInventory().getName().contains("'s Ender Chest") || e.getInventory().getName().contains("'s Inventar")) {
                e.setCancelled(true);
            }
        }
    }
}
