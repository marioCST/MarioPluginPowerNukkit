package de.mariocst.Commands.World;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class NightCommand extends Command {
    private MarioMain plugin;

    public NightCommand(MarioMain plugin) {
        super("night", "Macht Nacht", "night");
        this.setPermission("mario.night");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mario.night") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                sender.sendMessage(MarioMain.getPrefix() + "Die Zeit wurde auf Nacht gestellt.");
                player.getLevel().setTime(16000);
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "§cKeine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Bitte führe den Befehl InGame aus!");
        }
        return false;
    }
}
