package de.mariocst.AntiCheat;

import cn.nukkit.Player;
import de.mariocst.AntiCheat.Cheat.AntiCheat;
import de.mariocst.AntiCheat.Config.MasterConfig;
import de.mariocst.MarioMain;

public interface AntiCheatAPI {
    static MarioMain getInstance() {
        return MarioMain.getInstance();
    }

    MasterConfig getMasterConfig();

    void addRecord(Player player, AntiCheat.CheatType cheatType);
}
