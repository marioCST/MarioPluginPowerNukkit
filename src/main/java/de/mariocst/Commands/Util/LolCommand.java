package de.mariocst.Commands.Util;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class LolCommand extends Command {
    private MarioMain plugin;

    public LolCommand(MarioMain plugin) {
        super("lol", "LOL", "lol");
        this.setPermission("mario.lol");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.lol") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                String message = String.join(" ",MarioMain.PREFIX + "§a§lLOL");
                MarioMain.getInstance().getServer().broadcastMessage(message);
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            String message = String.join(" ",MarioMain.PREFIX + "§a§lLOL");
            MarioMain.getInstance().getServer().broadcastMessage(message);
        }
        return false;
    }
}
