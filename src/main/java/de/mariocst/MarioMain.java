package de.mariocst;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.command.CommandMap;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.Config;
import de.mariocst.commands.announcements.*;
import de.mariocst.commands.chat.*;
import de.mariocst.commands.inventory.*;
import de.mariocst.commands.others.*;
import de.mariocst.commands.player.*;
import de.mariocst.commands.player.movement.*;
import de.mariocst.commands.send.*;
import de.mariocst.commands.server.*;
import de.mariocst.commands.setter.*;
import de.mariocst.commands.world.*;
import de.mariocst.config.MasterConfig;
import de.mariocst.forms.FormListener;
import de.mariocst.forms.FormTimer;
import de.mariocst.forms.FormTroll;
import de.mariocst.listeners.*;
import de.mariocst.timer.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarioMain extends PluginBase {
    private static MarioMain instance;

    private static String prefix;

    private static MasterConfig masterConfig;
    private static Timer timerConfig;

    public List<Player> invTroll = new ArrayList<>();
    public List<Player> moveTroll = new ArrayList<>();

    public List<Player> staffChat = new ArrayList<>();

    public List<Player> muted = new ArrayList<>();

    public List<Player> airStuckHack = new ArrayList<>();
    public List<Player> noFallHack = new ArrayList<>();

    public Map<Player, Location> freezed = new HashMap<>();

    @Getter
    public FormTroll formTroll;

    @Getter
    public FormTimer formTimer;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        this.initConfig();

        setPrefix(getMasterConfig().getPrefix());

        this.register();

        this.log("marioCST's Plugin geladen!");
    }

    @Override
    public void onDisable() {
        this.saveConfigs();

        this.log("marioCST's Plugin entladen!");
    }

    public void log(String text) {
        this.getLogger().info(getPrefix() + text);
    }

    public void critical(String text) {
        this.getLogger().critical(getPrefix() + text);
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        MarioMain.prefix = prefix;
    }

    private void initConfig() {
        Config c = new Config(this.getDataFolder() + "/config.yml", Config.YAML);
        masterConfig = new MasterConfig(c.getRootSection());
        if (masterConfig.isEmpty()) {
            this.getLogger().warning("Die Config ist leer!");
        }

        if (!masterConfig.isTimer()) return;
        Config timer = new Config(this.getDataFolder() + "/timer.yml", Config.YAML);
        timerConfig = new Timer(timer.getRootSection());
        if (timerConfig.isEmpty()) {
            this.getLogger().warning("Die Timer Config ist leer!");
        }
    }

    public void saveConfigs() {
        masterConfig.save();
        if (masterConfig.isTimer()) timerConfig.save();
    }

    public void reloadConfigs() {
        Config c = new Config(this.getDataFolder() + "/config.yml", Config.YAML);
        masterConfig = new MasterConfig(c.getRootSection());

        Config timer = new Config(this.getDataFolder() + "/timer.yml", Config.YAML);
        timerConfig = new Timer(timer.getRootSection());
    }

    private void register() {
        CommandMap commandMap = getServer().getCommandMap();

        // Announcements
        commandMap.register("broadcast5", new Custom());
        commandMap.register("announcekick", new Kick());
        commandMap.register("announcereload", new Reload());
        commandMap.register("announcerestart", new Restart());
        commandMap.register("announcestop", new Stop());

        // Chat
        commandMap.register("broadcast", new BroadcastCommand());
        commandMap.register("chatclear", new ChatClearCommand());
        commandMap.register("mute", new MuteCommand());

        //Inventory
        commandMap.register("clearenderchest", new ClearEnderchestCommand());
        commandMap.register("clearinventory", new ClearInventoryCommand());
        commandMap.register("giveitems", new GiveItemsCommand());
        commandMap.register("id", new IDCommand());
        if (this.getServer().getPluginManager().getPlugin("FakeInventories") != null) {
            commandMap.register("invsee", new InvseeCommand());
        }
        else {
            this.critical("Plugin \"FakeInventories\" wurde nicht gefunden! Invsee wird deaktiviert! Download: https://ci.opencollab.dev//job/NukkitX/job/FakeInventories/job/master/");
        }
        commandMap.register("tnt", new TNTCommand());

        // Others
        commandMap.register("date", new DateCommand());
        commandMap.register("discord", new DiscordCommand());

        // Player
        commandMap.register("die", new DieCommand());
        commandMap.register("getgamemode", new GetGamemodeCommand());
        commandMap.register("gm", new GMCommand());
        commandMap.register("hacktroll", new HackTrollCommand());
        commandMap.register("heal", new HealCommand());
        commandMap.register("near", new NearCommand());
        commandMap.register("nick", new NickCommand());
        commandMap.register("nightvision", new NightvisionCommand());
        commandMap.register("realname", new RealnameCommand());
        commandMap.register("size", new SizeCommand());
        commandMap.register("skin", new SkinCommand());
        commandMap.register("troll", new TrollCommand());
        commandMap.register("unnick", new UnnickCommand());
            // Movement
            commandMap.register("climb", new ClimbCommand());
            commandMap.register("fly", new FlyCommand());
            commandMap.register("freeze", new FreezeCommand());
            commandMap.register("scaffold", new ScaffoldCommand());
            commandMap.register("speed", new SpeedCommand());

        // Send
        commandMap.register("sendactionbar", new SendActionbarCommand());
        commandMap.register("sendmessage", new SendMessageCommand());
        commandMap.register("sendtitle", new SendTitleCommand());

        // Server
        commandMap.register("configuration", new ConfigurationCommand());
        commandMap.register("kickall", new KickAllCommand());
        commandMap.register("report", new ReportCommand());
        commandMap.register("staffchat", new StaffChatCommand());
        commandMap.register("timer", new TimerCommand());
        commandMap.register("tps", new TPSCommand());

        // Setter
        commandMap.register("setlink", new SetLinkCommand());
        commandMap.register("setprefix", new SetPrefixCommand());

        // World
        commandMap.register("day", new DayCommand());
        commandMap.register("getpos", new GetPosCommand());
        commandMap.register("night", new NightCommand());


        // Events/Listener
        PluginManager manager = this.getServer().getPluginManager();

        manager.registerEvents(new AchievementListener(), this);
        manager.registerEvents(new ChatListener(), this);
        manager.registerEvents(new FakeHackListener(), this);
        manager.registerEvents(new FormListener(), this);
        manager.registerEvents(new FreezedListener(), this);
        manager.registerEvents(new HungerListener(), this);
        manager.registerEvents(new InventoryListener(), this);
        manager.registerEvents(new MoveListener(), this);
        manager.registerEvents(new ScaffoldListener(), this);
        manager.registerEvents(new UIListener(), this);


        // Form Windows
        this.formTroll = new FormTroll();
        this.formTimer = new FormTimer();
    }

    public static MarioMain getInstance() {
        return instance;
    }

    public MasterConfig getMasterConfig() {
        return masterConfig;
    }

    public Timer getTimer() {
        return timerConfig;
    }

    public static boolean hasFly(Player player) {
        return player.getAdventureSettings().get(AdventureSettings.Type.ALLOW_FLIGHT);
    }
}
