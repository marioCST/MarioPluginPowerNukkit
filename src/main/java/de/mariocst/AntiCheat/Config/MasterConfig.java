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

    public MasterConfig(ConfigSection configSection) {
        masterConfig = this;
        config = configSection;
        isEmpty = config.isEmpty();
        init();
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
            invalidItemEnchantmentCheck = config.getBoolean("invalidItemEnchantmentCheck");
            MarioMain.setPrefix(prefix);
        } else {
            spawnDefaultConfig();
        }
    }

    private void spawnDefaultConfig() {
        prefix = "'§8[§6marioCST.de§8] §b'";
        invalidItemEnchantmentCheck = true;
        save();
    }

    public void save() {
        try {
            config.put("prefix", prefix);
            config.put("invalidItemEnchantmentCheck", invalidItemEnchantmentCheck);
            MarioMain.setPrefix(prefix.replaceAll("'", ""));
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
