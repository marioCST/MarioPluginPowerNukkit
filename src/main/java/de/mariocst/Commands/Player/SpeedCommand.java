package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import cn.nukkit.potion.Effect;
import de.mariocst.MarioMain;

public class SpeedCommand extends Command {
    private MarioMain plugin;

    public SpeedCommand(MarioMain plugin) {
        super("speed", "Gibt dir Speed.", "speed");
        this.setPermission("mario.speed");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.speed") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("reset")) {
                            player.removeEffect(Effect.SPEED);
                            sender.sendMessage(MarioMain.PREFIX + "Dein Speed wurde zurückgesetzt!");
                        }
                        else {
                            try {
                                int speed = Integer.parseInt(args[0]);

                                if (speed != 0) {
                                    player.addEffect(
                                            Effect.getEffect(Effect.SPEED)
                                                    .setAmplifier(speed)
                                                    .setDuration(Integer.MAX_VALUE)
                                                    .setVisible(false)
                                    );
                                    sender.sendMessage(MarioMain.PREFIX + "Dein Speed ist nun: " + speed);
                                }
                                else {
                                    player.removeEffect(Effect.SPEED);
                                    sender.sendMessage(MarioMain.PREFIX + "Dein Speed wurde zurückgesetzt!");
                                }
                            }
                            catch (NumberFormatException e) {
                                e.printStackTrace();
                                sender.sendMessage(MarioMain.PREFIX + "/speed <reset|value>");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                    }
                    else if (args.length == 2) {
                        Player t = player.getLevel().getServer().getPlayer(args[0]);

                        try {
                            if (null != t.getName()) {
                                if (args[1].equalsIgnoreCase("reset")) {
                                    t.removeEffect(Effect.SPEED);
                                    sender.sendMessage(MarioMain.PREFIX + "Der Speed des Spielers " + t.getName() + " wurde zurückgesetzt!");
                                    t.sendMessage(MarioMain.PREFIX + "Dein Speed wurde zurückgesetzt!");
                                }
                                try {
                                    int speed = Integer.parseInt(args[1]);

                                    if (speed != 0) {
                                        t.addEffect(
                                                Effect.getEffect(Effect.SPEED)
                                                        .setAmplifier(speed)
                                                        .setDuration(Integer.MAX_VALUE)
                                                        .setVisible(false)
                                        );
                                        sender.sendMessage(MarioMain.PREFIX + "Der Speed des Spielers " + t.getName() + " ist nun: " + speed);
                                        t.sendMessage(MarioMain.PREFIX + "Dein Speed ist nun: " + speed);
                                    }
                                    else {
                                        t.removeEffect(Effect.SPEED);
                                        sender.sendMessage(MarioMain.PREFIX + "Der Speed des Spielers " + t.getName() + " wurde zurückgesetzt!");
                                        t.sendMessage(MarioMain.PREFIX + "Dein Speed wurde zurückgesetzt!");
                                    }
                                }
                                catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    sender.sendMessage(MarioMain.PREFIX + "/speed <Spieler> <reset|value>");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                }
                            }
                            else {
                                sender.sendMessage(MarioMain.PREFIX + "Der Spieler existiert nicht!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                        catch (NullPointerException e) {
                            e.printStackTrace();
                            sender.sendMessage(MarioMain.PREFIX + "Der Spieler existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    sender.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage(MarioMain.PREFIX + "Dieser Befehl kann nur InGame ausgeführt werden!");
        }
        return false;
    }
}
