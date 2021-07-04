package de.mariocst.Commands.Announcements;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class Custom extends Command {
    private MarioMain plugin;

    public Custom(MarioMain plugin) {
        super("broadcast5", "Einfacher Broadcast Command mit 5er Ausgabe", "broadcast5", new String[]{"bc5"});
        this.setPermission("mario.broadcast5");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        int schleife1 = 0;
        int schleife2 = 0;

        String message = String.join(" ", args);
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.broadcast5") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length > 0) {
                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                    while (schleife1 < 5) {
                        schleife1++;

                        MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix() + message.replaceAll("&", "§"));
                        MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    sender.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Text ein!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            if (args.length > 0) {
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                while (schleife2 < 5) {
                    schleife2++;

                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix() + message);
                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                sender.sendMessage("Bitte gib einen Text ein!");
            }
        }
        return false;
    }
}
