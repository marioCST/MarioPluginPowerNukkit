package de.mariocst.forms;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.level.Sound;
import de.mariocst.forms.custom.CustomForm;
import de.mariocst.forms.simple.SimpleForm;
import de.mariocst.MarioMain;
import de.mariocst.timer.Timer;

import java.util.HashMap;

public class FormTimer {
    public static HashMap<String, String> messages = new HashMap<>();

    public static String getNP(String key, String description, Object... replacements) {
        String message = messages.getOrDefault(key, description).replace("&", "§");

        int i = 0;
        for (Object replacement : replacements) {
            message = message.replace("[" + i + "]", String.valueOf(replacement));
            i++;
        }

        return message;
    }

    public void openTimer(Player player) {
        SimpleForm form = new SimpleForm.Builder("§6Timer",
                getNP("", "§aSuch dir eine Kategorie aus!"))
                .addButton(new ElementButton("§6Start", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/play-icon.png")), e -> this.startTimer(player))
                .addButton(new ElementButton("§6Stopp", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/pause-icon.png")), e -> this.stopTimer(player))
                .addButton(new ElementButton("§6Reset", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/arrow-circle.png")), e -> this.resetTimer(player))
                .addButton(new ElementButton("§6Zeit", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/timers.png")), e -> this.openZeitMenu(player))
                .build();
        form.send(player);
    }

    public void openZeitMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6Zeit")
                .addElement(new ElementInput("Sekunden", "0"))
                .addElement(new ElementInput("Minuten", "0"))
                .addElement(new ElementInput("Stunden", "0"))
                .onSubmit((e, r) -> {
                    if (r.getInputResponse(0).isEmpty() || r.getInputResponse(1).isEmpty() || r.getInputResponse(2).isEmpty()) {
                        player.sendMessage(MarioMain.getPrefix() + "Bitte gib Sekunden, Minuten und Stunden ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }

                    Timer timer = MarioMain.getInstance().getTimer();

                    try {
                        timer.setRunning(false);
                        timer.setSeconds(Integer.parseInt(r.getInputResponse(0)));
                        timer.setMinutes(Integer.parseInt(r.getInputResponse(1)));
                        timer.setHours(Integer.parseInt(r.getInputResponse(2)));
                        player.sendMessage(MarioMain.getPrefix() + "Die Zeit wurde auf " + r.getInputResponse(0) + " Sekunden, " + r.getInputResponse(1) + " Minuten und " + r.getInputResponse(2) + " Stunden gesetzt!");
                    } catch (NumberFormatException exception) {
                        player.sendMessage(MarioMain.getPrefix() + "§4Deine angegebenen Sekunden, Minuten und Stunden müssen eine ganze Zahl sein!");
                        exception.printStackTrace();
                    }
                })
                .build();
        form.send(player);
    }

    private void startTimer(Player player) {
        Timer timer = MarioMain.getInstance().getTimer();
        if (timer.isRunning()) {
            player.sendMessage(MarioMain.getPrefix() + "§cDer Timer läuft bereits!");
            return;
        }

        timer.setRunning(true);
        player.sendMessage(MarioMain.getPrefix() + "Der Timer wurde gestartet!");
    }

    private void stopTimer(Player player) {
        Timer timer = MarioMain.getInstance().getTimer();
        if (!timer.isRunning()) {
            player.sendMessage(MarioMain.getPrefix() + "§cDer Timer läuft nicht.");
            return;
        }

        timer.setRunning(false);
        player.sendMessage(MarioMain.getPrefix() + "Der Timer wurde gestoppt!");
    }

    private void resetTimer(Player player) {
        Timer timer = MarioMain.getInstance().getTimer();
        timer.setRunning(false);
        timer.setSeconds(0);
        timer.setMinutes(0);
        timer.setHours(0);
        player.sendMessage(MarioMain.getPrefix() + "Der Timer wurde resettet!");
    }
}
