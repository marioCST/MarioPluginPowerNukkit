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
                if (sender.getName().equals("marioCST")) {
                    if (args.length == 1) {
                        String target = args[0];
                        Player targetPlayer = player.getLevel().getServer().getPlayer(target);

                        if (null != targetPlayer) {
                            if (targetPlayer.getName().equals(args[0])) {
                                sender.sendMessage(MarioMain.PREFIX + "Dieser Spieler ist NICHT genickt!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            } else {
                                sender.sendMessage(MarioMain.PREFIX + "Der Echte Name von " + args[0] + " ist: " + targetPlayer.getName());
                            }
                        } else {
                            sender.sendMessage(MarioMain.PREFIX + "java.lang.NullPointerException");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    } else if (args.length == 0) {
                        sender.sendMessage(MarioMain.PREFIX + "Bitte gib den Nickname an.");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    } else {
                        sender.sendMessage(MarioMain.PREFIX + "Bitte gib einen gültigen Nickname an.");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                } else {
                    sender.sendMessage("Befehl ist in Development. Sonst leider nur NullPointerExceptions");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Momentan noch keine Konsole, weil es Fehler gibt");
        }
        return false;
    }
}
