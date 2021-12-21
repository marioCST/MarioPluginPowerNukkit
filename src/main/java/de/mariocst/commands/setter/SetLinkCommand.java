package de.mariocst.commands.setter;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.config.MasterConfig;
import de.mariocst.MarioMain;

public class SetLinkCommand extends Command {
    public SetLinkCommand() {
        super("setlink", "Setzt den Link für den /discord Command", "setlink", new String[]{"sl"});
        this.setPermission("mario.setlink");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ",  args);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.setlink") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                if (args.length > 0) {
                    player.sendMessage(MarioMain.getPrefix() + "Der Discord Link ist nun: " + message);
                    MasterConfig.getMasterConfig().setLink(message);
                    MasterConfig.getMasterConfig().save();
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "§cBitte setze einen Link!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            if (args.length > 0) {
                sender.sendMessage("Der Discord Link ist nun: " + message);
                MasterConfig.getMasterConfig().setLink(message);
                MasterConfig.getMasterConfig().save();
            }
            else {
                sender.sendMessage("Bitte setze einen Link!");
            }
        }
        return false;
    }
}
