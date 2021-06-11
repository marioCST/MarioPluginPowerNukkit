package de.mariocst.Commands.Player;

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
                            sender.sendMessage(MarioMain.PREFIX + "§aDu kannst dich nun wieder bewegen!");
                        } else if (!player.isImmobile()) {
                            player.setImmobile(true);
                            sender.sendMessage(MarioMain.PREFIX + "§4Du kannst dich nun nicht mehr bewegen!");
                        } else {
                            sender.sendMessage(MarioMain.PREFIX + "ETWAS IST GEWALTIG SCHIEF GELAUFEN! BITTE WENDE DICH AN DEN SUPPORT!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else if (args.length == 1) {
                        Player t = player.getLevel().getServer().getPlayer(args[0]);

                        try {
                            if (t.getName() != null) {
                                if (t.isImmobile()) {
                                    t.setImmobile(false);
                                    t.sendMessage(MarioMain.PREFIX + "§aDu wurdest entfreezed und kannst dich wieder bewegen!");
                                    sender.sendMessage(MarioMain.PREFIX + "Der Spieler " + t.getName() + " wurde gefreezed!");
                                } else if (!t.isImmobile()) {
                                    t.setImmobile(true);
                                    t.sendMessage(MarioMain.PREFIX + "§4Du wurdest gefreezed und kannst die nicht mehr bewegen!");
                                    sender.sendMessage(MarioMain.PREFIX + "Der Spieler " + t.getName() + " wurde entfreezed!");
                                } else {
                                    sender.sendMessage(MarioMain.PREFIX + "ETWAS IST GEWALTIG SCHIEF GELAUFEN! BITTE WENDE DICH AN DEN SUPPORT!");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                }
                            }
                            else {
                                sender.sendMessage(MarioMain.PREFIX + "Dieser Spieler existiert nicht!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                        catch (NullPointerException e) {
                            e.printStackTrace();
                            sender.sendMessage(MarioMain.PREFIX + "Dieser Spieler existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        sender.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    sender.sendMessage(MarioMain.PREFIX + "Ungültige Parameter Länge!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            sender.sendMessage("Bitte führe den Command InGame aus!");
        }
        return false;
    }
}
