package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class SizeCommand extends Command {
    private MarioMain plugin;

    public SizeCommand(MarioMain plugin) {
        super("size", "Lässt dich größer oder kleiner werden", "size", new String[]{"grösse", "scale", "sz"});
        this.setPermission("mario.size");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mario.size") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length == 1) {
                    try {
                        float getSize = Float.parseFloat(args[0]);
                        if (getSize >= 72) {
                            sender.sendMessage(MarioMain.PREFIX + "Bitte wähle eine kleinere Größe! Ab 72 laggt Minecraft hart ^^");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        } else if (getSize <= -72) {
                            sender.sendMessage(MarioMain.PREFIX + "Bitte wähle eine größere Größe! Ab -72 laggt Minecraft hart ^^");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        } else {
                            if (args[0].equals("1")) {
                                sender.sendMessage(MarioMain.PREFIX + "Deine Größe wurde zurückgesetzt!");
                                player.setScale(1);
                            } else {
                                float size = Float.parseFloat(args[0]);
                                sender.sendMessage(MarioMain.PREFIX + "Deine Größe wurde auf " + args[0] + " gesetzt!");
                                player.setScale(size);
                            }
                        }
                    } catch (NumberFormatException e) {
                        sender.sendMessage(MarioMain.PREFIX + "Bitte gib eine (gültige) Zahl ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        e.printStackTrace();
                    }
                } else if (args.length >= 2) {
                    sender.sendMessage(MarioMain.PREFIX + "Bitte schreibe eine einspaltige Größe!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                } else {
                    sender.sendMessage(MarioMain.PREFIX + "Bitte schreibe eine Größe!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Du bist eine Konsole.");
        }
        return false;
    }
}
