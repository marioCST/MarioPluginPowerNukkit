package de.mariocst.AntiCheat.Config;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.mariocst.MarioMain;

public class MasterConfig {
    private static MasterConfig masterConfig;

    private ConfigSection config;

    private Boolean isEmpty;

    private boolean invalidItemEnchantmentCheck;

    private String prefix;
    private String link;

    public MasterConfig(ConfigSection configSection) {
        masterConfig = this;
        config = configSection;
        isEmpty = config.isEmpty();
        init();
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
        if (!isEmpty) {
            prefix = config.getString("prefix");
            link = config.getString("link");
            invalidItemEnchantmentCheck = config.getBoolean("invalidItemEnchantmentCheck");
            MarioMain.setPrefix(prefix.replaceAll("'''", ""));
        } else {
            spawnDefaultConfig();
        }
    }

    private void spawnDefaultConfig() {
        prefix = "§8[§6marioCST.de§8] §b";
        link = "Undefined";
        invalidItemEnchantmentCheck = true;
        save();
    }

    public void save() {
        try {
            config.put("prefix", prefix.replaceAll("'''", ""));
            config.put("link", link);
            config.put("invalidItemEnchantmentCheck", invalidItemEnchantmentCheck);
            MarioMain.setPrefix(prefix.replaceAll("'''", ""));
            Config c = new Config(MarioMain.getInstance().getDataFolder() + "/config.yml", Config.YAML);
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

    public boolean getInvalidItemEnchantmentCheck() {
        return invalidItemEnchantmentCheck;
    }
}
