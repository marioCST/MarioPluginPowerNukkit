package de.mariocst.Commands.Report;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class MarioReportCommand extends Command {
    private MarioMain plugin;

    public MarioReportCommand(MarioMain plugin) {
        super("marioreport", "Reporte einen Spieler!", "marioreport", new String[]{"mr"});
        this.setPermission("mario.marioreport");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            try {
                if (args.length >= 2) {
                    try {
                        Player t = MarioMain.getInstance().getServer().getPlayer(args[0]);

                        if (t.getName() != null) {
                            String msg = "";
                            for(int i = 1; i < args.length; i++) {
                                msg = msg + args[i] + " ";
                            }

                            if (MarioMain.reportPlayer.containsKey(t.getName())) {
                                sender.sendMessage(MarioMain.getPrefix() + "Bitte wiederhole den Report nicht!");
                                return true;
                            }

                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde für " + msg + " reported!");

                            for (Player staff : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                                if (staff.hasPermission("mario.staff")) {
                                    staff.sendMessage(MarioMain.getPrefix() + "Der Spieler " + p.getName() + " hat den Spieler " + t.getName() + " für " + msg + " reported!");
                                }
                            }
                        }
                        else {
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler existiert nicht!");
                            p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler existiert nicht!");
                        p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                else if (args.length == 1) {
                    try {
                        Player t = MarioMain.getInstance().getServer().getPlayer(args[0]);

                        if (t.getName() != null) {
                            if (MarioMain.reportPlayer.containsKey(t.getName())) {
                                sender.sendMessage(MarioMain.getPrefix() + "Bitte wiederhole den Report nicht!");
                                return true;
                            }

                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde reported!");

                            for (Player staff : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                                if (staff.hasPermission("mario.staff")) {
                                    staff.sendMessage(MarioMain.getPrefix() + "Der Spieler " + p.getName() + " hat den Spieler " + t.getName() + " reported!");
                                }
                            }
                        }
                        else {
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler existiert nicht!");
                            p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler existiert nicht!");
                        p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "Ungültige Parameter Länge!");
                    p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage(MarioMain.getPrefix() + "Ungültige Parameter Länge!");
                p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage(MarioMain.getPrefix() + "Ban den Spieler doch selber!");
        }
        return false;
    }

}
