package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityPrimedTNT;
import cn.nukkit.item.Item;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class TrollCommand extends Command {
    private MarioMain plugin;

    public TrollCommand(MarioMain plugin) {
        super("troll", "Trolle jemanden!", "troll");
        this.setPermission("mario.troll");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.troll") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 1) {
                        Player t = MarioMain.getInstance().getServer().getPlayer(args[0]);

                        try {
                            if (t != null) {
                                for (int i = 0; i <= 35; i++) {
                                    if (t.getInventory().getItem(i) != null && t.getInventory().getItem(i) != t.getInventory().getHelmet()) {
                                        t.dropItem(t.getInventory().getItem(i));
                                        t.getInventory().clear(i, true);
                                    }
                                }

                                player.sendMessage(MarioMain.PREFIX + "Der Spieler " + t.getName() + " wurde gerfolgreich getrollt!");
                            }
                            else {
                                player.sendMessage(MarioMain.PREFIX + "Dieser Spieler existiert nicht!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                        catch (NullPointerException e) {
                            e.printStackTrace();
                            player.sendMessage(MarioMain.PREFIX + "Dieser Spieler existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            MarioMain.getInstance().log("In Arbeit");
        }
        return false;
    }
}
