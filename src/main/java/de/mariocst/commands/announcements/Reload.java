package de.mariocst.commands.announcements;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class Reload extends Command {
    public Reload() {
        super("announcereload", "Schreibt 5mal eine Nachricht, dass der Server reloaded wird!", "announcereload", new String[]{"arl"});
        this.setPermission("mario.annnouncereload");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ",MarioMain.getPrefix() + "§6§lSERVER RELOAD! BITTE KEINE BEFEHLE EINGEBEN!");

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.announcereload") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
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
