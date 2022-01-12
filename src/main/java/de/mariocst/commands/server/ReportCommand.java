package de.mariocst.commands.server;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ReportCommand extends Command {
    public ReportCommand() {
        super("report", "Reporte einen Spieler!", "report");
        this.setPermission("mario.report");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            try {
                if (args.length >= 2) {
                    try {
                        Player t = MarioMain.getInstance().getServer().getPlayer(args[0]);

                        if (t != null) {
                            StringBuilder msg = new StringBuilder();
                            for(int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde für " + msg + " reported!");

                            for (Player staff : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                                if (staff.hasPermission("mario.staff")) {
                                    staff.sendMessage(MarioMain.getPrefix() + "Der Spieler " + player.getName() + " hat den Spieler " + t.getName() + " für " + msg + " reported!");
                                }
                            }
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    catch (NullPointerException e) {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                else if (args.length == 1) {
                    try {
                        Player t = MarioMain.getInstance().getServer().getPlayer(args[0]);

                        if (t != null) {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde reported!");

                            for (Player staff : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                                if (staff.hasPermission("mario.staff")) {
                                    staff.sendMessage(MarioMain.getPrefix() + "Der Spieler " + player.getName() + " hat den Spieler " + t.getName() + " reported!");
                                }
                            }
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    catch (NullPointerException e) {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "/report <Spieler> [Grund]");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(MarioMain.getPrefix() + "/report <Spieler> [Grund]");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            sender.sendMessage(MarioMain.getPrefix() + "Ban den Spieler doch selber!");
        }
        return false;
    }

}
