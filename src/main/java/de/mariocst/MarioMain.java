package de.mariocst;

import cn.nukkit.Player;
import cn.nukkit.command.CommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import de.mariocst.AntiCheat.AntiCheatAPI;
import de.mariocst.AntiCheat.Cheat.AntiCheat;
import de.mariocst.AntiCheat.Cheat.Report;
import de.mariocst.AntiCheat.Cheat.inventory.InvalidItemEnchantmentCheckThread;
import de.mariocst.AntiCheat.Config.MasterConfig;
import de.mariocst.AntiCheat.Config.PlayerCheatRecord;
import de.mariocst.AntiCheat.Event.Listener.EventListener;
import de.mariocst.Commands.Announcements.*;
import de.mariocst.Commands.Chat.*;
import de.mariocst.Commands.Inventory.*;
import de.mariocst.Commands.Player.*;
import de.mariocst.Commands.Report.*;
import de.mariocst.Commands.Send.*;
import de.mariocst.Commands.Server.*;
import de.mariocst.Commands.Setter.*;
import de.mariocst.Commands.Util.*;
import de.mariocst.Commands.World.*;
import de.mariocst.Config.PlayerIllegalItems;

import java.util.HashMap;
import java.util.Map;

public class MarioMain extends PluginBase implements AntiCheatAPI {
    public static MarioMain instance;

    private Map<String, String> lastMessagedPlayers = new HashMap<>();

    public static String PREFIX = "§8[§6marioCST.de§8] §b";

    private static MasterConfig masterConfig;
    private PlayerCheatRecord playerCheatRecord;
    private PlayerIllegalItems playerIllegalItems;
    private static PlayerIllegalItems playerIllegalItemsS;

    public static HashMap<String, Report> reportThread = new HashMap<>();
    public static HashMap<String, AntiCheat.CheatType> reportPlayer = new HashMap<>();

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        register();
        initConfig();

        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.getServer().getScheduler().scheduleRepeatingTask(new InvalidItemEnchantmentCheckThread(), 10);

        log("marioCST's PlugIn geladen!");
    }

    @Override
    public void onDisable() {
        log("marioCST's PlugIn entladen!");
    }

    public void log(String text) {
        getLogger().info(PREFIX + text);
    }

    private void initConfig() {
        Config c = new Config(this.getDataFolder() + "/config.yml", Config.YAML);
        masterConfig = new MasterConfig(c.getRootSection());
        if (masterConfig.isEmpty()) {
            this.getLogger().warning("Die Config ist leer!");
        }
        playerCheatRecord = new PlayerCheatRecord(new Config(this.getDataFolder() + "/record.yml", Config.YAML).getRootSection());
        playerIllegalItems = new PlayerIllegalItems(new Config(this.getDataFolder() + "/bannedIllegalPlayers.yml", Config.YAML).getRootSection());
    }

    @Override
    public void addRecord(Player player, de.mariocst.AntiCheat.Cheat.AntiCheat.CheatType cheatType) {
        playerCheatRecord.addRecord(player, cheatType);
    }

    private void register() {
        CommandMap commandMap = getServer().getCommandMap();

        // Announcements
        commandMap.register("broadcast5", new Custom(this));
        commandMap.register("announcekick", new Kick(this));
        commandMap.register("announcereload", new Reload(this));
        commandMap.register("announcerestart", new Restart(this));
        commandMap.register("announcestop", new Stop(this));

        // Chat
        commandMap.register("broadcast", new BroadcastCommand(this));
        commandMap.register("chatclear", new ChatClearCommand(this));

        //Inventory
        commandMap.register("clear", new ClearInventoryCommand(this));
        commandMap.register("invsee", new InvseeCommand(this));

        // Player
        commandMap.register("die", new DieCommand(this));
        commandMap.register("dumb", new DumbCommand(this));
        commandMap.register("fly", new FlyCommand(this));
        commandMap.register("freeze", new FreezeCommand(this));
        commandMap.register("gm", new GMCommand(this));
        commandMap.register("heal", new HealCommand(this));
        commandMap.register("nick", new NickCommand(this));
        commandMap.register("realname", new RealnameCommand(this)); //Broken
        commandMap.register("size", new SizeCommand(this));
        commandMap.register("skin", new SkinCommand(this));
        commandMap.register("speed", new SpeedCommand(this));
        commandMap.register("unnick", new UnnickCommand(this));

        //Report
        commandMap.register("marioacreport", new MarioACReportCommand(this));
        commandMap.register("marioreport", new MarioReportCommand(this));

        // Send
        commandMap.register("sendactionbar", new SendActionbarCommand(this));
        commandMap.register("sendmessage", new SendMessageCommand(this));
        commandMap.register("sendtitle", new SendTitleCommand(this));

        // Server
        commandMap.register("kickall", new KickAllCommand(this));
        commandMap.register("tps", new TPSCommand(this)); //Broken?

        // Setter
        commandMap.register("setlink", new SetLinkCommand(this));

        // Util
        commandMap.register("date", new DateCommand(this));
        commandMap.register("discord", new DiscordCommand(this));
        commandMap.register("lol", new LolCommand(this));
        commandMap.register("reply", new ReplyCommand(this));

        // World
        commandMap.register("day", new DayCommand(this));
        commandMap.register("night", new NightCommand(this));
    }

    public static MarioMain getInstance() {
        return instance;
    }

    @Override
    public MasterConfig getMasterConfig() {
        return masterConfig;
    }

    public Map<String, String> getLastMessagedPlayers() {
        return lastMessagedPlayers;
    }

    public static void addIllegalPlayer(Player player) {
        playerIllegalItemsS.addIllegalPlayer(player);
    }
}
