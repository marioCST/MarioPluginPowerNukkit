package de.mariocst.commands.inventory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class IDCommand extends Command {
    public IDCommand() {
        super("id", "Zeigt dir die ID des Items in deiner Hand!", "id");
        this.setPermission("mario.id");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.id") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                player.sendMessage(MarioMain.getPrefix() + "ID: " + player.getInventory().getItemInHand().getId() + ":" + player.getInventory().getItemInHand().getDamage());
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            sender.sendMessage(MarioMain.getPrefix() + "Bitte führe den Command InGame aus!");
        }
        return false;
    }
}
