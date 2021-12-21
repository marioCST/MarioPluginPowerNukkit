package de.mariocst.commands.others;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.config.MasterConfig;
import de.mariocst.MarioMain;

public class DiscordCommand extends Command {
    public DiscordCommand() {
        super("discord", "Zeigt den definierten Discord Link an!", "discord", new String[]{"dc"});
        this.setPermission("mario.discord");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.discord") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                player.sendMessage(MarioMain.getPrefix() + "Unser Discord: " + MasterConfig.getMasterConfig().getLink());
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            sender.sendMessage("Unser Discord: " + MasterConfig.getMasterConfig().getLink());
        }
        return false;
    }
}
