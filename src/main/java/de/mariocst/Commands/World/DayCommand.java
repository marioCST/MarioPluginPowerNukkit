package de.mariocst.Commands.World;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class DayCommand extends Command {
    private MarioMain plugin;

    public DayCommand(MarioMain plugin) {
        super("day", "Macht Tag", "day");
        this.setPermission("mario.day");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mario.day") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                sender.sendMessage(MarioMain.PREFIX + "Die Zeit wurde auf Tag gestellt.");
                player.getLevel().setTime(0);
            } else {
                sender.sendMessage(MarioMain.PREFIX + "§cKeine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Bitte führe den Befehl InGame aus!");
        }
        return false;
    }
}
