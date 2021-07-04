package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class GetGamemodeCommand extends Command {
    private MarioMain plugin;

    public GetGamemodeCommand(MarioMain plugin) {
        super("getgamemode", "Lässt dich wissen, welchen Gamemode ein Spieler hat.", "getgamemode", new String[]{"ggm"});
        this.setPermission("mario.getgamemode");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.getgamemode") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 1) {
                        Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t == null) {
                                sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                return true;
                            }
                            else {
                                player.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " ist " + t.getGamemode());
                            }
                        }
                        catch (NullPointerException e) {
                            sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            return true;
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/getgamemode <Spieler>!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(MarioMain.getPrefix() + "/getgamemode <Spieler>!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            try {
                if (args.length == 1) {
                    Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t == null) {
                            sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler");
                            return true;
                        }
                        else {
                            sender.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " ist " + t.getGamemode());
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler");
                        return true;
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/getgamemode <Spieler>!");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage(MarioMain.getPrefix() + "/getgamemode <Spieler>!");
            }
        }
        return false;
    }
}
