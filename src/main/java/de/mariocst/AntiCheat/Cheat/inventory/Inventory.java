package de.mariocst.AntiCheat.Cheat.inventory;

import cn.nukkit.Player;
import de.mariocst.AntiCheat.Cheat.AntiCheat;

abstract class Inventory extends AntiCheat {
    Inventory(Player player) {
        super(player);
    }
}
