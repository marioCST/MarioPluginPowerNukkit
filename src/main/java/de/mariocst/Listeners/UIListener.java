package de.mariocst.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Location;
import de.mariocst.MarioMain;

public class UIListener implements Listener {
    @EventHandler
    public void onResponse(final PlayerFormRespondedEvent event) {
        if (event.getWindow() instanceof FormWindowSimple) {
            if (event.getResponse() instanceof FormResponseSimple) {
                final FormWindowSimple fws = (FormWindowSimple) event.getWindow();
                Player player = event.getPlayer();
                String button = fws.getResponse().getClickedButton().getText();

                if (fws.getTitle().equals("§aGröße")) {
                    if (button.equals("§4Riesig")) {
                        player.setScale(7.0F);

                        player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun riesig!");
                    }
                    if (button.equals("§6Groß")) {
                        player.setScale(5.0F);

                        player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun groß!");
                    }
                    if (button.equals("§aNormal")) {
                        player.setScale(1.0F);

                        player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun normal!");
                    }
                    if (button.equals("§bKlein")) {
                        player.setScale(0.7F);

                        player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun klein!");
                    }
                    if (button.equals("§1Winzig")) {
                        player.setScale(0.3F);

                        player.sendMessage(MarioMain.getPrefix() + "Deine Größe ist nun winzig!");
                    }
                }
                if (fws.getTitle().equals("§bGamemode")) {
                    if (button.equals("§cSurvival")) {
                        player.setGamemode(0);

                        player.sendMessage(MarioMain.getPrefix() + "Dein Gamemode ist nun Survival!");
                    }
                    if (button.equals("§1Creative")) {
                        player.setGamemode(1);

                        player.sendMessage(MarioMain.getPrefix() + "Dein Gamemode ist nun Creative!");
                    }
                    if (button.equals("§6Adventure")) {
                        player.setGamemode(2);

                        player.sendMessage(MarioMain.getPrefix() + "Dein Gamemode ist nun Adventure!");
                    }
                    if (button.equals("§eSpectator")) {
                        player.setGamemode(3);

                        player.sendMessage(MarioMain.getPrefix() + "Dein Gamemode ist nun Spectator!");
                    }
                }
            }
        }
    }
}
