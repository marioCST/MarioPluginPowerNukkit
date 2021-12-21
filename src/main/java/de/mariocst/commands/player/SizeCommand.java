package de.mariocst.commands.player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class SizeCommand extends Command {
    public SizeCommand() {
        super("size", "Lässt dich größer oder kleiner werden", "size", new String[]{"grösse", "scale", "sz"});
        this.setPermission("mario.size");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            if (player.hasPermission("mario.size") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                if (args.length == 0) {
                    final FormWindowSimple sui = new FormWindowSimple("§aGröße", "§6Bitte wähle eine Größe.");

                    sui.addButton(new ElementButton("§4Riesig"));
                    sui.addButton(new ElementButton("§6Groß"));
                    sui.addButton(new ElementButton("§aNormal"));
                    sui.addButton(new ElementButton("§bKlein"));
                    sui.addButton(new ElementButton("§1Winzig"));

                    player.showFormWindow(sui);
                }
                else if (args.length == 1) {
                    if (player.hasPermission("mario.size.custom") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                        try {
                            float getSize = Float.parseFloat(args[0]);

                            if (getSize >= 72) {
                                player.sendMessage(MarioMain.getPrefix() + "Bitte wähle eine kleinere Größe! Ab 72 laggt Minecraft hart ^^");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                            else if (getSize <= -72) {
                                player.sendMessage(MarioMain.getPrefix() + "Bitte wähle eine größere Größe! Ab -72 laggt Minecraft hart ^^");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                            else {
                                if (args[0].equals("1")) {
                                    player.sendMessage(MarioMain.getPrefix() + "Deine Größe wurde zurückgesetzt!");
                                    player.setScale(1);
                                }
                                else {
                                    float size = Float.parseFloat(args[0]);
                                    player.sendMessage(MarioMain.getPrefix() + "Deine Größe wurde auf " + args[0] + " gesetzt!");
                                    player.setScale(size);
                                }
                            }
                        }
                        catch (NumberFormatException e) {
                            player.sendMessage(MarioMain.getPrefix() + "Bitte gib eine (gültige) Zahl ein!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "Keine Rechte für eine custom Größe!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "/size [Größe]!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            if (args.length == 2) {
                Player t = MarioMain.getInstance().getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                try {
                    if (t != null) {
                        try {
                            t.setScale(Float.parseFloat(args[0]));
                        }
                        catch (NumberFormatException e) {
                            sender.sendMessage(MarioMain.getPrefix() + "Bitte gib eine gültige Zahl ein!");
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                    }
                }
                catch (NullPointerException e) {
                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "/size <Größe> <Spieler>!");
            }
        }
        return false;
    }
}
