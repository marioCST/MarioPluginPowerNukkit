package de.mariocst.Commands.Player;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.weather.EntityLightning;
import cn.nukkit.event.weather.LightningStrikeEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Sound;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import de.mariocst.MarioMain;

public class TrollCommand extends Command {
    private MarioMain plugin;

    public TrollCommand(MarioMain plugin) {
        super("troll", "Trolle jemanden!", "troll");
        this.setPermission("mario.troll");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("mario.troll") || sender.hasPermission("mario.*") || sender.hasPermission("*") || sender.isOp()) {
                try {
                    if (args.length == 0) {
                        MarioMain.getInstance().getFormTroll().openTroll(player);
                    }
                    else if (args.length == 2) {
                        Player t = MarioMain.getInstance().getServer().getPlayer(args[1].replaceAll("_", " ").replaceAll("\"", ""));

                        try {
                            if (t != null) {
                                switch (args[0].toLowerCase()) {
                                    case "drop" -> {
                                        for (int i = 0; i <= 39; i++) {
                                            if (t.getInventory().getItem(i) != Item.get(BlockID.AIR)) {
                                                t.dropItem(t.getInventory().getItem(i));
                                                t.getInventory().clear(i, true);
                                            }
                                        }

                                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde erfolgreich mit Itemdrop getrollt!");
                                    }
                                    case "damage", "dmg" -> {
                                        if (!(t.getGamemode() == 1) && !(t.getGamemode() == 3)) {
                                            boolean hadFly = false;

                                            if (MarioMain.hasFly(t)) {
                                                t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, false);
                                                t.getAdventureSettings().update();
                                                hadFly = true;
                                            }

                                            t.getLevel().getGameRules().setGameRule(GameRule.FALL_DAMAGE, true);

                                            t.fall(5.0F);

                                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde erfolgreich mit Damage getrollt!");

                                            t.getLevel().getGameRules().setGameRule(GameRule.FALL_DAMAGE, false);

                                            if (hadFly) {
                                                t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                                                t.getAdventureSettings().update();
                                            }
                                        } else {
                                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " kann keinen Schaden bekommen!");
                                        }
                                    }
                                    case "tnt" -> {
                                        if (MarioMain.getInstance().getServer().getPluginManager().getPlugin("MobPlugin") != null) {
                                            MarioMain.getInstance().getServer().dispatchCommand(MarioMain.getInstance().getServer().getConsoleSender(), "summon primed_tnt " + t.getName());

                                            player.sendMessage(MarioMain.getPrefix() + "TNT bei " + t.getName() + " gespawnt!");
                                        }
                                        else {
                                            player.sendMessage(MarioMain.getPrefix() + "Plugin \"MobPlugin\" ist nicht installiert! /troll tnt geht leider nicht.");
                                        }
                                    }
                                    case "pumpkin", "pk", "jumpscare", "js" -> {
                                        t.getInventory().setHelmet(Item.get(-155));

                                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " hat evtl. einen Jumpscare bekommen!");
                                    }
                                    case "inventory", "inv" -> {
                                        if (MarioMain.getInstance().invTroll.contains(t)) {
                                            MarioMain.getInstance().invTroll.remove(t);

                                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf nun wieder sein Inventar benutzen!");
                                        }
                                        else {
                                            MarioMain.getInstance().invTroll.add(t);

                                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf nun nicht mehr sein Inventar benutzen!");
                                        }
                                    }
                                    case "move" -> {
                                        if (MarioMain.getInstance().moveTroll.contains(t)) {
                                            MarioMain.getInstance().moveTroll.remove(t);

                                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf sich nun wieder bewegen!");
                                        }
                                        else {
                                            MarioMain.getInstance().moveTroll.add(t);

                                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf sich nun nicht mehr bewegen!");
                                        }
                                    }
                                    case "thunderstrike", "ts", "strike" -> {
                                        CompoundTag nbt = new CompoundTag()
                                                .putList(new ListTag<DoubleTag>("Pos").add(new DoubleTag("", t.getX()))
                                                        .add(new DoubleTag("", t.getY())).add(new DoubleTag("", t.getZ())))
                                                .putList(new ListTag<DoubleTag>("Motion").add(new DoubleTag("", 0))
                                                        .add(new DoubleTag("", 0)).add(new DoubleTag("", 0)))
                                                .putList(new ListTag<FloatTag>("Rotation").add(new FloatTag("", 0))
                                                        .add(new FloatTag("", 0)));

                                        EntityLightning bolt = new EntityLightning(t.getChunk(), nbt);
                                        LightningStrikeEvent event = new LightningStrikeEvent(t.getLevel(), bolt);

                                        MarioMain.getInstance().getServer().getPluginManager().callEvent(event);

                                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " hat einen Schlag!");
                                    }
                                    default -> {
                                        player.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstrike> <Spieler>!");
                                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                    }
                                }
                            }
                            else {
                                player.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                        catch (NullPointerException e) {
                            e.printStackTrace();
                            player.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstrike> <Spieler>!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstrike> <Spieler>!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            } else {
                sender.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        } else {
            assert false;
            try {
                if (args.length == 1) {
                    Player t = MarioMain.getInstance().getServer().getPlayer(args[0].replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
                            switch (args[0].toLowerCase()) {
                                case "drop" -> {
                                    for (int i = 0; i <= 39; i++) {
                                        if (t.getInventory().getItem(i) != Item.get(BlockID.AIR)) {
                                            t.dropItem(t.getInventory().getItem(i));
                                            t.getInventory().clear(i, true);
                                        }
                                    }

                                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde erfolgreich mit Itemdrop getrollt!");
                                }
                                case "damage", "dmg" -> {
                                    if (!(t.getGamemode() == 1) && !(t.getGamemode() == 3)) {
                                        boolean hadFly = false;

                                        if (MarioMain.hasFly(t)) {
                                            t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, false);
                                            t.getAdventureSettings().update();
                                            hadFly = true;
                                        }

                                        t.getLevel().getGameRules().setGameRule(GameRule.FALL_DAMAGE, true);

                                        t.fall(5.0F);

                                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde erfolgreich mit Damage getrollt!");

                                        t.getLevel().getGameRules().setGameRule(GameRule.FALL_DAMAGE, false);

                                        if (hadFly) {
                                            t.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                                            t.getAdventureSettings().update();
                                        }
                                    } else {
                                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " kann keinen Schaden bekommen!");
                                    }
                                }
                                case "tnt" -> {
                                    if (MarioMain.getInstance().getServer().getPluginManager().getPlugin("MobPlugin") != null) {
                                        MarioMain.getInstance().getServer().dispatchCommand(MarioMain.getInstance().getServer().getConsoleSender(), "summon primed_tnt " + t.getName());

                                        sender.sendMessage(MarioMain.getPrefix() + "TNT bei " + t.getName() + " gespawnt!");
                                    }
                                    else {
                                        sender.sendMessage(MarioMain.getPrefix() + "Plugin \"MobPlugin\" ist nicht installiert! /troll tnt geht leider nicht.");
                                    }
                                }
                                case "pumpkin", "pk", "jumpscare", "js" -> {
                                    t.getInventory().setHelmet(Item.get(-155));

                                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " hat evtl. einen Jumpscare bekommen!");
                                }
                                case "inventory", "inv" -> {
                                    if (MarioMain.getInstance().invTroll.contains(t)) {
                                        MarioMain.getInstance().invTroll.remove(t);

                                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf nun wieder sein Inventar benutzen!");
                                    }
                                    else {
                                        MarioMain.getInstance().invTroll.add(t);

                                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf nun nicht mehr sein Inventar benutzen!");
                                    }
                                }
                                case "move" -> {
                                    if (MarioMain.getInstance().moveTroll.contains(t)) {
                                        MarioMain.getInstance().moveTroll.remove(t);

                                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf sich nun wieder bewegen!");
                                    }
                                    else {
                                        MarioMain.getInstance().moveTroll.add(t);

                                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf sich nun nicht mehr bewegen!");
                                    }
                                }
                                case "thunderstrike", "ts", "strike" -> {
                                    CompoundTag nbt = new CompoundTag()
                                            .putList(new ListTag<DoubleTag>("Pos").add(new DoubleTag("", t.getX()))
                                                    .add(new DoubleTag("", t.getY())).add(new DoubleTag("", t.getZ())))
                                            .putList(new ListTag<DoubleTag>("Motion").add(new DoubleTag("", 0))
                                                    .add(new DoubleTag("", 0)).add(new DoubleTag("", 0)))
                                            .putList(new ListTag<FloatTag>("Rotation").add(new FloatTag("", 0))
                                                    .add(new FloatTag("", 0)));

                                    EntityLightning bolt = new EntityLightning(t.getChunk(), nbt);
                                    LightningStrikeEvent event = new LightningStrikeEvent(t.getLevel(), bolt);

                                    MarioMain.getInstance().getServer().getPluginManager().callEvent(event);

                                    sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " hat einen Schlag!");
                                }
                                default -> sender.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstrike> <Spieler>!");
                            }
                        }
                        else {
                            sender.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage(MarioMain.getPrefix() + "Dieser Spieler existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstrike> <Spieler>!");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                sender.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstrike> <Spieler>!");
            }
        }
        return false;
    }
}
