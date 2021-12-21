package de.mariocst.commands.world;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class DayCommand extends Command {
    public DayCommand() {
        super("day", "Macht Tag", "day");
        this.setPermission("mario.day");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.day") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                player.sendMessage(MarioMain.getPrefix() + "Die Zeit wurde auf Tag gestellt.");
                player.getLevel().setTime(1000);
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            sender.sendMessage("Bitte führe den Befehl InGame aus!");
        }
        return false;
    }
}
