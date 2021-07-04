package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class DieCommand extends Command {

    private MarioMain plugin;

    public DieCommand(MarioMain plugin) {
        super("die", "Tötet dich", "die");
        this.setPermission("mario.die");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.die") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                player.setHealth(0F);
                player.sendMessage(MarioMain.getPrefix() + "Du bist gestorben.");
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            sender.sendMessage("Du bist gestorben. Warte... Du bist eine Konsole!");
        }

        return false;
    }
}
