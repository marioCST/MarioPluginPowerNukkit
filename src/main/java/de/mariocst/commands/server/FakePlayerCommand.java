package de.mariocst.commands.server;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.level.Sound;
import cn.nukkit.nbt.tag.CompoundTag;
import de.mariocst.MarioMain;

import java.nio.charset.StandardCharsets;

public class FakePlayerCommand extends Command {
    public FakePlayerCommand() {
        super("fakeplayer", "Spawnt einen FakePlayer!", "fakeplayer", new String[]{"fp"});
        this.setPermission("mario.fakeplayer");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MarioMain.getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission("mario.fakeplayer") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "create" -> {
                        CompoundTag nbt = Entity.getDefaultNBT(player.getLocation());
                        Skin skin = player.getSkin();

                        CompoundTag skinTag = new CompoundTag()
                                .putByteArray("Data", skin.getSkinData().data)
                                .putInt("SkinImageWidth", skin.getSkinData().width)
                                .putInt("SkinImageHeight", skin.getSkinData().height)
                                .putString("ModelId", skin.getSkinId())
                                .putString("CapeId", skin.getCapeId())
                                .putByteArray("CapeData", skin.getCapeData().data)
                                .putInt("CapeImageWidth", skin.getCapeData().width)
                                .putInt("CapeImageHeight", skin.getCapeData().height)
                                .putByteArray("SkinResourcePatch", skin.getSkinResourcePatch().getBytes(StandardCharsets.UTF_8))
                                .putByteArray("GeometryData", skin.getGeometryData().getBytes(StandardCharsets.UTF_8))
                                .putByteArray("AnimationData", skin.getAnimationData().getBytes(StandardCharsets.UTF_8))
                                .putBoolean("PremiumSkin", skin.isPremium())
                                .putBoolean("PersonaSkin", skin.isPersona())
                                .putBoolean("CapeOnClassicSkin", skin.isCapeOnClassic());

                        nbt.putCompound("Skin", skinTag);

                        EntityHuman human = new EntityHuman(player.getChunk(), nbt);
                        human.spawnToAll();
                        human.setSkin(player.getSkin());
                        human.setMaxHealth(20);
                        human.setNameTag(player.getName());

                        player.sendMessage(MarioMain.getPrefix() + "FakePlayer erfolgreich gespawnt!");
                    }
                    case "delete" -> {
                        for (Entity entity : player.getLevel().getEntities()) {
                            if (entity instanceof EntityHuman && entity.getName().equals(player.getName()) && entity != player) {
                                entity.despawnFromAll();
                                player.sendMessage(MarioMain.getPrefix() + "FakePlayer gelöscht!");
                                return true;
                            }
                        }

                        player.sendMessage(MarioMain.getPrefix() + "FakePlayer konnte nicht gefunden werden!");
                    }
                    default -> player.sendMessage(MarioMain.getPrefix() + "/fp <create|delete>");
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "/fp <create|delete>");
            }
        }
        else {
            player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
        }
        return false;
    }
}
