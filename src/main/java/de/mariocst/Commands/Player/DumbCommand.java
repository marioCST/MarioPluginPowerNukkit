package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class DumbCommand extends Command {
    private MarioMain plugin;

    public DumbCommand(MarioMain plugin) {
        super("dumb", "Du bist dumm.", "dumb", new String[]{"dumm"});
        this.setPermission("mario.dumb");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.dumb") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                String message = String.join(" ",MarioMain.PREFIX + player.getDisplayName() + " ist dumm.");
                MarioMain.getInstance().getServer().broadcastMessage(message);
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            String message = String.join(" ",MarioMain.PREFIX + "Die Konsole ist dumm.");
            MarioMain.getInstance().getServer().broadcastMessage(message);
        }
        return false;
    }
}
