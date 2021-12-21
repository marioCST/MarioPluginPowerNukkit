package de.mariocst.commands.announcements;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class Kick extends Command {
    public Kick() {
        super("announcekick", "Schreibt 5mal eine Nachricht, dass alle Spieler gekickt werden!", "announcekick", new String[]{"ak"});
        this.setPermission("mario.annnouncekick");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ", MarioMain.getPrefix() + "§e§lALLE WERDEN GEKICKT! BITTE NACH KURZER ZEIT REJOINEN! NICHT DIREKT!");

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.announcekick") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                for (int i = 0; i <= 5; i++) {
                    MarioMain.getInstance().getServer().broadcastMessage(message);
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
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
            for (int i = 0; i <= 5; i++) {
                MarioMain.getInstance().getServer().broadcastMessage(message);
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
