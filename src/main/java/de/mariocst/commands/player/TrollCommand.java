package de.mariocst.commands.player;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityPrimedTNT;
import cn.nukkit.entity.weather.EntityLightning;
import cn.nukkit.event.entity.EntityExplosionPrimeEvent;
import cn.nukkit.event.weather.LightningStrikeEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Explosion;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Sound;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import de.mariocst.MarioMain;

public class TrollCommand extends Command {
    public TrollCommand() {
        super("troll", "Trolle jemanden!", "troll");
        this.setPermission("mario.troll");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.troll") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                try {
                    if (args.length == 0) {
                        MarioMain.getInstance().getFormTroll().openTroll(player);
                    }
                    else if (args.length >= 2) {
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
                                        }
                                        else {
                                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " kann keinen Schaden bekommen!");
                                        }
                                    }
                                    case "tnt" -> {
                                        if (args.length == 3) {
                                            try {
                                                int multiplier = Integer.parseInt(args[2]);

                                                if (multiplier > 16) {
                                                    player.sendMessage(MarioMain.getPrefix() + "Bitte nutze eine kleinere Zahl!");
                                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                                }
                                                else if (multiplier <= 0){
                                                    player.sendMessage(MarioMain.getPrefix() + "Bitte nutze eine gr????ere Zahl!");
                                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                                }
                                                else {
                                                    double force = 4.0D * multiplier;

                                                    EntityPrimedTNT tnt = new EntityPrimedTNT(t.getChunk(), Entity.getDefaultNBT(player.getPosition()), t);

                                                    EntityExplosionPrimeEvent event = new EntityExplosionPrimeEvent(tnt, force);
                                                    event.setForce(force);

                                                    MarioMain.getInstance().getServer().getPluginManager().callEvent(event);
                                                    if (!event.isCancelled()) {
                                                        Explosion explosion = new Explosion(t.getPosition(), event.getForce(), tnt);

                                                        if (event.isBlockBreaking()) {
                                                            explosion.explodeA();
                                                        }

                                                        explosion.explodeB();
                                                    }

                                                    player.sendMessage(MarioMain.getPrefix() + "BOOM! TNT bei " + t.getName() + " gespawnt mit der St??rke " + multiplier + " (" + force + ")!");
                                                }
                                            }
                                            catch (NullPointerException e) {
                                                player.sendMessage(MarioMain.getPrefix() + "Bitte nutze eine Zahl!");
                                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                            }
                                        }
                                        else {
                                            EntityPrimedTNT tnt = new EntityPrimedTNT(t.getChunk(), Entity.getDefaultNBT(t.getPosition()), t);
                                            tnt.spawnTo(t);

                                            EntityExplosionPrimeEvent event = new EntityExplosionPrimeEvent(tnt, 4.0D);

                                            MarioMain.getInstance().getServer().getPluginManager().callEvent(event);

                                            player.sendMessage(MarioMain.getPrefix() + "BOOM! TNT bei " + t.getName() + " gespawnt!");
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
                                    case "thunderstruck", "ts", "struck" -> {
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
                                        player.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstruck> <Spieler>!");
                                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                    }
                                }
                            }
                            else {
                                player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                            }
                        }
                        catch (NullPointerException e) {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                        }
                    }
                    else {
                        player.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstruck> <Spieler>!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstruck> <Spieler>!");
                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                }
            }
            else {
                player.sendMessage(MarioMain.getPrefix() + "Keine Rechte!");
                player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
            }
        }
        else {
            try {
                if (args.length >= 2) {
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
                                    }
                                    else {
                                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " kann keinen Schaden bekommen!");
                                    }
                                }
                                case "tnt" -> {
                                    if (args.length == 3) {
                                        try {
                                            int multiplier = Integer.parseInt(args[2]);

                                            if (multiplier > 16) {
                                                sender.sendMessage(MarioMain.getPrefix() + "Bitte nutze eine kleinere Zahl!");
                                            }
                                            else if (multiplier <= 0){
                                                sender.sendMessage(MarioMain.getPrefix() + "Bitte nutze eine gr????ere Zahl!");
                                            }
                                            else {
                                                double force = 4.0D * multiplier;

                                                EntityPrimedTNT tnt = new EntityPrimedTNT(t.getChunk(), Entity.getDefaultNBT(t.getPosition()), t);

                                                EntityExplosionPrimeEvent event = new EntityExplosionPrimeEvent(tnt, force);
                                                event.setForce(force);

                                                MarioMain.getInstance().getServer().getPluginManager().callEvent(event);
                                                if (!event.isCancelled()) {
                                                    Explosion explosion = new Explosion(t.getPosition(), event.getForce(), tnt);

                                                    if (event.isBlockBreaking()) {
                                                        explosion.explodeA();
                                                    }

                                                    explosion.explodeB();
                                                }

                                                sender.sendMessage(MarioMain.getPrefix() + "BOOM! TNT bei " + t.getName() + " gespawnt mit der St??rke " + multiplier + " (" + force + ")!");
                                            }
                                        }
                                        catch (NullPointerException e) {
                                            sender.sendMessage(MarioMain.getPrefix() + "Bitte nutze eine Zahl!");
                                        }
                                    }
                                    else {
                                        EntityPrimedTNT tnt = new EntityPrimedTNT(t.getChunk(), Entity.getDefaultNBT(t.getPosition()), t);
                                        tnt.spawnTo(t);

                                        EntityExplosionPrimeEvent event = new EntityExplosionPrimeEvent(tnt, 4.0D);

                                        MarioMain.getInstance().getServer().getPluginManager().callEvent(event);

                                        sender.sendMessage(MarioMain.getPrefix() + "BOOM! TNT bei " + t.getName() + " gespawnt!");
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
                                case "thunderstruck", "ts", "struck" -> {
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
                                default -> sender.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstruck> <Spieler>!");
                            }
                        }
                        else {
                            sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage(MarioMain.getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstruck> <Spieler>!");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(MarioMain.getPrefix() + "/troll <drop|damage|tnt|pumpkin|inventory|move|thunderstruck> <Spieler>!");
            }
        }
        return false;
    }
}
