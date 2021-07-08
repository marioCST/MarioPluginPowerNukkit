package de.mariocst.Commands.Announcements;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class Reload extends Command {
    private MarioMain plugin;

    public Reload(MarioMain plugin) {
        super("announcereload", "Schreibt 5mal 'SERVER RELOAD! BITTE KEINE BEFEHLE EINGEBEN!'", "announcereload", new String[]{"arl"});
        this.setPermission("mario.annnouncereload");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        int schleife1 = 0;
        int schleife2 = 0;

        String message = String.join(" ",MarioMain.getPrefix() + "§6§lSERVER RELOAD! BITTE KEINE BEFEHLE EINGEBEN!");
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.announcereload") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                while (schleife1 < 5) {
                    schleife1++;

                    MarioMain.getInstance().getServer().broadcastMessage(message);
                    MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
            while (schleife2 < 5) {
                schleife2++;

                MarioMain.getInstance().getServer().broadcastMessage(message);
                MarioMain.getInstance().getServer().broadcastMessage(MarioMain.getPrefix());
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
