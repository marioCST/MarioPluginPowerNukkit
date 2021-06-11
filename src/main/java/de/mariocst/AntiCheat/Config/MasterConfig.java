package de.mariocst.AntiCheat.Config;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.mariocst.MarioMain;

public class MasterConfig {
    private ConfigSection config;

    private Boolean isEmpty;

    private boolean invalidItemEnchantmentCheck;

    public MasterConfig(ConfigSection configSection) {
        config = configSection;
        isEmpty = config.isEmpty();
        init();
    }

    private void init() {
        if (!isEmpty) {
            invalidItemEnchantmentCheck = config.getBoolean("invalidItemEnchantmentCheck");
        } else {
            spawnDefaultConfig();
        }
    }

    private void spawnDefaultConfig() {
        MarioMain.getInstance().getLogger().notice("Erstelle default Config.");
        invalidItemEnchantmentCheck = true;
        save();
    }

    private void save() {
        try {
            config.put("invalidItemEnchantmentCheck", invalidItemEnchantmentCheck);
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
