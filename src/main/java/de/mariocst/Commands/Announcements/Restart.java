package de.mariocst.Commands.Announcements;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class Restart extends Command {
    private MarioMain plugin;

    public Restart(MarioMain plugin) {
        super("announcerestart", "Screibt 5mal 'SERVER RESTART! Bitte in 1 Min. wieder joinen!'", "announcerestart", new String[]{"ars"});
        this.setPermission("mario.annnouncerestart");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        int schleife1 = 0;
        int schleife2 = 0;

        String message = String.join(" ",MarioMain.PREFIX + "§4§lSERVER RESTART! Bitte in 1 Min. wieder joinen!");
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.announcerestart") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
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
