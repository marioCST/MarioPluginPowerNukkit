package de.mariocst.Commands.Player.Movement;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class FreezeCommand extends Command {
    private MarioMain plugin;

    public FreezeCommand(MarioMain plugin) {
        super("freeze", "Lässt dich einfrieren", "freeze", new String[]{"fr"});
        this.setPermission("mario.freeze");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.freeze") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 0) {
                        if (player.isImmobile()) {
                            player.setImmobile(false);
                            sender.sendMessage(MarioMain.getPrefix() + "§aDu kannst dich nun wieder bewegen!");
                        } else if (!player.isImmobile()) {
                            player.setImmobile(true);
                            sender.sendMessage(MarioMain.getPrefix() + "§4Du kannst dich nun nicht mehr bewegen!");
                        } else {
                            sender.sendMessage(MarioMain.getPrefix() + "ETWAS IST GEWALTIG SCHIEF GELAUFEN! BITTE WENDE DICH AN DEN SUPPORT!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else if (args.length == 1) {
                        Player t = player.getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t != null) {
                                if (t.isImmobile()) {
                                    t.setImmobile(false);
                                    t.sendMessage(MarioMain.getPrefix() + "§aDu wurdest entfreezed und kannst dich wieder bewegen!");
                                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde gefreezed!");
                                } else if (!t.isImmobile()) {
                                    t.setImmobile(true);
                                    t.sendMessage(MarioMain.getPrefix() + "§4Du wurdest gefreezed und kannst die nicht mehr bewegen!");
                                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde entfreezed!");
                                } else {
                                    sender.sendMessage(MarioMain.getPrefix() + "ETWAS IST GEWALTIG SCHIEF GELAUFEN! BITTE WENDE DICH AN DEN SUPPORT!");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                }
                            }
                            else {
                                sender.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                        catch (NullPointerException e) {
                            e.printStackTrace();
                            sender.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "/freeze [Spieler]!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    sender.sendMessage(MarioMain.getPrefix() + "/freeze [Spieler]!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            if (args.length == 1) {
                Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                try {
                    if (t.getName() != null) {
                        if (t.isImmobile()) {
                            t.setImmobile(false);
                            t.sendMessage(MarioMain.getPrefix() + "§aDu wurdest entfreezed und kannst dich wieder bewegen!");
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde gefreezed!");
                        } else if (!t.isImmobile()) {
                            t.setImmobile(true);
                            t.sendMessage(MarioMain.getPrefix() + "§4Du wurdest gefreezed und kannst die nicht mehr bewegen!");
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde entfreezed!");
                        } else {
                            sender.sendMessage(MarioMain.getPrefix() + "ETWAS IST GEWALTIG SCHIEF GELAUFEN! BITTE WENDE DICH AN DEN SUPPORT!");
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                    }
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                    sender.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "/freeze <Spieler>!");
            }
        }
        return false;
    }
}
