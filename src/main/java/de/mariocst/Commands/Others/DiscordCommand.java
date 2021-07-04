package de.mariocst.Commands.Others;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.Commands.Setter.SetLinkCommand;
import de.mariocst.MarioMain;

public class DiscordCommand extends Command {
    private MarioMain plugin;

    public DiscordCommand(MarioMain plugin) {
        super("discord", "Zeigt den definierten Discord Link an! (MUSS IMMER MIT /setlink NACH JEDEM RESTART/RELOAD/STOP NEU DEFINIERT WERDEN!!!!)", "discord", new String[]{"dc"});
        this.setPermission("mario.discord");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.discord") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                sender.sendMessage(MarioMain.getPrefix() + "Unser Discord: " + SetLinkCommand.Link);
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Unser Discord: " + SetLinkCommand.Link);
        }

        return false;
    }
}
