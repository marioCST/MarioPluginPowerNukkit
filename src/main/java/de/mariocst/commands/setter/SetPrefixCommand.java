package de.mariocst.commands.setter;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.config.MasterConfig;
import de.mariocst.MarioMain;

public class SetPrefixCommand extends Command {
    public SetPrefixCommand() {
        super("setprefix", "Setzt den Prefix für das Plugin. Nutze & statt Paragraph", "setprefix", new String[]{"sp"});
        this.setPermission("mario.setprefix");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ",  args);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.setprefix") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                if (args.length > 0) {
                    player.sendMessage(MarioMain.getPrefix() + "Der Prefix ist nun: " + message);
                    MasterConfig.getMasterConfig().setPrefix(message.replaceAll("&", "§"));
                    MarioMain.setPrefix(message.replaceAll("&", "§"));
                    MasterConfig.getMasterConfig().save();
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "§cBitte setze einen Prefix!");
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
                sender.sendMessage("Der Prefix ist nun: " + message);
                MasterConfig.getMasterConfig().setPrefix(message.replaceAll("&", "§"));
                MarioMain.setPrefix(message.replaceAll("&", "§"));
                MasterConfig.getMasterConfig().save();
            }
            else {
                sender.sendMessage("Bitte setze einen Prefix!");
            }
        }
        return false;
    }
}
