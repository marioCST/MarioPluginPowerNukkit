package de.mariocst.commands.chat;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class BroadcastCommand extends Command {
    public BroadcastCommand() {
        super("broadcast", "Einfacher Broadcast Command", "broadcast", new String[]{"bc"});
        this.setPermission("mario.broadcast");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ",  args);

        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            if (player.hasPermission("mario.broadcast") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                if (args.length > 0) {
                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix() + message.replaceAll("&", "§"));
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Text ein!");
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
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix() + message);
            }
            else {
                sender.sendMessage("Bitte gib einen Text ein!");
            }
        }
        return false;
    }
}
