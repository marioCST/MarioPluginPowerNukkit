package de.mariocst.Commands.Chat;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class MuteCommand extends Command {
    private MarioMain plugin;

    public MuteCommand(MarioMain plugin) {
        super("mute", "Mute einen Spieler!", "mute");
        this.setPermission("mario.mute");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.mute") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 1) {
                        Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                        if (t != null) {
                            if (MarioMain.getInstance().muted.contains(t)) {
                                MarioMain.getInstance().muted.remove(t);

                                player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " ist nun nicht mehr gemuted!");
                            }
                            else {
                                MarioMain.getInstance().muted.add(t);

                                player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " ist nun gemuted!");
                            }
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler!");
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/mute <Spieler>");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(MarioMain.getPrefix() + "/mute <Spieler>");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            try {
                if (args.length == 1) {
                    Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                    if (t != null) {
                        if (MarioMain.getInstance().muted.contains(t)) {
                            MarioMain.getInstance().muted.remove(t);

                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " ist nun nicht mehr gemuted!");
                        }
                        else {
                            MarioMain.getInstance().muted.add(t);

                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " ist nun gemuted!");
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler!");
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/mute <Spieler>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage(MarioMain.getPrefix() + "/mute <Spieler>");
            }
        }
        return false;
    }
}
