package de.mariocst.commands.announcements;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class Custom extends Command {
    public Custom() {
        super("broadcast5", "Einfacher Broadcast Command mit 5er Ausgabe", "broadcast5", new String[]{"bc5"});
        this.setPermission("mario.broadcast5");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ", args);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.broadcast5") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                if (args.length > 0) {
                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                    for (int i = 0; i <= 5; i++) {
                        MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix() + message.replaceAll("&", "§"));
                        MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                        try {
                            Thread.sleep(500);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Text ein!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            if (args.length > 0) {
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                for (int i = 0; i <= 5; i++) {
                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix() + message);
                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                sender.sendMessage("Bitte gib einen Text ein!");
            }
        }
        return false;
    }
}
