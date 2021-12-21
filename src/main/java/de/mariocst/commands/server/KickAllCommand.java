package de.mariocst.commands.server;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class KickAllCommand extends Command {
    public KickAllCommand() {
        super("kickall", "Kickt alle Spieler mit einem bestimmten Grund", "kickall", new String[]{"ka"});
        this.setPermission("mario.kickall");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.kickall") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                try {
                    int count = MarioMain.getInstance().getServer().getOnlinePlayers().size();

                    if (args.length == 0) {

                        if (count == 1) {
                            player.sendMessage(MarioMain.getPrefix() + "Kein Spieler, außer dir, ist Online.");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                        else {
                            for (Player t : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                                if (t != player && (!t.hasPermission("mario.kickall.bypass") || t.isOp() || t.hasPermission("mario.*") || t.hasPermission("*"))) {
                                    t.kick();
                                }

                                if ((t.isOp() || t.hasPermission("mario.kickall.bypass") || t.hasPermission("mario.*") || t.hasPermission("*")) && t != player) {
                                    player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " ist gegen KickAll immun!");
                                }
                            }

                            player.sendMessage(MarioMain.getPrefix() + "Alle Spieler gekickt!");
                        }
                    }
                    else {
                        if (count == 1) {
                            player.sendMessage(MarioMain.getPrefix() + "Kein Spieler, außer dir, ist Online");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                        else {
                            String reason = String.join(" ", args);

                            for (Player t : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                                if (t != player && (!t.hasPermission("mario.kickall.bypass") || t.isOp() || t.hasPermission("mario.*") || t.hasPermission("*"))) {
                                    t.kick(reason);
                                }

                                if ((t.isOp() || t.hasPermission("mario.kickall.bypass") || t.hasPermission("mario.*") || t.hasPermission("*")) && t != player) {
                                    player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " ist gegen KickAll immun!");
                                }
                            }

                            player.sendMessage(MarioMain.getPrefix() + "Alle Spieler mit dem Grund " + reason + " gekickt!");
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MarioMain.getPrefix() + "/kickall [Grund]");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            try {
                int count = MarioMain.getInstance().getServer().getOnlinePlayers().size();

                if (args.length == 0) {

                    if (count == 0) {
                        sender.sendMessage(MarioMain.getPrefix() + "Kein Spieler ist Online lol");
                        return false;
                    }
                    else {
                        for (Player t : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                            t.kick();
                        }

                        sender.sendMessage(MarioMain.getPrefix() + "Alle Spieler gekickt!");
                    }
                }
                else {
                    if (count == 0) {
                        sender.sendMessage(MarioMain.getPrefix() + "Kein Spieler ist Online lol");
                        return false;
                    }
                    else {
                        String reason = String.join(" ", args);
                        for (Player t : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                            t.kick(reason);
                        }

                        sender.sendMessage(MarioMain.getPrefix() + "Alle Spieler mit dem Grund " + reason + " gekickt!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(MarioMain.getPrefix() + "/kickall [Grund]");
            }
        }
        return false;
    }
}
