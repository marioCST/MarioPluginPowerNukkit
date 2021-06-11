package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class GMCommand extends Command {
    private MarioMain plugin;

    public GMCommand(MarioMain plugin) {
        super("gm", "Abkürzung für /gamemode.", "gm");
        this.setPermission("mario.gm");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String msg = String.join(" ",  args);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("mario.gm") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length == 1) {
                    if(msg.equals("0") || msg.equalsIgnoreCase("survival")) {
                        player.setGamemode(0);
                        sender.sendMessage(MarioMain.PREFIX + "Dein Gamemode wurde auf Survival gestellt!");
                    } else if (msg.equals("1") || msg.equalsIgnoreCase("creative")) {
                        player.setGamemode(1);
                        sender.sendMessage(MarioMain.PREFIX + "Dein Gamemode wurde auf Creative gestellt!");
                    } else if (msg.equals("2") || msg.equalsIgnoreCase("adventure")) {
                        player.setGamemode(2);
                        sender.sendMessage(MarioMain.PREFIX + "Dein Gamemode wurde auf Adventure gestellt!");
                    } else if (msg.equals("3") || msg.equalsIgnoreCase("spectator")) {
                        player.setGamemode(3);
                        sender.sendMessage(MarioMain.PREFIX + "Dein Gamemode wurde auf Spectator gestellt!");
                    } else {
                        sender.sendMessage(MarioMain.PREFIX + "Bitte gib einen gültigen Gamemode ein!");
                    }
                } else {
                    sender.sendMessage(MarioMain.PREFIX + "Usage: /gm 0 oder 1 oder 2 oder 3 oder survival oder creative oder adventure oder spectator");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Bitte führe den Befehl InGame aus!");
        }
        return false;
    }
}
