package de.mariocst.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowSimple;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import de.mariocst.MarioMain;

public class WandListener implements Listener {
    @EventHandler
    public void onWand(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        FormWindowSimple formwindow = (FormWindowSimple)event.getWindow();
        String title = formwindow.getTitle();
        String text = formwindow.getResponse().getClickedButton().getText();

        if (title.equals("§6Plot-Wand")) {
            PlotPlayer p = PlotPlayer.wrap(player);
            Plot plot = p.getCurrentPlot();
            switch (text) {
                case "§8Andesit":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "1:5");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Andesit geändert!");
                    break;
                case "§8Steinziegel":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "98");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Steinziegel geändert!");
                    break;
                case "§8Endsteinziegel":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "121");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Endsteinziegel geändert!");
                    break;
                case "§8Polierter Granit":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "1:2");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Polierter Granit geändert!");
                    break;
                case "§8Polierter Diorit":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "1:4");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Polierter Diorit geändert!");
                    break;
                case "§8Polierter Andesit":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "1:6");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Polierter Andesit geändert!");
                    break;
                case "§8Prismarinziegel":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "168:2");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Prismarinziegel geändert!");
                    break;
                case "§8Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "24");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Sandstein geändert!");
                    break;
                case "§8Gemeißelter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "24:1");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Gemeißelter Sandstein geändert!");
                    break;
                case "§8Geschnittener Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "24:2");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Geschnittener Sandstein geändert!");
                    break;
                case "§8Glatter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "24:3");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Glatter Sandstein geändert!");
                    break;
                case "§8Roter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "179");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Roter Sandstein geändert!");
                    break;
                case "§8Gemeißelter roter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "179:1");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Gemeißelter roter Sandstein geändert!");
                    break;
                case "§8Geschnittener roter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "179:2");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Geschnittener roter Sandstein geändert!");
                    break;
                case "§8Glatter roter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "179:3");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Glatter roter Sandstein geändert!");
                    break;
                case "§8Weißer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Weißer Beton geändert!");
                    break;
                case "§8Oranger Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:1");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Oranger Beton geändert!");
                    break;
                case "§8Magenta Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:2");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Magenta Beton geändert!");
                    break;
                case "§8Hellblauer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:3");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Hellblauer Beton geändert!");
                    break;
                case "§8Gelber Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:4");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Gelber Beton geändert!");
                    break;
                case "§8Hellgrüner Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:5");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Hellgrüner Beton geändert!");
                    break;
                case "§8Rosa Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:6");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Rosa Beton geändert!");
                    break;
                case "§8Grauer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:7");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Grauer Beton geändert!");
                    break;
                case "§8Hellgrauer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:8");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Hellgrauer Beton geändert!");
                    break;
                case "§8Türkiser Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:9");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Türkiser Beton geändert!");
                    break;
                case "§8Violetter Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:10");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Violetter Beton geändert!");
                    break;
                case "§8Blauer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:11");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Blauer Beton geändert!");
                    break;
                case "§8Brauner Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:12");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Brauner Beton geändert!");
                    break;
                case "§8Grüner Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:13");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Grüner Beton geändert!");
                    break;
                case "Roter Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:14");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Roter Beton geändert!");
                    break;
                case "§8Schwarzer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "236:15");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Schwarzer Beton geändert!");
                    break;
                case "§8Kohleblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "173");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Kohleblock geändert!");
                    break;
                case "§8Lapislazuliblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "22");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Lapislazuliblock geändert!");
                    break;
                case "§8Eisenblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "42");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Eisenblock geändert!");
                    break;
                case "§8Goldblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "41");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Goldblock geändert!");
                    break;
                case "§8Diamantblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "57");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Diamantblock geändert!");
                    break;
                case "§8Smaragdblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("wall", "133");
                    player.sendMessage(MarioMain.getPrefix() + "Deine Wand wurde zu Smaragdblock geändert!");
                    break;
            }
        }
    }
}
