package de.mariocst.commands.others;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

import java.util.Date;

public class DateCommand extends Command {
    public DateCommand() {
        super("date", "Zeigt das Datum an", "date");
        this.setPermission("mario.date");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        Date date = new Date();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.date") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                player.sendMessage(MarioMain.getPrefix() + "Es ist gerade §e" + date);
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            sender.sendMessage("Es ist gerade §e" + date);
        }
        return false;
    }
}
