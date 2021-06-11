package de.mariocst.Commands.Player;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class FlyCommand extends Command {

    private MarioMain plugin;

    public FlyCommand(MarioMain plugin) {
        super("fly", "Lässt dich auch im Survival Modus fliegen", "fly", new String[]{"fliegen"});
        this.setPermission("mario.fly");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            boolean hasFly = player.getAdventureSettings().get(AdventureSettings.Type.ALLOW_FLIGHT);

            if (player.hasPermission("mario.fly") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (hasFly) {
                    player.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, false);
                    sender.sendMessage(MarioMain.PREFIX + "§cDu fliegst nun nicht mehr.");
                    player.getAdventureSettings().update();
                    return false;
                } else {
                    player.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                    sender.sendMessage(MarioMain.PREFIX + "§aDu fliegst nun.");
                    player.getAdventureSettings().update();
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Du bist eine Konsole.");
        }
        return false;
    }
}
