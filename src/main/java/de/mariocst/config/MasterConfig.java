package de.mariocst.config;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.mariocst.MarioMain;

public class MasterConfig {
    private static MasterConfig masterConfig;

    private final ConfigSection config;

    private final boolean isEmpty;

    private String prefix;
    private String link;

    public MasterConfig(ConfigSection configSection) {
        masterConfig = this;
        this.config = configSection;
        this.isEmpty = this.config.isEmpty();
        this.init();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public static MasterConfig getMasterConfig() {
        return masterConfig;
    }

    private void init() {
        if (!this.isEmpty) {
            this.prefix = this.config.getString("prefix");
            this.link = this.config.getString("link");
            MarioMain.setPrefix(this.prefix.replaceAll("'''", ""));
        }
        else {
            this.spawnDefaultConfig();
        }
    }

    private void spawnDefaultConfig() {
        this.prefix = "§8[§6marioCST.de§8] §b";
        this.link = "Undefined";
        this.save();
    }

    public void save() {
        try {
            this.config.put("prefix", this.prefix.replaceAll("'''", ""));
            this.config.put("link", this.link);
            MarioMain.setPrefix(this.prefix.replaceAll("'''", ""));
            Config c = new Config(MarioMain.getInstance().getDataFolder() + "/config.yml", Config.YAML);
            c.setAll(this.config);
            c.save();
        }
        catch (NullPointerException e) {
            this.spawnDefaultConfig();
            this.save();
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
