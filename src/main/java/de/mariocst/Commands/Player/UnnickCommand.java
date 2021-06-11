package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class UnnickCommand extends Command {
    private MarioMain plugin;

    public UnnickCommand(MarioMain plugin) {
        super("unnick", "Setzt deinen Nickname zurück", "unnick");
        this.setPermission("mario.unnick");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mario.unnick") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                player.setDisplayName(player.getName());
                player.setNameTag(player.getName());
                sender.sendMessage(MarioMain.PREFIX + "Dein Nickname wurde zurückgesetzt!");
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Bitte führe den Command InGame aus!");
        }
        return false;
    }
}
