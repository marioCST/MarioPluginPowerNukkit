package de.mariocst.Commands.Chat;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ChatClearCommand extends Command {
    private MarioMain plugin;

    public ChatClearCommand(MarioMain plugin) {
        super("chatclear", "Cleart den Chat", "chatclear", new String[]{"cc"});
        this.setPermission("mario.chatclear");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        int schleife1 = 0;
        int schleife2 = 0;

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.chatclear") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                while (schleife1 < 100) {
                    schleife1++;
                    MarioMain.getInstance().getServer().broadcastMessage("   ");
                }
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.PREFIX + player.getDisplayName() + " hat den Chat geleert.");
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            while (schleife2 < 100) {
                schleife2++;
                MarioMain.getInstance().getServer().broadcastMessage("   ");
            }
            MarioMain.getInstance().getServer().broadcastMessage(MarioMain.PREFIX + "Die Konsole hat den Chat geleert.");
        }
        return false;
    }
}
