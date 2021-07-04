package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class RealnameCommand extends Command {
    private MarioMain plugin;

    public RealnameCommand(MarioMain plugin) {
        super("realname", "Zeigt den echten Namen eines Spielers an", "realname");
        this.setPermission("mario.realname");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.realname") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length == 1) {
                    for (Player p : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                        if (p.getDisplayName().equals(args[0])) {
                            if (!p.getDisplayName().equals(player.getDisplayName())) {
                                if (!p.getDisplayName().equals(p.getName())) {
                                    player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " heißt in Wirklichkeit " + p.getName() + "!");
                                }
                                else {
                                    player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + p.getName() + " ist NICHT genickt!");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                }
                            }
                            else {
                                player.sendMessage(MarioMain.getPrefix() + "Das bist du.");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                } else if (args.length == 0) {
                    sender.sendMessage(MarioMain.getPrefix() + "Bitte gib den Nickname an.");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                } else {
                    sender.sendMessage(MarioMain.getPrefix() + "Bitte gib einen gültigen Nickname an.");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            if (args.length == 1) {
                for (Player p : MarioMain.getInstance().getServer().getOnlinePlayers().values()) {
                    if (p.getDisplayName().equals(args[0])) {
                        if (!p.getDisplayName().equals(p.getName())) {
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " heißt in Wirklichkeit " + p.getName() + "!");
                        }
                        else {
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + p.getName() + " ist NICHT genickt!");
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                    }
                }
            } else if (args.length == 0) {
                sender.sendMessage(MarioMain.getPrefix() + "Bitte gib den Nickname an.");
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Bitte gib einen gültigen Nickname an.");
            }
        }
        return false;
    }
}
