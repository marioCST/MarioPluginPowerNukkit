package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class SkinCommand extends Command {
    private MarioMain plugin;

    public SkinCommand(MarioMain plugin) {
        super("skin", "Übernehme den Skin eines anderen Spielers!.", "dumb", new String[]{"skin"});
        this.setPermission("mario.skin");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.skin") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 2) {
                        Player t = player.getServer().getPlayer(args[1]);

                        try {
                            if (null == t.getName()) {
                                sender.sendMessage(MarioMain.PREFIX + "Unbekannter Spieler");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                return true;
                            }
                            else {
                                if (t.getName().equals(player.getName())) {
                                    sender.sendMessage(MarioMain.PREFIX + "Du musst deinen eigenen Skin manuell wieder rein machen!");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                }
                                else {
                                    player.setSkin(t.getSkin());
                                    sender.sendMessage(MarioMain.PREFIX + "Du hast nun den Skin von: " + t.getName());
                                }
                            }
                        }
                        catch (NullPointerException e) {
                            sender.sendMessage(MarioMain.PREFIX + "Unbekannter Spieler");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            return true;
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage(MarioMain.PREFIX + "Bitte führe den Command InGame aus!");
        }
        return false;
    }
}
