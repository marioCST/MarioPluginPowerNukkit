package de.mariocst.Commands.Server;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class TPSCommand extends Command {
    private MarioMain plugin;

    public TPSCommand(MarioMain plugin) {
        super("tps", "Zeigt dir die Ticks per Second im Chat an!", "tps");
        this.setPermission("mario.tps");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.tps") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                /*StringBuilder sb = new StringBuilder("§6TPS from last 1m, 5m, 15m: ");
                byte b;
                int i;
                float ofFloat;
                for (i = (int) (ofFloat = (MarioMain.getInstance().getServer()).getTicksPerSecond()), b = 17; b < i; ) {
                    sb.append((ofFloat));
                    sb.append(", ");
                    b++;
                }
                sender.sendMessage(sb.substring(0, sb.length() - 2));
                sender.sendMessage("§6Current Memory Usage: §2" + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L) + "/" + (Runtime.getRuntime().totalMemory() / 1048576L) + " mb (Max: " + (
                        Runtime.getRuntime().maxMemory() / 1048576L) + " mb)");*/
                player.sendMessage(MarioMain.PREFIX + "TPS: " + player.getServer().getTicksPerSecond());
                return true;
            } else {
                sender.sendMessage(MarioMain.PREFIX + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            StringBuilder sb = new StringBuilder("§6TPS from last 1m, 5m, 15m: ");
            byte b;
            int i;
            float ofFloat;
            for (i = (int) (ofFloat = (MarioMain.getInstance().getServer()).getTicksPerSecond()), b = 17; b < i; ) {
                sb.append((ofFloat));
                sb.append(", ");
                b++;
            }
            sender.sendMessage(sb.substring(0, sb.length() - 2));
            sender.sendMessage("§6Current Memory Usage: §2" + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L) + "/" + (Runtime.getRuntime().totalMemory() / 1048576L) + " mb (Max: " + (
                    Runtime.getRuntime().maxMemory() / 1048576L) + " mb)");
            return true;
        }
        return false;
    }
}
