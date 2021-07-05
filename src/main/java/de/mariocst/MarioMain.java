package de.mariocst;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.command.CommandMap;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Sound;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.scheduler.ServerScheduler;
import cn.nukkit.utils.Config;
import de.mariocst.AntiCheat.AntiCheatAPI;
import de.mariocst.AntiCheat.Cheat.*;
import de.mariocst.AntiCheat.Cheat.inventory.InvalidItemEnchantmentCheckThread;
import de.mariocst.AntiCheat.Config.*;
import de.mariocst.AntiCheat.Event.Listener.EventListener;
import de.mariocst.Commands.Announcements.*;
import de.mariocst.Commands.Chat.*;
import de.mariocst.Commands.Inventory.*;
import de.mariocst.Commands.Others.*;
import de.mariocst.Commands.Player.*;
import de.mariocst.Commands.Player.Movement.*;
import de.mariocst.Commands.Report.*;
import de.mariocst.Commands.Send.*;
import de.mariocst.Commands.Server.*;
import de.mariocst.Commands.Setter.*;
import de.mariocst.Commands.World.*;
import de.mariocst.Config.PlayerIllegalItems;
import de.mariocst.Forms.FormListener;
import de.mariocst.Forms.FormTroll;
import de.mariocst.Listeners.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarioMain extends PluginBase implements AntiCheatAPI {

    private static MarioMain instance;

    private static String prefix = "§8[§6marioCST.de§8] §b";

    private static MasterConfig masterConfig;
    private PlayerCheatRecord playerCheatRecord;
    private PlayerIllegalItems playerIllegalItems;
    private static PlayerIllegalItems playerIllegalItemsS;

    public static HashMap<String, Report> reportThread = new HashMap<>();
    public static HashMap<String, AntiCheat.CheatType> reportPlayer = new HashMap<>();

    public List<Player> invTroll = new ArrayList<>();
    public List<Player> moveTroll = new ArrayList<>();

    public List<Player> staffChat = new ArrayList<>();

    public List<Player> muted = new ArrayList<>();

    @Getter
    public FormTroll formTroll;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        register();
        initConfig();

        log("marioCST's PlugIn geladen!");
    }

    @Override
    public void onDisable() {
        log("marioCST's PlugIn entladen!");
    }

    public void log(String text) {
        getLogger().info(getPrefix() + text);
    }

    public void critical(String text) {
        getLogger().critical(getPrefix() + text);
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
        playerCheatRecord = new PlayerCheatRecord(new Config(this.getDataFolder() + "/record.yml", Config.YAML).getRootSection());
        playerIllegalItems = new PlayerIllegalItems(new Config(this.getDataFolder() + "/bannedIllegalPlayers.yml", Config.YAML).getRootSection());
    }

    @Override
    public void addRecord(Player player, AntiCheat.CheatType cheatType) {
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
        commandMap.register("mute", new MuteCommand(this));

        //Inventory
        commandMap.register("clearenderchest", new ClearEnderchestCommand(this));
        commandMap.register("clearinventory", new ClearInventoryCommand(this));
        commandMap.register("giveitems", new GiveItemsCommand(this));
        commandMap.register("id", new IDCommand(this));
        if (this.getServer().getPluginManager().getPlugin("FakeInventories") != null) {
            commandMap.register("invsee", new InvseeCommand(this));
        }
        else {
            critical("Plugin \"FakeInventories\" wurde nicht gefunden! Invsee wird deaktiviert! Download: https://ci.opencollab.dev//job/NukkitX/job/FakeInventories/job/master/");
        }
        commandMap.register("tnt", new TNTCommand(this));

        // Others
        commandMap.register("date", new DateCommand(this));
        commandMap.register("discord", new DiscordCommand(this));
        commandMap.register("lol", new LolCommand(this));

        // Player
        commandMap.register("die", new DieCommand(this));
        commandMap.register("getgamemode", new GetGamemodeCommand(this));
        commandMap.register("dumb", new DumbCommand(this));
        commandMap.register("gm", new GMCommand(this));
        commandMap.register("heal", new HealCommand(this));
        commandMap.register("near", new NearCommand(this));
        commandMap.register("nick", new NickCommand(this));
        commandMap.register("realname", new RealnameCommand(this));
        commandMap.register("size", new SizeCommand(this));
        commandMap.register("skin", new SkinCommand(this));
        commandMap.register("troll", new TrollCommand(this));
        commandMap.register("unnick", new UnnickCommand(this));
            // Movement
            commandMap.register("climb", new ClimbCommand(this));
            commandMap.register("fly", new FlyCommand(this));
            commandMap.register("freeze", new FreezeCommand(this));
            commandMap.register("speed", new SpeedCommand(this));

        //Report
        commandMap.register("marioacreport", new MarioACReportCommand(this));
        commandMap.register("marioreport", new MarioReportCommand(this));

        // Send
        commandMap.register("sendactionbar", new SendActionbarCommand(this));
        commandMap.register("sendmessage", new SendMessageCommand(this));
        commandMap.register("sendtitle", new SendTitleCommand(this));

        // Server
        commandMap.register("kickall", new KickAllCommand(this));
        commandMap.register("staffchat", new StaffChatCommand(this));
        commandMap.register("tps", new TPSCommand(this));
        if (this.getServer().getPluginManager().getPlugin("PlotSquared") != null) {
            commandMap.register("rand", new RandCommand(this));
            commandMap.register("wand", new WandCommand(this));
        }
        else {
            critical("Plugin \"PlotSquared\" wurde nicht gefunden! Rand und Wand wird deaktiviert! Download: https://cloudburstmc.org/resources/plotsquared.31/");
        }

        // Setter
        commandMap.register("setlink", new SetLinkCommand(this));

        // World
        commandMap.register("day", new DayCommand(this));
        commandMap.register("getpos", new GetPosCommand(this));
        commandMap.register("night", new NightCommand(this));


        // Events/Listener
        PluginManager manager = this.getServer().getPluginManager();

        manager.registerEvents(new AchievementListener(), this);
        manager.registerEvents(new ChatListener(), this);
        manager.registerEvents(new EventListener(), this);
        manager.registerEvents(new FormListener(), this);
        manager.registerEvents(new HungerListener(), this);
        manager.registerEvents(new InventoryListener(), this);
        manager.registerEvents(new MoveListener(), this);
        manager.registerEvents(new UIListener(), this);

        if (this.getServer().getPluginManager().getPlugin("PlotSquared") != null) {
            manager.registerEvents(new RandListener(), this);
            manager.registerEvents(new WandListener(), this);
        }


        // Scheduler
        ServerScheduler scheduler = this.getServer().getScheduler();

        scheduler.scheduleRepeatingTask(new InvalidItemEnchantmentCheckThread(), 10);


        // Form Windows
        this.formTroll = new FormTroll();

        if (this.getServer().getPluginManager().getPlugin("MobPlugin") == null) {
            critical("Plugin \"MobPlugin\" wurde nicht gefunden! Troll im Modus TNT wird deaktiviert! Download: https://cloudburstmc.org/resources/mobplugin.3/");
        }
    }

    public static MarioMain getInstance() {
        return instance;
    }

    @Override
    public MasterConfig getMasterConfig() {
        return masterConfig;
    }

    public static void addIllegalPlayer(Player player) {
        playerIllegalItemsS.addIllegalPlayer(player);
    }

    public static void unknownPlayer(CommandSender sender) {
        sender.sendMessage(MarioMain.getPrefix() + "Unbekannter Spieler!");

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getLevel().addSound(player.getLocation(), Sound.RANDOM_ANVIL_LAND);
        }
    }

    public static boolean hasFly(Player player) {
        return player.getAdventureSettings().get(AdventureSettings.Type.ALLOW_FLIGHT);
    }
}
