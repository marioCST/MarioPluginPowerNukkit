package de.mariocst.Commands.Report;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.AntiCheat.Cheat.AntiCheat;
import de.mariocst.AntiCheat.Cheat.inventory.ReportInvalidItemEnchantmentThread;
import de.mariocst.MarioMain;

public class MarioACReportCommand extends Command {
    private MarioMain plugin;

    public MarioACReportCommand(MarioMain plugin) {
        super("marioacreport", "Reporte einen Spieler für das ''AntiCheat'' (Illegal Items).", "marioacreport", new String[]{"macr"});
        this.setPermission("mario.marioacreport");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            try {
                if (args.length == 2) {
                    try {
                        Player t = MarioMain.getInstance().getServer().getPlayer(args[0]);

                        if (t.getName() != null) {
                            int type = 0;

                            switch (args[1].toLowerCase()) {
                                case "invaliditemenchant":
                                case "iie":
                                    type = 1;
                                    break;
                                default:
                                    sender.sendMessage(MarioMain.PREFIX + "Report types: invaliditemenchant, iie");
                            }

                            AntiCheat.CheatType cheatType = null;

                            switch (type) {
                                case 1:
                                    cheatType = AntiCheat.CheatType.INVALID_ITEM;
                                    break;
                                default:
                                    sender.sendMessage(MarioMain.PREFIX + "Unbekannter Report Typ! Report Types: invaliditemenchant, iie.");
                                    return true;
                            }

                            if (MarioMain.reportPlayer.containsKey(t.getName())) {
                                sender.sendMessage(MarioMain.PREFIX + "Bitte wiederhole den Report nicht!");
                                return true;
                            }

                            MarioMain.reportPlayer.put(t.getName(), cheatType);
                            MarioMain.getInstance().getLogger().warning("Der Spieler " + p.getName() + " hat den Spieler " + t.getName() + " wegen " + cheatType.getTypeName() + " reported!");
                            sender.sendMessage(MarioMain.PREFIX + "Du hast erfolgreich den Spieler " + t.getName() + " für " + cheatType.getTypeName() + " reported!");
                        }
                        else {
                            sender.sendMessage(MarioMain.PREFIX + "Der Spieler existiert nicht!");
                            p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage(MarioMain.PREFIX + "Der Spieler existiert nicht!");
                        p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                else {
                    sender.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                    p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                p.getLevel().addSound(p.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage(MarioMain.PREFIX + "Ban den Spieler doch selber!");
        }
        return false;
    }

    private void addReportThread(Player player, AntiCheat.CheatType type) {
        switch (type.getTypeName()) {
            default:
                MarioMain.reportThread.put(player.getName(), new ReportInvalidItemEnchantmentThread(player));
                break;
        }
    }

}
