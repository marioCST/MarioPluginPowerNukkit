package de.mariocst.commands.player.movement;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class FlyCommand extends Command {
    public FlyCommand() {
        super("fly", "Lässt dich auch im Survival Modus fliegen", "fly", new String[]{"fliegen"});
        this.setPermission("mario.fly");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.fly") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                try {
                    if (args.length == 0) {
                        if (MarioMain.hasFly(player)) {
                            player.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, false);
                            player.sendMessage(MarioMain.getPrefix() + "§cDu fliegst nun nicht mehr.");
                            player.getAdventureSettings().update();
                        }
                        else {
                            player.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                            player.sendMessage(MarioMain.getPrefix() + "§aDu fliegst nun.");
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
                            }
                            else {
                                t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                                t.sendMessage(MarioMain.getPrefix() + "§aDu fliegst nun.");
                                t.getAdventureSettings().update();
                                player.sendMessage(MarioMain.getPrefix() + "§aDer Spieler " + t.getName() + " fliegt nun.");
                            }
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/fly [Spieler]");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MarioMain.getPrefix() + "/fly [Spieler]");
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
                if (args.length == 1) {
                    Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                    if (t != null) {
                        if (MarioMain.hasFly(t)) {
                            t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, false);
                            t.sendMessage(MarioMain.getPrefix() + "§cDu fliegst nun nicht mehr.");
                            t.getAdventureSettings().update();
                            sender.sendMessage(MarioMain.getPrefix() + "§cDer Spieler " + t.getName() + " fliegt nun nicht mehr.");
                        }
                        else {
                            t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                            t.sendMessage(MarioMain.getPrefix() + "§aDu fliegst nun.");
                            t.getAdventureSettings().update();
                            sender.sendMessage(MarioMain.getPrefix() + "§aDer Spieler " + t.getName() + " fliegt nun.");
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/fly [Spieler]");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(MarioMain.getPrefix() + "/fly <Spieler>");
            }
        }
        return false;
    }
}
