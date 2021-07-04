package de.mariocst.Commands.Setter;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class SetLinkCommand extends Command {
    public static String Link = "Kein Link ist definiert";
    private MarioMain plugin;

    public SetLinkCommand(MarioMain plugin) {
        super("setlink", "Setzt den Link für den /discord Command", "setlink", new String[]{"sl"});
        this.setPermission("mario.setlink");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ",  args);

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.setlink") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length > 0) {
                    Link = message;
                    sender.sendMessage(MarioMain.getPrefix() + "Der Discord Link ist nun: " + message);
                } else {
                    sender.sendMessage(MarioMain.getPrefix() + "§cBitte setze einen Link!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            if (args.length > 0) {
                Link = message;
                sender.sendMessage("Der Discord Link ist nun: " + message);
            } else {
                sender.sendMessage("Bitte setze einen Link!");
            }
        }
        return false;
    }
}
