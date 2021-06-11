package de.mariocst.Commands.Chat;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class BroadcastCommand extends Command {
    private MarioMain plugin;

    public BroadcastCommand(MarioMain plugin) {
        super("broadcast", "Einfacher Broadcast Command", "broadcast", new String[]{"bc"});
        this.setPermission("mario.broadcast");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ",  args);

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.broadcast") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length > 0) {
                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.PREFIX + message);
                } else {
                    sender.sendMessage(MarioMain.PREFIX + "Bitte gib einen Text ein!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            if (args.length > 0) {
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.PREFIX + message);
            } else {
                sender.sendMessage("Bitte gib einen Text ein!");
            }
        }
        return false;
    }
}
