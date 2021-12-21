package de.mariocst.commands.chat;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ChatClearCommand extends Command {
    public ChatClearCommand() {
        super("chatclear", "Cleart den Chat", "chatclear", new String[]{"cc"});
        this.setPermission("mario.chatclear");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mario.chatclear") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                for (int i = 0; i <= 100; i++) {
                    MarioMain.getInstance().getServer().broadcastMessage("   ");
                }
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix() + player.getDisplayName() + " hat den Chat geleert.");
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            for (int i = 0; i <= 100; i++) {
                MarioMain.getInstance().getServer().broadcastMessage("   ");
            }
            MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix() + "Die Konsole hat den Chat geleert.");
        }
        return false;
    }
}
