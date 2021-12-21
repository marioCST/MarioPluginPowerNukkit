package de.mariocst.commands.server;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class TPSCommand extends Command {
    public TPSCommand() {
        super("tps", "Zeigt dir die Ticks per Second im Chat an!", "tps");
        this.setPermission("mario.tps");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.tps") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                player.sendMessage(MarioMain.getPrefix() + "TPS: " + MarioMain.getInstance().getServer().getTicksPerSecond());
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            sender.sendMessage(MarioMain.getPrefix() + "TPS: " + MarioMain.getInstance().getServer().getTicksPerSecond());
        }
        return false;
    }
}
