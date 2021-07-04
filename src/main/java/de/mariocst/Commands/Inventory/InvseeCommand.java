package de.mariocst.Commands.Inventory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import com.nukkitx.fakeinventories.inventory.ChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.DoubleChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.FakeSlotChangeEvent;
import de.mariocst.MarioMain;

public class InvseeCommand extends Command {
    private MarioMain plugin;

    public InvseeCommand(MarioMain plugin) {
        super("invsee", "Öffnet das Inventar eines Anderen Spielers", "invsee");
        this.setPermission("mario.invsee");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.invsee") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 2) {
                        Player t = player.getLevel().getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t == null) {
                                MarioMain.unknownPlayer(player);
                                return true;
                            }
                            else {
                                if (t.equals(sender)) {
                                    sender.sendMessage(MarioMain.getPrefix() + "Du kannst diesen Command nicht für dein eigenes Inventar nutzen");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                    return true;
                                }
                                else {
                                    DoubleChestFakeInventory inv = new DoubleChestFakeInventory();
                                    ChestFakeInventory echest = new ChestFakeInventory();
                                    inv.addListener(this::onSlotChange);
                                    echest.addListener(this::onSlotChange);

                                    if (args[0].equalsIgnoreCase("echest")) {
                                        echest.setContents(t.getEnderChestInventory().getContents());
                                        echest.setName(t.getName() + "'s Ender Chest");
                                        ((Player) sender).addWindow(echest);
                                        return true;
                                    }
                                    else if (args[0].equalsIgnoreCase("inv")){
                                        inv.setContents(t.getInventory().getContents());
                                        inv.setName(t.getName() + "'s Inventar");
                                        ((Player) sender).addWindow(inv);
                                        return true;
                                    }
                                    else {
                                        sender.sendMessage(MarioMain.getPrefix() + "/invsee <inv|echest> <Spieler>");
                                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                    }
                                }
                            }
                        }
                        catch (NullPointerException e) {
                            sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            return true;
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
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
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
