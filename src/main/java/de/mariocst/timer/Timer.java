package de.mariocst.timer;

import cn.nukkit.Player;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.mariocst.MarioMain;

public class Timer {
    private final ConfigSection config;

    private final boolean isEmpty;

    private boolean running;
    private int seconds;
    private int minutes;
    private int hours;

    public Timer(ConfigSection configSection) {
        this.config = configSection;
        this.isEmpty = this.config.isEmpty();

        this.running = false;

        this.init();
        this.run();
    }

    private void init() {
        if (!this.isEmpty) {
            this.seconds = this.config.getInt("seconds");
            this.minutes = this.config.getInt("minutes");
            this.hours = this.config.getInt("hours");
        }
        else {
            this.spawnDefaultConfig();
        }
    }

    private void spawnDefaultConfig() {
        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;
        this.save();
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

            if (!this.isRunning()) {
                player.sendActionBar("§4Timer ist pausiert");
                continue;
            }

            String secondsString = String.valueOf(this.getSeconds());
            String minutesString = String.valueOf(this.getMinutes());
            String hoursString = String.valueOf(this.getHours());

            if (this.getSeconds() < 10) {
                secondsString = "0" + this.getSeconds();
            }

            if (this.getMinutes() < 10) {
                minutesString = "0" + this.getMinutes();
            }

            if (this.getHours() < 10) {
                hoursString = "0" + this.getHours();
            }

            player.sendActionBar("§6§l" + hoursString + ":" + minutesString + ":" + secondsString);
        }
    }

    public void save() {
        try {
            this.config.put("seconds", this.seconds);
            this.config.put("minutes", this.minutes);
            this.config.put("hours", this.hours);
            Config c = new Config(MarioMain.getInstance().getDataFolder() + "/timer.yml", Config.YAML);
            c.setAll(this.config);
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
