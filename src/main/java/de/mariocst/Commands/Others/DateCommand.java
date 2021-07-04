package de.mariocst.Commands.Others;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

import java.util.Date;

public class DateCommand extends Command {

    private MarioMain plugin;

    public DateCommand(MarioMain plugin) {
        super("date", "Zeigt das Datum an", "date");
        this.setPermission("mario.date");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        Date date = new Date();

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.date") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                sender.sendMessage(MarioMain.getPrefix() + "Es ist gerade §e" + date.toString());
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Es ist gerade §e" + date.toString());
        }

        return false;
    }
}
