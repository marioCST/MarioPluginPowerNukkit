package de.mariocst.AntiCheat.Config;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.mariocst.AntiCheat.Cheat.AntiCheat;
import de.mariocst.MarioMain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerCheatRecord {
    private ConfigSection config;

    public PlayerCheatRecord(ConfigSection configSection) {
        config = configSection;
    }

    public void addRecord(Player player, AntiCheat.CheatType cheatType) {
        int id = config.size() + 1;
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = format.format(date);
        config.put("#" + id + "|Time:" + time, "Player " + player.getName() + "(" + player.getLocation().toString() + ")[HP:" + player.getHealth() + "/" + player.getMaxHealth() + "] try cheating(Type:" + cheatType.getTypeName() + ").");
        save();
    }

    private void save() {
        Config c = new Config(MarioMain.getInstance().getDataFolder() + "/record.yml", Config.YAML);
        c.setAll(config);
        c.save();
    }
}
