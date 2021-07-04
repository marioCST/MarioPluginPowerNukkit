package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class HealCommand extends Command {

    private MarioMain plugin;

    public HealCommand(MarioMain plugin) {
        super("heal", "Heilt  dich", "heal");
        this.setPermission("mario.heal");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.heal") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
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
                        MarioMain.unknownPlayer(player);
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
        } else {
            assert false;
            if (args.length == 1) {
                Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                if (t != null) {
                    t.setHealth(20F);
                    t.getFoodData().setLevel(t.getFoodData().getMaxLevel());
                    t.sendMessage(MarioMain.getPrefix() + "Du wurdest geheilt und gesättigt!");
                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde geheilt und gesättigt!");
                }
                else {
                    MarioMain.unknownPlayer(sender);
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "/heal [Spieler]");
            }
        }

        return false;
    }
}
