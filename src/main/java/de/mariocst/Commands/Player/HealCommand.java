package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class HealCommand extends Command {

    private MarioMain plugin;

    public HealCommand(MarioMain plugin) {
        super("heal", "Heilt und sättigt dich", "heal");
        this.setPermission("mario.heal");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.heal") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                player.setHealth(20F);
                player.getFoodData().setLevel(player.getFoodData().getMaxLevel());
                player.sendMessage(MarioMain.PREFIX + "Du wurdest geheilt und gesättigt!");
            } else {
                player.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Du bist eine Konsole.");
        }

        return false;
    }
}
