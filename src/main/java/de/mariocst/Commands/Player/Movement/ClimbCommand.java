package de.mariocst.Commands.Player.Movement;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ClimbCommand extends Command {
    private MarioMain plugin;

    public ClimbCommand(MarioMain plugin) {
        super("climb", "Klettere überall, auch in der Luft", "climb");
        this.setPermission("mario.climb");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.climb") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (player.canClimbWalls()) {
                    player.setCanClimbWalls(false);
                    player.sendMessage(MarioMain.getPrefix() + "Du kannst nun nicht mehr überall hochklettern!");
                }
                else {
                    player.setCanClimbWalls(true);
                    player.sendMessage(MarioMain.getPrefix() + "Du kannst nun überall hochklettern!");
                }
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            sender.sendMessage("Bitte führe den Command InGame aus!");
        }

        return false;
    }
}
