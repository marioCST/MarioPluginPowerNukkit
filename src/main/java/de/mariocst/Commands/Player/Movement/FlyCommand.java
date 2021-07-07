package de.mariocst.Commands.Player.Movement;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class FlyCommand extends Command {

    private MarioMain plugin;

    public FlyCommand(MarioMain plugin) {
        super("fly", "Lässt dich auch im Survival Modus fliegen", "fly", new String[]{"fliegen"});
        this.setPermission("mario.fly");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mario.fly") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 0) {
                        if (MarioMain.hasFly(player)) {
                            player.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, false);
                            sender.sendMessage(MarioMain.getPrefix() + "§cDu fliegst nun nicht mehr.");
                            player.getAdventureSettings().update();
                            return false;
                        } else {
                            player.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                            sender.sendMessage(MarioMain.getPrefix() + "§aDu fliegst nun.");
                            player.getAdventureSettings().update();
                        }
                    }
                    else if (args.length == 1) {
                        Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                        if (t != null) {
                            if (MarioMain.hasFly(t)) {
                                t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, false);
                                t.sendMessage(MarioMain.getPrefix() + "§cDu fliegst nun nicht mehr.");
                                t.getAdventureSettings().update();
                                player.sendMessage(MarioMain.getPrefix() + "§cDer Spieler " + t.getName() + " fliegt nun nicht mehr.");
                                return false;
                            } else {
                                t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                                t.sendMessage(MarioMain.getPrefix() + "§aDu fliegst nun.");
                                t.getAdventureSettings().update();
                                player.sendMessage(MarioMain.getPrefix() + "§aDer Spieler " + t.getName() + " fliegt nun.");
                            }
                        }
                        else {
                            sender.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "/fly [Spieler]");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    sender.sendMessage(MarioMain.getPrefix() + "/fly [Spieler]");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            try {
                if (args.length == 1) {
                    Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                    if (t != null) {
                        if (t.getLevel().getName().equals("flatmap") || t.getLevel().getName().equals("farmwelt") ||
                                t.getLevel().getName().equals("netherfw") || t.getLevel().getName().equals("endfw") ||
                                (!t.hasPermission("mario.fly.bypass") && !t.hasPermission("mario.*") &&
                                !t.hasPermission("*") && !t.isOp()) && (t.getGamemode() != 1 &&
                                t.getGamemode() != 3)) {
                            if (MarioMain.hasFly(t)) {
                                sender.sendMessage(MarioMain.getPrefix() + "§cDer Spieler " + t.getName() + " darf in der Welt nicht fliegen!");
                            }
                        }
                        else {
                            if (MarioMain.hasFly(t)) {
                                t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, false);
                                t.sendMessage(MarioMain.getPrefix() + "§cDu fliegst nun nicht mehr.");
                                t.getAdventureSettings().update();
                                sender.sendMessage(MarioMain.getPrefix() + "§cDer Spieler " + t.getName() + " fliegt nun nicht mehr.");
                                return false;
                            } else {
                                t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                                t.sendMessage(MarioMain.getPrefix() + "§aDu fliegst nun.");
                                t.getAdventureSettings().update();
                                sender.sendMessage(MarioMain.getPrefix() + "§aDer Spieler " + t.getName() + " fliegt nun.");
                            }
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/fly [Spieler]");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage(MarioMain.getPrefix() + "/fly <Spieler>");
            }
        }
        return false;
    }
}
