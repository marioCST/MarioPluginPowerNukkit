package de.mariocst.Commands.Setter;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.AntiCheat.Config.MasterConfig;
import de.mariocst.MarioMain;

public class SetPrefixCommand extends Command {
    private MarioMain plugin;

    public SetPrefixCommand(MarioMain plugin) {
        super("setprefix", "Setzt den Prefix für das Plugin. Nutze & statt Paragraph", "setprefix", new String[]{"sp"});
        this.setPermission("mario.setprefix");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ",  args);

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.setprefix") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length > 0) {
                    sender.sendMessage(MarioMain.getPrefix() + "Der Prefix ist nun: " + message);
                    MasterConfig.getMasterConfig().setPrefix(message.replaceAll("&", "§"));
                    MarioMain.setPrefix(message.replaceAll("&", "§"));
                    MasterConfig.getMasterConfig().save();
                } else {
                    sender.sendMessage(MarioMain.getPrefix() + "§cBitte setze einen Prefix!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            if (args.length > 0) {
                sender.sendMessage("Der Prefix ist nun: " + message);
                MasterConfig.getMasterConfig().setPrefix(message.replaceAll("&", "§"));
                MarioMain.setPrefix(message.replaceAll("&", "§"));
                MasterConfig.getMasterConfig().save();
            } else {
                sender.sendMessage("Bitte setze einen Prefix!");
            }
        }
        return false;
    }
}
