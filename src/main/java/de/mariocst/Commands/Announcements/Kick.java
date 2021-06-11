package de.mariocst.Commands.Announcements;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class Kick extends Command {
    private MarioMain plugin;

    public Kick(MarioMain plugin) {
        super("announcekick", "Screibt 5mal 'ALLE WERDEN GEKICKT! BITTE NACH KURZER ZEIT REJOINEN! NICHT DIREKT!'", "announcekick", new String[]{"ak"});
        this.setPermission("mario.annnouncekick");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        int schleife1 = 0;
        int schleife2 = 0;

        String message = String.join(" ", MarioMain.PREFIX + "§e§lALLE WERDEN GEKICKT! BITTE NACH KURZER ZEIT REJOINEN! NICHT DIREKT!");
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.announcekick") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.PREFIX);
                while (schleife1 < 5) {
                    schleife1++;

                    MarioMain.getInstance().getServer().broadcastMessage(message);
                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.PREFIX);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            MarioMain.getInstance().getServer().broadcastMessage(MarioMain.PREFIX);
            while (schleife2 < 5) {
                schleife2++;

                MarioMain.getInstance().getServer().broadcastMessage(message);
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.PREFIX);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
