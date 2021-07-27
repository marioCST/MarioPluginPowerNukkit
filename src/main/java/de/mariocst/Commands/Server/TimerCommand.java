package de.mariocst.Commands.Server;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;
import de.mariocst.Timer.Timer;

public class TimerCommand extends Command {
    private MarioMain plugin;

    public TimerCommand(MarioMain plugin) {
        super("timer", "Starte, stoppe und resette den Timer!", "timer");
        this.setPermission("mario.timer");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        Timer timer = MarioMain.getInstance().getTimer();

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mario.timer") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                if (args.length == 0) {
                    sendUsage(sender);
                    return true;
                }

                switch (args[0].toLowerCase()) {
                    case "resume" -> {
                        if (timer.isRunning()) {
                            sender.sendMessage(MarioMain.getPrefix() + "§cDer Timer läuft bereits!");
                            break;
                        }

                        timer.setRunning(true);
                        sender.sendMessage(MarioMain.getPrefix() + "Der Timer wurde gestartet!");
                    }
                    case "pause" -> {
                        if (!timer.isRunning()) {
                            sender.sendMessage(MarioMain.getPrefix() + "§cDer Timer läuft nicht.");
                            break;
                        }

                        timer.setRunning(false);
                        sender.sendMessage(MarioMain.getPrefix() + "Der Timer wurde gestoppt!");
                    }
                    case "time" -> {
                        if (args.length != 4) {
                            sender.sendMessage("§cUsage: §6/timer time <Seconds> <Minutes> <Hours>");
                            return true;
                        }

                        try {
                            timer.setRunning(false);
                            timer.setSeconds(Integer.parseInt(args[1]));
                            timer.setMinutes(Integer.parseInt(args[2]));
                            timer.setHours(Integer.parseInt(args[3]));
                            sender.sendMessage(MarioMain.getPrefix() + "Die Zeit wurde auf " + args[1] + " Sekunden, " + args[2] + " Minuten und " + args[3] + " Stunden gesetzt!");
                        } catch (NumberFormatException e) {
                            sender.sendMessage(MarioMain.getPrefix() + "§4Deine Parameter 2, 3 und 4 müssen eine ganze Zahl sein!");
                            e.printStackTrace();
                        }
                    }
                    case "reset" -> {
                        timer.setRunning(false);
                        timer.setSeconds(0);
                        timer.setMinutes(0);
                        timer.setHours(0);
                        sender.sendMessage(MarioMain.getPrefix() + "Der Timer wurde resettet!");
                    }
                }
            } else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            if (args.length == 0) {
                sendUsage(sender);
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "resume" -> {
                    if (timer.isRunning()) {
                        sender.sendMessage(MarioMain.getPrefix() + "§cDer Timer läuft bereits!");
                        break;
                    }

                    timer.setRunning(true);
                    sender.sendMessage(MarioMain.getPrefix() + "Der Timer wurde gestartet!");
                }
                case "pause" -> {
                    if (!timer.isRunning()) {
                        sender.sendMessage(MarioMain.getPrefix() + "§cDer Timer läuft nicht.");
                        break;
                    }

                    timer.setRunning(false);
                    sender.sendMessage(MarioMain.getPrefix() + "Der Timer wurde gestoppt!");
                }
                case "time" -> {
                    if (args.length != 4) {
                        sender.sendMessage("§cUsage: §6/timer time <Seconds> <Minutes> <Hours>");
                        return true;
                    }

                    try {
                        timer.setRunning(false);
                        timer.setSeconds(Integer.parseInt(args[1]));
                        timer.setMinutes(Integer.parseInt(args[2]));
                        timer.setHours(Integer.parseInt(args[3]));
                        sender.sendMessage(MarioMain.getPrefix() + "Die Zeit wurde auf " + args[1] + " Sekunden, " + args[2] + " Minuten und " + args[3] + " Stunden gesetzt!");
                    } catch (NumberFormatException e) {
                        sender.sendMessage(MarioMain.getPrefix() + "§4Deine Parameter 2, 3 und 4 müssen eine ganze Zahl sein!");
                        e.printStackTrace();
                    }
                }
                case "reset" -> {
                    timer.setRunning(false);
                    timer.setSeconds(0);
                    timer.setMinutes(0);
                    timer.setHours(0);
                    sender.sendMessage(MarioMain.getPrefix() + "Der Timer wurde resettet!");
                }
            }
        }

        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage("§cUsage: §6/timer resume, /timer pause, /timer time <Seconds> <Minutes> <Hours>, /timer reset");
    }
}
