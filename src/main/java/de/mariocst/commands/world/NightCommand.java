package de.mariocst.commands.world;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class NightCommand extends Command {
    public NightCommand() {
        super("night", "Macht Nacht", "night");
        this.setPermission("mario.night");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.night") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                player.sendMessage(MarioMain.getPrefix() + "Die Zeit wurde auf Nacht gestellt.");
                player.getLevel().setTime(16000);
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
