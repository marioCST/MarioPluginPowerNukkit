package de.mariocst.commands.announcements;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class Stop extends Command {
    public Stop() {
        super("announcestop", "Schreibt 5mal eine Nachricht, dass der Server gestoppt wird!", "announcestop", new String[]{"acs"});
        this.setPermission("mario.annnouncestop");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String message = String.join(" ",MarioMain.getPrefix() + "§4§lSERVER STOPP! Danke für's spielen!");

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.announcestop") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
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
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
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
