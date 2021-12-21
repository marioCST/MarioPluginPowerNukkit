package de.mariocst.commands.player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class UnnickCommand extends Command {
    public UnnickCommand() {
        super("unnick", "Setzt deinen Nickname zurück", "unnick");
        this.setPermission("mario.unnick");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.unnick") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                player.setDisplayName(player.getName());
                player.setNameTag(player.getName());
                player.sendMessage(MarioMain.getPrefix() + "Dein Nickname wurde zurückgesetzt!");
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            sender.sendMessage("Bitte führe den Command InGame aus!");
        }
        return false;
    }
}
