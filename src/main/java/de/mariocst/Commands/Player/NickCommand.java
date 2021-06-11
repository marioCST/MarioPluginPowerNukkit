package de.mariocst.Commands.Player;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class NickCommand extends Command {
    private MarioMain plugin;

    public NickCommand(MarioMain plugin) {
        super("nick", "Ändert deinen Nickname", "nick");
        this.setPermission("mario.nick");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mario.nick") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length == 1) {
                    if (args[0].length() < 16) {
                        String newNick = String.join(" ", args);
                        player.setDisplayName(newNick);
                        player.setNameTag(newNick);
                        sender.sendMessage(MarioMain.PREFIX + "Dein Nickname wurde erfolgreich zu " + newNick + " geändert!");
                    } else {
                        sender.sendMessage(MarioMain.PREFIX + "Bitte wähle einen Namen, der kürzer als 16 Zeichen ist!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                } else {
                    sender.sendMessage(MarioMain.PREFIX + "Bitte gib einen Nickname ein, oder suche dir einen, der keine Leerzeichen hat!");
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