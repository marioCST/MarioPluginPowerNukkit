package de.mariocst.Forms;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.entity.weather.EntityLightning;
import cn.nukkit.event.weather.LightningStrikeEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.item.Item;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Sound;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import de.mariocst.Forms.custom.CustomForm;
import de.mariocst.Forms.simple.SimpleForm;
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
                .addButton(new ElementButton("§6Lightningstrike", new ElementButtonImageData("url", "https://raw.githubusercontent.com/marioCST/MarioPlugInPowerNukkit/master/src/main/resources/textures/lightningstrike.png")), e -> this.openLightningstrikeMenu(player))
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
                            MarioMain.unknownPlayer(t);
                        }
                    }
                    catch (NullPointerException n) {
                        n.printStackTrace();
                        MarioMain.unknownPlayer(t);
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
                            MarioMain.unknownPlayer(t);
                        }
                    }
                    catch (NullPointerException n) {
                        n.printStackTrace();
                        MarioMain.unknownPlayer(t);
                    }
                })
                .build();
        form.send(player);
    }

    public void openTNTMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6TNT")
                .addElement(new ElementInput("Spieler", player.getName()))
                .onSubmit((e, r) -> {
                    if (r.getInputResponse(0).isEmpty()) {
                        player.sendMessage(MarioMain.getPrefix() + "Bitte gib einen Spieler Namen ein!");
                        player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
                    }

                    Player t = MarioMain.getInstance().getServer().getPlayer(r.getInputResponse(0).replaceAll("_", " ").replaceAll("\"", ""));

                    try {
                        if (t != null) {
                            if (MarioMain.getInstance().getServer().getPluginManager().getPlugin("MobPlugin") != null) {
                                dispatchCommand(consoleSender(), "summon primed_tnt " + t.getName());

                                player.sendMessage(MarioMain.getPrefix() + "TNT bei " + t.getName() + " gespawnt!");
                            }
                            else {
                                player.sendMessage(MarioMain.getPrefix() + "Plugin \"MobPlugin\" ist nicht installiert! /troll tnt geht leider nicht.");
                            }
                        }
                        else {
                            MarioMain.unknownPlayer(t);
                        }
                    }
                    catch (NullPointerException n) {
                        n.printStackTrace();
                        MarioMain.unknownPlayer(t);
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

                            player.sendMessage(MarioMain.getPrefix() + "Der Spieler " + t.getName() + " hat evtl. einen Jumpscare bekommen!");
                        }
                        else {
                            MarioMain.unknownPlayer(t);
                        }
                    }
                    catch (NullPointerException n) {
                        n.printStackTrace();
                        MarioMain.unknownPlayer(t);
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
                            MarioMain.unknownPlayer(t);
                        }
                    }
                    catch (NullPointerException n) {
                        n.printStackTrace();
                        MarioMain.unknownPlayer(t);
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
                            MarioMain.unknownPlayer(t);
                        }
                    }
                    catch (NullPointerException n) {
                        n.printStackTrace();
                        MarioMain.unknownPlayer(t);
                    }
                })
                .build();
        form.send(player);
    }

    public void openLightningstrikeMenu(Player player) {
        CustomForm form = new CustomForm.Builder("§6Lightningstrike")
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
                            MarioMain.unknownPlayer(t);
                        }
                    }
                    catch (NullPointerException n) {
                        n.printStackTrace();
                        MarioMain.unknownPlayer(t);
                    }
                })
                .build();
        form.send(player);
    }

    private void dispatchCommand(CommandSender sender, String commandLine) {
        MarioMain.getInstance().getServer().dispatchCommand(sender, commandLine);
    }

    private ConsoleCommandSender consoleSender() {
        return MarioMain.getInstance().getServer().getConsoleSender();
    }
}
