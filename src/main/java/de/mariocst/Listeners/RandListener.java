package de.mariocst.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowSimple;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import de.mariocst.MarioMain;

public class RandListener implements Listener {
    @EventHandler
    public void onRand(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        FormWindowSimple formwindow = (FormWindowSimple)event.getWindow();
        String title = formwindow.getTitle();
        String text = formwindow.getResponse().getClickedButton().getText();

        if (title.equals("§6Plot-Rand")) {
            PlotPlayer p = PlotPlayer.wrap(player);
            Plot plot = p.getCurrentPlot();
            switch (text) {
                case "§8Luft":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "0");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Luft geändert!");
                    break;
                case "§8Glatte Steinstufe":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "44");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Glatte Steinstufe geändert!");
                    break;
                case "§8Steinziegel":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "98");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Steinziegel geändert!");
                    break;
                case "§Endsteinziegel":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "121");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Endsteinziegel geändert!");
                    break;
                case "§8Polierter Granit":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "1:2");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Polierter Granit geändert!");
                    break;
                case "§8Polierter Diorit":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "1:4");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Polierter Diorit geändert!");
                    break;
                case "§8Polierter Andesit":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "1:6");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Polierter Andesit geändert!");
                    break;
                case "§8Prismarinziegel":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "168:2");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Prismarinziegel geändert!");
                    break;
                case "§8Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "24");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Sandstein geändert!");
                    break;
                case "§8Gemeißelter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "24:1");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Gemeißelter Sandstein geändert!");
                    break;
                case "§8Geschnittener Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "24:2");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Geschnittener Sandstein geändert!");
                    break;
                case "§8Glatter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "24:3");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Glatter Sandstein geändert!");
                    break;
                case "§8Roter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "179");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Roter Sandstein geändert!");
                    break;
                case "§8Gemeißelter roter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "179:1");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Gemeißelter roter Sandstein geändert!");
                    break;
                case "§8Geschnittener roter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "179:2");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Geschnittener roter Sandstein geändert!");
                    break;
                case "§8Glatter roter Sandstein":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "179:3");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Glatter roter Sandstein geändert!");
                    break;
                case "§8Weißer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Weißer Beton geändert!");
                    break;
                case "§8Oranger Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:1");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Oranger Beton geändert!");
                    break;
                case "§8Magenta Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:2");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Magenta Beton geändert!");
                    break;
                case "§8Hellblauer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:3");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Hellblauer Beton geändert!");
                    break;
                case "§8Gelber Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:4");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Gelber Beton geändert!");
                    break;
                case "§8Hellgrüner Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:5");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Hellgrüner Beton geändert!");
                    break;
                case "§8Rosa Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:6");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Rosa Beton geändert!");
                    break;
                case "§8Grauer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:7");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Grauer Beton geändert!");
                    break;
                case "§8Hellgrauer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:8");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Hellgrauer Beton geändert!");
                    break;
                case "§8Türkiser Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:9");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Türkiser Beton geändert!");
                    break;
                case "§8Violetter Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:10");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Violetter Beton geändert!");
                    break;
                case "§8Blauer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:11");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Blauer Beton geändert!");
                    break;
                case "§8Brauner Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:12");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Brauner Beton geändert!");
                    break;
                case "§8Grüner Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:13");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Grüner Beton geändert!");
                    break;
                case "§8Roter Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:14");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Roter Beton geändert!");
                    break;
                case "§8Schwarzer Beton":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "236:15");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Schwarzer Beton geändert!");
                    break;
                case "§8Kohleblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "173");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Kohleblock geändert!");
                    break;
                case "§8Lapislazuliblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "22");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Lapislazuliblock geändert!");
                    break;
                case "§8Eisenblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "42");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Eisenblock geändert!");
                    break;
                case "§8Goldblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "41");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Goldblock geändert!");
                    break;
                case "§8Diamantblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "57");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Diamantblock geändert!");
                    break;
                case "§8Smaragdblock":
                    for (Plot plots : plot.getConnectedPlots())
                        plots.setComponent("border", "133");
                    player.sendMessage(MarioMain.getPrefix() + "Dein Rand wurde zu Smaragdblock geändert!");
                    break;
            }
        }
    }
}
