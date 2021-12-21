package de.mariocst.commands.player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class HackTrollCommand extends Command {
    public HackTrollCommand() {
        super("hacktroll", "Lasse einen Spieler so wirken, als würde er hacken!", "hacktroll", new String[]{"ht"});
        this.setPermission("mario.hacktroll");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.hacktroll") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                try {
                    if (args.length == 2) {
                        Player t = player.getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));
                        if (t != null) {
                            switch (args[0].toLowerCase()) {
                                case "airstuck", "as" -> {
                                    if (MarioMain.getInstance().airStuckHack.contains(t)) {
                                        MarioMain.getInstance().airStuckHack.remove(t);

                                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " steckt nun nicht mehr in der Luft!");

                                        t.setImmobile(false);
                                    }
                                    else {
                                        MarioMain.getInstance().airStuckHack.add(t);

                                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " steckt nun in der Luft!");

                                        t.teleport(new Location(t.getX(), t.getY() + 2, t.getZ()));

                                        t.setImmobile(true);
                                    }
                                }
                                case "nofall" -> {
                                    if (MarioMain.getInstance().noFallHack.contains(t)) {
                                        MarioMain.getInstance().noFallHack.remove(t);

                                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " kriegt nun wieder Fallschaden!");
                                    } else {
                                        MarioMain.getInstance().noFallHack.add(t);

                                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " kriegt nun keinen Fallschaden mehr!");
                                    }
                                }
                                default -> {
                                    player.sendMessage(MarioMain.getPrefix() + "/hacktroll <airstuck|nofall> <Spieler>!");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                }
                            }
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/hacktroll <airstuck|nofall> <Spieler>!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MarioMain.getPrefix() + "/hacktroll <airstuck|nofall> <Spieler>!");
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
                if (args.length == 2) {
                    Player t = MarioMain.getInstance().getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                    if (t != null) {
                        switch (args[0].toLowerCase()) {
                            case "airstuck", "as" -> {
                                if (MarioMain.getInstance().airStuckHack.contains(t)) {
                                    MarioMain.getInstance().airStuckHack.remove(t);

                                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " steckt nun nicht mehr in der Luft!");

                                    t.setImmobile(false);
                                }
                                else {
                                    MarioMain.getInstance().airStuckHack.add(t);

                                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " steckt nun in der Luft!");

                                    t.teleport(new Location(t.getX(), t.getY() + 2, t.getZ()));

                                    t.setImmobile(true);
                                }
                            }
                            case "nofall" -> {
                                if (MarioMain.getInstance().noFallHack.contains(t)) {
                                    MarioMain.getInstance().noFallHack.remove(t);

                                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " kriegt nun wieder Fallschaden!");
                                } else {
                                    MarioMain.getInstance().noFallHack.add(t);

                                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " kriegt nun keinen Fallschaden mehr!");
                                }
                            }
                            default -> sender.sendMessage(MarioMain.getPrefix() + "/hacktroll <airstuck|nofall> <Spieler>!");
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/hacktroll <airstuck|nofall> <Spieler>!");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(MarioMain.getPrefix() + "/hacktroll <airstuck|nofall> <Spieler>!");
            }
        }

        return false;
    }
}
