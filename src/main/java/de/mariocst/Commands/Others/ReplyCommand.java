package de.mariocst.Commands.Others;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ReplyCommand extends Command {
    private MarioMain plugin;

    public ReplyCommand(MarioMain plugin) {
        super("reply", "Antworte einem mit /msg geschriebem Spieler.", "reply", new String[]{"r"});
        this.setPermission("mario.reply");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.reply") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length < 1) {
                    sender.sendMessage(MarioMain.getPrefix() + "Ungültige Parameter Länge!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    return false;
                }
                else {
                    if (!MarioMain.getInstance().getLastMessagedPlayers().containsKey(sender.getName())) {
                        sender.sendMessage(MarioMain.getPrefix() + "Du hast niemandem eine MSG geschrieben!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        return false;
                    }
                    else {
                        String playerName = MarioMain.getInstance().getLastMessagedPlayers().get(sender.getName());
                        CommandSender target = MarioMain.getInstance().getServer().getPlayer(playerName);

                        if (target == null) {
                            if (playerName.equalsIgnoreCase("CONSOLE")) {
                                target = MarioMain.getInstance().getServer().getConsoleSender();
                            } else {
                                sender.sendMessage(MarioMain.getPrefix() + "Spieler " + playerName + " nicht gefunden!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                MarioMain.getInstance().getLastMessagedPlayers().remove(sender.getName());
                                return false;
                            }
                        }
                        else {
                            StringBuilder builder = new StringBuilder();
                            for (int i = 0; i < args.length; i++) {
                                builder.append(args[i]).append(" ");
                            }

                            sender.sendMessage(MarioMain.getPrefix() + "[Du -> " + target.getName() + "] " + builder.toString());
                            target.sendMessage(MarioMain.getPrefix() + "[" + sender.getName() + " -> Dir] " + builder.toString());

                            MarioMain.getInstance().getLastMessagedPlayers().put(sender.getName(), target.getName());
                            MarioMain.getInstance().getLastMessagedPlayers().put(target.getName(), sender.getName());
                        }
                    }
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage(MarioMain.getPrefix() + "Bitte führe den Command InGame aus!");
        }
        return false;
    }
}
