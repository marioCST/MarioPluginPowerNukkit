package de.mariocst.commands.player.movement;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class FreezeCommand extends Command {
    public FreezeCommand() {
        super("freeze", "Lässt dich einfrieren", "freeze", new String[]{"fr"});
        this.setPermission("mario.freeze");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.freeze") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                try {
                    if (args.length == 0) {
                        if (player.isImmobile()) {
                            MarioMain.getInstance().freezed.remove(player);
                            player.setImmobile(false);
                            player.sendMessage(MarioMain.getPrefix() + "§aDu kannst dich nun wieder bewegen!");
                        }
                        else {
                            MarioMain.getInstance().freezed.add(player);
                            player.setImmobile(true);
                            player.sendMessage(MarioMain.getPrefix() + "§4Du kannst dich nun nicht mehr bewegen!");
                        }
                    }
                    else if (args.length == 1) {
                        Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t != null) {
                                if (t.isImmobile()) {
                                    MarioMain.getInstance().freezed.remove(t);
                                    t.setImmobile(false);
                                    t.sendMessage(MarioMain.getPrefix() + "§aDu wurdest entfreezed und kannst dich wieder bewegen!");
                                    player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde gefreezed!");
                                }
                                else {
                                    MarioMain.getInstance().freezed.add(t);
                                    t.setImmobile(true);
                                    t.sendMessage(MarioMain.getPrefix() + "§4Du wurdest gefreezed und kannst die nicht mehr bewegen!");
                                    player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde entfreezed!");
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
                        player.sendMessage(MarioMain.getPrefix() + "/freeze [Spieler]!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MarioMain.getPrefix() + "/freeze [Spieler]!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            if (args.length == 1) {
                Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                try {
                    if (t != null) {
                        if (t.isImmobile()) {
                            MarioMain.getInstance().freezed.remove(t);
                            t.setImmobile(false);
                            t.sendMessage(MarioMain.getPrefix() + "§aDu wurdest entfreezed und kannst dich wieder bewegen!");
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde gefreezed!");
                        }
                        else {
                            MarioMain.getInstance().freezed.add(t);
                            t.setImmobile(true);
                            t.sendMessage(MarioMain.getPrefix() + "§4Du wurdest gefreezed und kannst die nicht mehr bewegen!");
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde entfreezed!");
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                    }
                }
                catch (NullPointerException e) {
                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "/freeze <Spieler>!");
            }
        }
        return false;
    }
}
