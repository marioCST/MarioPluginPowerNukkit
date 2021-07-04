package de.mariocst.Config;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.mariocst.MarioMain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerIllegalItems {
    private ConfigSection config;

    private Boolean isEmpty;

    public PlayerIllegalItems(ConfigSection configSection) {
        config = configSection;
        isEmpty = config.isEmpty();
        init();
    }

    private void init() {
        if (isEmpty) {
            spawnDefaultConfig();
        }
    }

    private void spawnDefaultConfig() {
        save();
    }

    public void addIllegalPlayer(Player player) {
        int id = config.size() + 1;
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = format.format(date);
        config.put("#" + id + "|Time:" + time, player.getName());
        save();
    }

    private void save() {
        try {
            Config c = new Config(MarioMain.getInstance().getDataFolder() + "/bannedIllegalPlayers.yml", Config.YAML);
            c.setAll(config);
            c.save();
        } catch (NullPointerException e) {
            spawnDefaultConfig();
            save();
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
