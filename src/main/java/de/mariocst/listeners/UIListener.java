package de.mariocst.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import de.mariocst.MarioMain;

public class UIListener implements Listener {
    @EventHandler
    public void onResponse(final PlayerFormRespondedEvent event) {
        if (!(event.getWindow() instanceof FormWindowSimple) || !(event.getResponse() instanceof FormResponseSimple)) return;

        final FormWindowSimple fws = (FormWindowSimple) event.getWindow();
        Player player = event.getPlayer();
        String button = fws.getResponse().getClickedButton().getText();

        if (fws.getTitle().equals("§aGröße")) {
            switch (button) {
                case "§4Riesig" -> {
                    player.setScale(7.0F);
                    player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun riesig!");
                }
                case "§6Groß" -> {
                    player.setScale(5.0F);
                    player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun groß!");
                }
                case "§aNormal" -> {
                    player.setScale(1.0F);
                    player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun normal!");
                }
                case "§bKlein" -> {
                    player.setScale(0.7F);
                    player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun klein!");
                }
                case "§1Winzig" -> {
                    player.setScale(0.3F);
                    player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun winzig!");
                }
            }
        }
        else if (fws.getTitle().equals("§bGamemode")) {
            switch (button) {
                case "§cSurvival" -> {
                    player.setGamemode(0);
                    player.sendMessage(MarioMain.getPrefix() + "Dein Gamemode ist nun Survival!");
                }
                case "§1Creative" -> {
                    player.setGamemode(1);
                    player.sendMessage(MarioMain.getPrefix() + "Dein Gamemode ist nun Creative!");
                }
                case "§6Adventure" -> {
                    player.setGamemode(2);
                    player.sendMessage(MarioMain.getPrefix() + "Dein Gamemode ist nun Adventure!");
                }
                case "§eSpectator" -> {
                    player.setGamemode(3);
                    player.sendMessage(MarioMain.getPrefix() + "Dein Gamemode ist nun Spectator!");
                }
            }
        }
    }
}
