package de.mariocst.Commands.Inventory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ClearInventoryCommand extends Command {
    private MarioMain plugin;

    public ClearInventoryCommand(MarioMain plugin) {
        super("clear", "Cleart dein Inventar", "clear", new String[]{"clearinventory", "ci", "cleari"});
        this.setPermission("mario.clear");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.clear") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                sender.sendMessage(MarioMain.PREFIX + "Dein Inventar wurde geleert!");
                player.getInventory().clearAll();
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Bitte führe den Befehl InGame aus!");
        }
        return false;
    }

}
