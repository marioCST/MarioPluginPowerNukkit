package de.mariocst.forms;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityPrimedTNT;
import cn.nukkit.entity.weather.EntityLightning;
import cn.nukkit.event.entity.EntityExplosionPrimeEvent;
import cn.nukkit.event.weather.LightningStrikeEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.item.Item;
import cn.nukkit.level.Explosion;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Sound;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import de.mariocst.forms.custom.CustomForm;
import de.mariocst.forms.simple.SimpleForm;
import de.mariocst.MarioMain;

import java.util.HashMap;

public class FormTroll {
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

    public void openTroll(Player player) {
        SimpleForm form = new SimpleForm.Builder("§cTroll",
                getNP("", "§aSuch dir eine Kategorie aus!"))
                .addButton(new ElementButton("§6Item Drop", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/itemphysics.png")), e -> this.openItemDropMenu(player))
                .addButton(new ElementButton("§6Damage", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/hitcolor.png")), e -> this.openDamageMenu(player))
                .addButton(new ElementButton("§6TNT", new ElementButtonImageData("path", "textures/blocks/tnt_side.png")), e -> this.openTNTMenu(player))
                .addButton(new ElementButton("§6Pumpkin", new ElementButtonImageData("path", "textures/blocks/pumpkin_face_off.png")), e -> this.openPumpkinMenu(player))
                .addButton(new ElementButton("§6Inventory", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/nickhider.png")), e -> this.openInventoryTrollMenu(player))
                .addButton(new ElementButton("§6Move", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/togglesprint.png")), e -> this.openMoveMenu(player))
                .addButton(new ElementButton("§6Lightning Struck", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/lightningstrike.png")), e -> this.openLightningStruckMenu(player))
                .build();
        form.send(player);
    }

    public void openItemDropMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6Item Drop")
                .addElement(new ElementInput("Spieler", player.getName()))
                .onSubmit((e, r) -> {
                    if (r.getInputResponse(0).isEmpty()) {
                        player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Spieler Namen ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }

                    Player t = MarioMain.getInstance().getServer().getPlayer(r.getInputResponse(0).replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
                            for (int i = 0; i <= 39; i++) {
                                if (t.getInventory().getItem(i) != Item.get(BlockID.AIR)) {
                                    t.dropItem(t.getInventory().getItem(i));
                                    t.getInventory().clear(i, true);
                                }
                            }

                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " wurde erfolgreich mit Itemdrop getrollt!");
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException n) {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                    }
                })
                .build();
        form.send(player);
    }

    public void openDamageMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6Damage")
                .addElement(new ElementInput("Spieler", player.getName()))
                .onSubmit((e, r) -> {
                    if (r.getInputResponse(0).isEmpty()) {
                        player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Spieler Namen ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }

                    Player t = MarioMain.getInstance().getServer().getPlayer(r.getInputResponse(0).replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
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
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException n) {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                    }
                })
                .build();
        form.send(player);
    }

    public void openTNTMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6TNT")
                .addElement(new ElementInput("Spieler", player.getName()))
                .addElement(new ElementInput("Stärke", "1"))
                .onSubmit((e, r) -> {
                    if (r.getInputResponse(0).isEmpty()) {
                        player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Spieler Namen ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }

                    Player t = MarioMain.getInstance().getServer().getPlayer(r.getInputResponse(0).replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
                            if (r.getInputResponse(1).isEmpty()) {
                                EntityPrimedTNT tnt = new EntityPrimedTNT(t.getChunk(), Entity.getDefaultNBT(t.getPosition()), t);
                                tnt.spawnTo(t);

                                EntityExplosionPrimeEvent event = new EntityExplosionPrimeEvent(tnt, 4.0D);

                                MarioMain.getInstance().getServer().getPluginManager().callEvent(event);

                                player.sendMessage(MarioMain.getPrefix() + "BOOM! TNT bei " + t.getName() + " gespawnt!");
                            }
                            else {
                                try {
                                    int multiplier = Integer.parseInt(r.getInputResponse(1));

                                    if (multiplier > 16) {
                                        player.sendMessage(MarioMain.getPrefix() + "Bitte nutze eine kleinere Zahl!");
                                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                    }
                                    else if (multiplier <= 0){
                                        player.sendMessage(MarioMain.getPrefix() + "Bitte nutze eine größere Zahl!");
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

                                        player.sendMessage(MarioMain.getPrefix() + "BOOM! TNT bei " + t.getName() + " gespawnt mit der Stärke " + multiplier + " (" + force + ")!");
                                    }
                                }
                                catch (NullPointerException f) {
                                    player.sendMessage(MarioMain.getPrefix() + "Bitte nutze eine Zahl!");
                                    player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                                }
                            }
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException n) {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                    }
                })
                .build();
        form.send(player);
    }

    public void openPumpkinMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6Pumpkin")
                .addElement(new ElementInput("Spieler", player.getName()))
                .onSubmit((e, r) -> {
                    if (r.getInputResponse(0).isEmpty()) {
                        player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Spieler Namen ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }

                    Player t = MarioMain.getInstance().getServer().getPlayer(r.getInputResponse(0).replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
                            t.getInventory().setHelmet(Item.get(-155));

                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " hat einen Jumpscare bekommen!");
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException n) {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                    }
                })
                .build();
        form.send(player);
    }

    public void openInventoryTrollMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6Inventory")
                .addElement(new ElementInput("Spieler", player.getName()))
                .onSubmit((e, r) -> {
                    if (r.getInputResponse(0).isEmpty()) {
                        player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Spieler Namen ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }

                    Player t = MarioMain.getInstance().getServer().getPlayer(r.getInputResponse(0).replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
                            if (MarioMain.getInstance().invTroll.contains(t)) {
                                MarioMain.getInstance().invTroll.remove(t);

                                player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf nun wieder sein Inventar benutzen!");
                            }
                            else {
                                MarioMain.getInstance().invTroll.add(t);

                                player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf nun nicht mehr sein Inventar benutzen!");
                            }
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException n) {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                    }
                })
                .build();
        form.send(player);
    }

    public void openMoveMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6Move")
                .addElement(new ElementInput("Spieler", player.getName()))
                .onSubmit((e, r) -> {
                    if (r.getInputResponse(0).isEmpty()) {
                        player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Spieler Namen ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }

                    Player t = MarioMain.getInstance().getServer().getPlayer(r.getInputResponse(0).replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
                            if (MarioMain.getInstance().moveTroll.contains(t)) {
                                MarioMain.getInstance().moveTroll.remove(t);

                                player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf sich nun wieder bewegen!");
                            }
                            else {
                                MarioMain.getInstance().moveTroll.add(t);

                                player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " darf sich nun nicht mehr bewegen!");
                            }
                        }
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException n) {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                    }
                })
                .build();
        form.send(player);
    }

    public void openLightningStruckMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6Lightning Struck")
                .addElement(new ElementInput("Spieler", player.getName()))
                .onSubmit((e, r) -> {
                    if (r.getInputResponse(0).isEmpty()) {
                        player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Spieler Namen ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }

                    Player t = MarioMain.getInstance().getServer().getPlayer(r.getInputResponse(0).replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
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
                        else {
                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException n) {
                        player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + r.getInputResponse(0) + " existiert nicht!");
                    }
                })
                .build();
        form.send(player);
    }
}
