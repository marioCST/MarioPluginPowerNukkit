package de.mariocst.Timer;

import cn.nukkit.Player;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.mariocst.MarioMain;

import java.awt.*;

public class Timer {
    private ConfigSection config;

    private boolean isEmpty;

    private boolean running;
    private int seconds;
    private int minutes;
    private int hours;

    private static Timer timer;

    public Timer(ConfigSection configSection) {
        this.config = configSection;
        this.isEmpty = config.isEmpty();
        timer = this;

        this.running = false;

        init();
        run();
    }

    private void init() {
        if (!isEmpty) {
            seconds = config.getInt("seconds");
            minutes = config.getInt("minutes");
            hours = config.getInt("hours");
        } else {
            spawnDefaultConfig();
        }
    }

    private void spawnDefaultConfig() {
        seconds = 0;
        minutes = 0;
        hours = 0;
        save();
    }

    public static Timer getTimer() {
        return timer;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int time) {
        this.seconds = time;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void sendActionBar() {

        for (Player player: MarioMain.getInstance().getServer().getOnlinePlayers().values()) {

            if (!isRunning()) {
                player.sendActionBar("§4Timer ist pausiert");
                continue;
            }

            String secondsString = String.valueOf(getSeconds());
            String minutesString = String.valueOf(getMinutes());
            String hoursString = String.valueOf(getHours());

            if (getSeconds() < 10) {
                secondsString = "0" + getSeconds();
            }

            if (minutes < 10) {
                minutesString = "0" + getMinutes();
            }

            if (hours < 10) {
                hoursString = "0" + getHours();
            }

            player.sendActionBar("§6§l" + hoursString + ":" + minutesString + ":" + secondsString);
        }
    }

    public void save() {
        try {
            config.put("seconds", seconds);
            config.put("minutes", minutes);
            config.put("hours", hours);
            Config c = new Config(MarioMain.getInstance().getDataFolder() + "/timer.yml", Config.YAML);
            c.setAll(config);
            c.save();
        } catch (NullPointerException e) {
            spawnDefaultConfig();
            save();
        }
    }

    private void run() {
        new NukkitRunnable() {
            @Override
            public void run() {

                sendActionBar();

                if (!isRunning()) {
                    return;
                }

                setSeconds(getSeconds() + 1);

                if (getSeconds() == 60) {
                    setSeconds(0);
                    setMinutes(getMinutes() + 1);
                }

                if (getMinutes() == 60) {
                    setMinutes(0);
                    setHours(getHours() + 1);
                }

                if (getSeconds() > 60) {
                    setMinutes(getMinutes() + getSeconds() / 60);
                    setSeconds(getSeconds() % 60);
                }

                if (getMinutes() > 60) {
                    setHours(getHours() + getMinutes() / 60);
                    setMinutes(getMinutes() % 60);
                }
            }
        }.runTaskTimer(MarioMain.getInstance(), 20, 20);
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
