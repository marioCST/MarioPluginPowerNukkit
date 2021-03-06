package de.mariocst.commands.player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class HealCommand extends Command {
    public HealCommand() {
        super("heal", "Heilt  dich", "heal");
        this.setPermission("mario.heal");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.heal") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                if (args.length == 0) {
                    player.setHealth(20F);
                    player.sendMessage(MarioMain.getPrefix() + "Du wurdest geheilt!");
                }
                else if (args.length == 1) {
                    Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                    if (t != null) {
                        t.setHealth(20F);
                        t.sendMessage(MarioMain.getPrefix() + "Du wurdest geheilt!");
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde geheilt!");
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "/heal [Spieler]");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            if (args.length == 1) {
                Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                if (t != null) {
                    t.setHealth(20F);
                    t.getFoodData().setLevel(t.getFoodData().getMaxLevel());
                    t.sendMessage(MarioMain.getPrefix() + "Du wurdest geheilt und ges??ttigt!");
                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde geheilt und ges??ttigt!");
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "/heal [Spieler]");
            }
        }

        return false;
    }
}
