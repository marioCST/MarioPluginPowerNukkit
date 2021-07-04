package de.mariocst.Commands.Send;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class SendMessageCommand extends Command {
    private MarioMain plugin;

    public SendMessageCommand(MarioMain plugin) {
        super("sendmessage", "Schreibe einem bestimmten Spieler eine Nachricht!", "sendmessage", new String[]{"sm"});
        this.setPermission("mario.sendmessage");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.sendmessage") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length >= 2) {
                        Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t != null) {
                                String msg = "";
                                for(int i = 1; i < args.length; i++) {
                                    msg = msg + args[i] + " ";
                                }

                                t.sendMessage(msg);
                            }
                        }
                        catch (NullPointerException e) {
                            e.printStackTrace();
                            player.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/sm <Spieler> <Nachricht>");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(MarioMain.getPrefix() + "/sm <Spieler> <Nachricht>");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            try {
                if (args.length >= 2) {
                    Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
                            String msg = "";
                            for(int i = 1; i < args.length; i++) {
                                msg = msg + args[i] + " ";
                            }

                            t.sendMessage(msg);
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage("Dieser Spieler existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage("/sm <Spieler> <Nachricht>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage("/sm <Spieler> <Nachricht>");
            }
            return true;
        }

        return false;
    }
}
