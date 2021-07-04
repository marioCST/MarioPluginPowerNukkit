package de.mariocst.Commands.Server;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Sound;
import de.mariocst.MarioMain;

public class WandCommand extends Command {
    private MarioMain plugin;

    public WandCommand(MarioMain plugin) {
        super("wand", "Öffnet eine UI für eine Plot Wand", "wand");
        this.setPermission("mario.wand");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("mario.wand") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                if (player.getLevel().getName().equals("cb01")) {
                    if (args.length == 0) {
                        FormWindowSimple simple = new FormWindowSimple("§6Plot-Wand", "§6Wähle eine Plot-Wand aus!");
                        simple.addButton(new ElementButton("§8Andesit", new ElementButtonImageData("path", "textures/blocks/stone_andesite.png")));
                        simple.addButton(new ElementButton("§8Steinziegel", new ElementButtonImageData("path", "textures/blocks/stonebrick.png")));
                        simple.addButton(new ElementButton("§8Endsteinziegel", new ElementButtonImageData("path", "textures/blocks/end_bricks.png")));
                        simple.addButton(new ElementButton("§8Polierter Granit", new ElementButtonImageData("path", "textures/blocks/stone_granite_smooth.png")));
                        simple.addButton(new ElementButton("§8Polierter Diorit", new ElementButtonImageData("path", "textures/blocks/stone_diorite_smooth.png")));
                        simple.addButton(new ElementButton("§8Polierter Andesit", new ElementButtonImageData("path", "textures/blocks/stone_andesite_smooth.png")));
                        simple.addButton(new ElementButton("§8Prismarienziegel", new ElementButtonImageData("path", "textures/blocks/prismarine_bricks.png")));
                        simple.addButton(new ElementButton("§8Sandstein", new ElementButtonImageData("path", "textures/blocks/sandstone_normal.png")));
                        simple.addButton(new ElementButton("§8Gemeißelter Sandstein", new ElementButtonImageData("path", "textures/blocks/sandstone_carved.png")));
                        simple.addButton(new ElementButton("§8Geschnittener Sandstein", new ElementButtonImageData("path", "textures/blocks/sandstone_smooth.png")));
                        simple.addButton(new ElementButton("§8Glatter Sandstein", new ElementButtonImageData("path", "textures/blocks/sandstone_top.png")));
                        simple.addButton(new ElementButton("§8Roter Sandstein", new ElementButtonImageData("path", "textures/blocks/red_sandstone_normal.png")));
                        simple.addButton(new ElementButton("§8Gemeißelter roter Sandstein", new ElementButtonImageData("path", "textures/blocks/red_sandstone_carved.png")));
                        simple.addButton(new ElementButton("§8Geschnittener roter Sandstein", new ElementButtonImageData("path", "textures/blocks/red_sandstone_smooth.png")));
                        simple.addButton(new ElementButton("§8Glatter roter Sandstein", new ElementButtonImageData("path", "textures/blocks/red_sandstone_top.png")));
                        simple.addButton(new ElementButton("§8Weißer Beton", new ElementButtonImageData("path", "textures/blocks/concrete_white.png")));
                        simple.addButton(new ElementButton("§8Oranger Beton", new ElementButtonImageData("path", "textures/blocks/concrete_orange.png")));
                        simple.addButton(new ElementButton("§8Magenta Beton", new ElementButtonImageData("path", "textures/blocks/concrete_magenta.png")));
                        simple.addButton(new ElementButton("§8Hellblauer Beton", new ElementButtonImageData("path", "textures/blocks/concrete_light_blue.png")));
                        simple.addButton(new ElementButton("§8Gelber Beton", new ElementButtonImageData("path", "textures/blocks/concrete_yellow.png")));
                        simple.addButton(new ElementButton("§8Hellgrüner Beton", new ElementButtonImageData("path", "textures/blocks/concrete_lime.png")));
                        simple.addButton(new ElementButton("§8Rosa Beton", new ElementButtonImageData("path", "textures/blocks/concrete_pink.png")));
                        simple.addButton(new ElementButton("§8Grauer Beton", new ElementButtonImageData("path", "textures/blocks/concrete_gray.png")));
                        simple.addButton(new ElementButton("§8Hellgrauer Beton", new ElementButtonImageData("path", "textures/blocks/concrete_silver.png")));
                        simple.addButton(new ElementButton("§8Türkiser Beton", new ElementButtonImageData("path", "textures/blocks/concrete_cyan.png")));
                        simple.addButton(new ElementButton("§8Violetter Beton", new ElementButtonImageData("path", "textures/blocks/concrete_purple.png")));
                        simple.addButton(new ElementButton("§8Blauer Beton", new ElementButtonImageData("path", "textures/blocks/concrete_blue.png")));
                        simple.addButton(new ElementButton("§8Brauner Beton", new ElementButtonImageData("path", "textures/blocks/concrete_brown.png")));
                        simple.addButton(new ElementButton("§8Grüner Beton", new ElementButtonImageData("path", "textures/blocks/concrete_green.png")));
                        simple.addButton(new ElementButton("§8Roter Beton", new ElementButtonImageData("path", "textures/blocks/concrete_red.png")));
                        simple.addButton(new ElementButton("§8Schwarzer Beton", new ElementButtonImageData("path", "textures/blocks/concrete_black.png")));
                        simple.addButton(new ElementButton("§8Kohleblock", new ElementButtonImageData("path", "textures/blocks/coal_block.png")));
                        simple.addButton(new ElementButton("§8Lapislazuliblock", new ElementButtonImageData("path", "textures/blocks/lapis_block.png")));
                        simple.addButton(new ElementButton("§8Eisenblock", new ElementButtonImageData("path", "textures/blocks/iron_block.png")));
                        simple.addButton(new ElementButton("§8Goldblock", new ElementButtonImageData("path", "textures/blocks/gold_block.png")));
                        simple.addButton(new ElementButton("§8Diamantblock", new ElementButtonImageData("path", "textures/blocks/diamond_block.png")));
                        simple.addButton(new ElementButton("§8Smaragdblock", new ElementButtonImageData("path", "textures/blocks/emerald_block.png")));
                        player.showFormWindow(simple);
                    }
                }
                else {
                    player.sendMessage(MarioMain.getPrefix() + "Du befindest dich nicht auf einer Plot Welt!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            assert false;
            sender.sendMessage("Bitte führe den Command InGame aus!");
        }
        return true;
    }
}
