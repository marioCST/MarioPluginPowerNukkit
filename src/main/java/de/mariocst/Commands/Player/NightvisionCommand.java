package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import cn.nukkit.potion.Effect;
import de.mariocst.MarioMain;

public class NightvisionCommand extends Command {
    private MarioMain plugin;

    public NightvisionCommand(MarioMain plugin) {
        super("nightvision", "Gibt dir Nightvision.", "nightvision", new String[]{"nv"});
        this.setPermission("mario.nightvision");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.nightvision") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 0) {
                        if (player.hasEffect(Effect.NIGHT_VISION)) {
                            player.removeEffect(Effect.NIGHT_VISION);
                            sender.sendMessage(MarioMain.getPrefix() + "Deine Nightvision wurde entfernt!");
                        }
                        else {
                            player.addEffect(
                                    Effect.getEffect(Effect.NIGHT_VISION)
                                            .setAmplifier(255)
                                            .setDuration(Integer.MAX_VALUE)
                                            .setVisible(false)
                            );
                            sender.sendMessage(MarioMain.getPrefix() + "Du hast nun Nightvision!");
                        }
                    }
                    else if (args.length == 1) {
                        Player t = player.getLevel().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t != null) {
                                if (t.hasEffect(Effect.NIGHT_VISION)) {
                                    t.removeEffect(Effect.NIGHT_VISION);
                                    sender.sendMessage(MarioMain.getPrefix() + "Nightvision wurde dem Spieler " + t.getName() + " entfernt!");
                                    t.sendMessage(MarioMain.getPrefix() + "Deine Nightvision wurde entfernt!");
                                }
                                else {
                                    t.addEffect(
                                            Effect.getEffect(Effect.NIGHT_VISION)
                                                    .setAmplifier(255)
                                                    .setDuration(Integer.MAX_VALUE)
                                                    .setVisible(false)
                                    );
                                    sender.sendMessage(MarioMain.getPrefix() + "Nightvision wurde dem Spieler " + t.getName() + " hinzugefügt!");
                                    t.sendMessage(MarioMain.getPrefix() + "Du hast nun Nightvision!");
                                }
                            }
                            else {
                                MarioMain.unknownPlayer(player);
                            }
                        }
                        catch (NullPointerException e) {
                            e.printStackTrace();
                            MarioMain.unknownPlayer(player);
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "/nightvision [Spieler]!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    sender.sendMessage(MarioMain.getPrefix() + "/nightvision [Spieler]!");
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

                    try {
                        if (t != null) {
                            if (t.hasEffect(Effect.NIGHT_VISION)) {
                                t.removeEffect(Effect.NIGHT_VISION);
                                sender.sendMessage(MarioMain.getPrefix() + "Nightvision wurde dem Spieler " + t.getName() + " entfernt!");
                                t.sendMessage(MarioMain.getPrefix() + "Deine Nightvision wurde entfernt!");
                            }
                            else {
                                t.addEffect(
                                        Effect.getEffect(Effect.NIGHT_VISION)
                                                .setAmplifier(255)
                                                .setDuration(Integer.MAX_VALUE)
                                                .setVisible(false)
                                );
                                sender.sendMessage(MarioMain.getPrefix() + "Nightvision wurde dem Spieler " + t.getName() + " hinzugefügt!");
                                t.sendMessage(MarioMain.getPrefix() + "Du hast nun Nightvision!");
                            }
                        }
                        else {
                            MarioMain.unknownPlayer(sender);
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        MarioMain.unknownPlayer(sender);
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/nightvision <Spieler>!");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage(MarioMain.getPrefix() + "/nightvision <Spieler>!");
            }
        }
        return false;
    }
}
