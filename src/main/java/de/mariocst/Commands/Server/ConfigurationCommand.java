package de.mariocst.Commands.Server;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class ConfigurationCommand extends Command {
    private MarioMain plugin;

    public ConfigurationCommand(MarioMain plugin) {
        super("configuration", "Speicher oder lade die Config neu", "configuration", new String[]{"config"});
        this.setPermission("mario.configuration");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.configuration") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 1) {
                        switch (args[0].toLowerCase()) {
                            case "save" -> {
                                MarioMain.getInstance().saveConfigs();
                                player.sendMessage(MarioMain.getPrefix() + "Configs gespeichert!");
                            }
                            case "reload" -> {
                                MarioMain.getInstance().reloadConfigs();
                                player.sendMessage(MarioMain.getPrefix() + "Configs neu geladen!");
                            }
                            default -> {
                                player.sendMessage(MarioMain.getPrefix() + "/config <reload|save>");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/config <reload|save>");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(MarioMain.getPrefix() + "/config <reload|save>");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            try {
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                        case "save" -> {
                            MarioMain.getInstance().saveConfigs();
                            sender.sendMessage(MarioMain.getPrefix() + "Configs gespeichert!");
                        }
                        case "reload" -> {
                            MarioMain.getInstance().reloadConfigs();
                            sender.sendMessage(MarioMain.getPrefix() + "Configs neu geladen!");
                        }
                        default -> {
                            sender.sendMessage(MarioMain.getPrefix() + "/config <reload|save>");
                        }
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/config <reload|save>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage(MarioMain.getPrefix() + "/config <reload|save>");
            }
        }

        return false;
    }
}
