package de.mariocst.Commands.Inventory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class IDCommand extends Command {
    private MarioMain plugin;

    public IDCommand(MarioMain plugin) {
        super("id", "Zeigt dir die ID des Items in deiner Hand!", "id");
        this.setPermission("mario.id");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.id") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                player.sendMessage(MarioMain.getPrefix() + "ID: " + player.getInventory().getItemInHand().getId() + ":" + player.getInventory().getItemInHand().getDamage());
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            sender.sendMessage("Bitte führe den Command InGame aus!");
        }

        return false;
    }
}
