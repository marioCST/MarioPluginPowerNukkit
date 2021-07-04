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
                if (fws.getTitle().equals("§aWorld Teleport")) {
                    if (button.equals("Lobby")) {
                        if (MarioMain.getInstance().getServer().getLevelByName("flatmap") != null) {
                            player.teleport(new Location(-71.5, 7, 92.5, MarioMain.getInstance().getServer().getLevelByName("flatmap")));
                        }
                    }
                    if (button.equals("§1Citybuild 1")) {
                        if (MarioMain.getInstance().getServer().getLevelByName("cb01") != null) {
                            player.teleport(new Location(-48.5, 69.5, -48.5, MarioMain.getInstance().getServer().getLevelByName("cb01")));
                        }
                    }
                    if (button.equals("§2Farmwelt")) {
                        if (MarioMain.getInstance().getServer().getLevelByName("farmwelt") != null) {
                            player.teleport(new Location(-51.5, 64, 239.5, MarioMain.getInstance().getServer().getLevelByName("farmwelt")));
                        }
                    }
                    if (button.equals("§4Nether")) {
                        if (MarioMain.getInstance().getServer().getLevelByName("netherfw") != null) {
                            player.teleport(new Location(121.5, 71, 130.5, MarioMain.getInstance().getServer().getLevelByName("netherfw")));
                        }
                    }
                    if (button.equals("§eEnd")) {
                        if (MarioMain.getInstance().getServer().getLevelByName("endfw") != null) {
                            player.teleport(new Location(100.5, 56, 39.5, MarioMain.getInstance().getServer().getLevelByName("endfw")));
                        }
                    }
                }
            }
        }
    }
}
