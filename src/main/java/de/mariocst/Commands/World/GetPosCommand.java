package de.mariocst.Commands.World;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class GetPosCommand extends Command {
    private MarioMain plugin;

    public GetPosCommand(MarioMain plugin) {
        super("getpos", "Welche Position habe ich?", "getpos", new String[]{"gp"});
        this.setPermission("mario.getpos");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.getpos") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 0) {
                        sender.sendMessage(MarioMain.getPrefix() + "Deine Position ist: " +
                                "Level: " + player.getLevel().getName() +
                                " X: " + player.getLocation().getX() +
                                " Y: " + player.getLocation().getY() +
                                " Z: " + player.getLocation().getZ());
                    }
                    else if (args.length == 1) {
                        Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t == null) {
                                sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                return true;
                            }
                            else {
                                sender.sendMessage(MarioMain.getPrefix() + "Die Position von " + t.getName() + " ist: " +
                                        "Level: " + t.getLevel().getName() +
                                        " X: " + t.getLocation().getX() +
                                        " Y: " + t.getLocation().getY() +
                                        " Z: " + t.getLocation().getZ());
                            }
                        }
                        catch (NullPointerException e) {
                            sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            return true;
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/getpos [Spieler]!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(MarioMain.getPrefix() + "/getpos [Spieler]!");
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
                            sender.sendMessage(MarioMain.getPrefix() + "Die Position von " + t.getName() + " ist: " +
                                    "Level: " + t.getLevel().getName() +
                                    " X: " + t.getLocation().getX() +
                                    " Y: " + t.getLocation().getY() +
                                    " Z: " + t.getLocation().getZ());
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler");
                        return true;
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/getpos <Spieler>!");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage(MarioMain.getPrefix() + "/getpos <Spieler>!");
            }
        }
        return false;
    }
}
