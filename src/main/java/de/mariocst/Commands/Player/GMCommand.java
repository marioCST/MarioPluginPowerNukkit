package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class GMCommand extends Command {
    private MarioMain plugin;

    public GMCommand(MarioMain plugin) {
        super("gm", "Abkürzung für /gamemode.", "gm");
        this.setPermission("mario.gm");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        String msg = String.join(" ",  args);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("mario.gm") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length == 0) {
                    final FormWindowSimple gmui = new FormWindowSimple("§bGamemode", "§6Bitte wähle einen Gamemode.");

                    gmui.addButton(new ElementButton("§cSurvival"));
                    gmui.addButton(new ElementButton("§1Creative"));
                    gmui.addButton(new ElementButton("§6Adventure"));
                    gmui.addButton(new ElementButton("§eSpectator"));

                    player.showFormWindow(gmui);
                }
                else if (args.length == 1) {
                    if(msg.equals("0") || msg.equalsIgnoreCase("survival")) {
                        player.setGamemode(0);
                        sender.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Survival gestellt!");
                    } else if (msg.equals("1") || msg.equalsIgnoreCase("creative")) {
                        player.setGamemode(1);
                        sender.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Creative gestellt!");
                    } else if (msg.equals("2") || msg.equalsIgnoreCase("adventure")) {
                        player.setGamemode(2);
                        sender.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Adventure gestellt!");
                    } else if (msg.equals("3") || msg.equalsIgnoreCase("spectator")) {
                        player.setGamemode(3);
                        sender.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Spectator gestellt!");
                    } else {
                        sender.sendMessage(MarioMain.getPrefix() + "Bitte gib einen gültigen Gamemode ein!");
                    }
                }
                else if (args.length == 2) {
                    Player t = player.getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                    if (t != null) {
                        if(msg.equals("0") || msg.equalsIgnoreCase("survival")) {
                            t.setGamemode(0);
                            t.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Survival gestellt!");
                            sender.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " wurde auf Survival gestellt!");
                        } else if (msg.equals("1") || msg.equalsIgnoreCase("creative")) {
                            t.setGamemode(1);
                            t.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Creative gestellt!");
                            sender.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " wurde auf Creative gestellt!");
                        } else if (msg.equals("2") || msg.equalsIgnoreCase("adventure")) {
                            t.setGamemode(2);
                            t.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Adventure gestellt!");
                            sender.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " wurde auf Adventure gestellt!");
                        } else if (msg.equals("3") || msg.equalsIgnoreCase("spectator")) {
                            t.setGamemode(3);
                            t.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Spectator gestellt!");
                            sender.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " wurde auf Spectator gestellt!");
                        } else {
                            sender.sendMessage(MarioMain.getPrefix() + "Bitte gib einen gültigen Gamemode ein!");
                        }
                    }
                    else {
                        MarioMain.unknownPlayer(player);
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "Usage: /gm <0 oder 1 oder 2 oder 3 oder survival oder creative oder adventure oder spectator> [Spieler]");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            if (args.length == 2) {
                Player t = MarioMain.getInstance().getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                if (t != null) {
                    if(msg.equals("0") || msg.equalsIgnoreCase("survival")) {
                        t.setGamemode(0);
                        t.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Survival gestellt!");
                        sender.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " wurde auf Survival gestellt!");
                    } else if (msg.equals("1") || msg.equalsIgnoreCase("creative")) {
                        t.setGamemode(1);
                        t.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Creative gestellt!");
                        sender.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " wurde auf Creative gestellt!");
                    } else if (msg.equals("2") || msg.equalsIgnoreCase("adventure")) {
                        t.setGamemode(2);
                        t.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Adventure gestellt!");
                        sender.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " wurde auf Adventure gestellt!");
                    } else if (msg.equals("3") || msg.equalsIgnoreCase("spectator")) {
                        t.setGamemode(3);
                        t.sendMessage(MarioMain.getPrefix() + "Dein Gamemode wurde auf Spectator gestellt!");
                        sender.sendMessage(MarioMain.getPrefix() + "Der Gamemode von " + t.getName() + " wurde auf Spectator gestellt!");
                    } else {
                        sender.sendMessage(MarioMain.getPrefix() + "Bitte gib einen gültigen Gamemode ein!");
                    }
                }
                else {
                    MarioMain.unknownPlayer(sender);
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "Usage: /gm <0 oder 1 oder 2 oder 3 oder survival oder creative oder adventure oder spectator> <Spieler>");
            }
        }
        return false;
    }
}
