package de.mariocst.AntiCheat.Cheat.inventory;

import cn.nukkit.Player;

public class InvalidItemEnchantmentCheck extends Inventory {
    public InvalidItemEnchantmentCheck(Player player) {
        super(player);
    }

    @Override
    public CheatType getCheatType() {
        return CheatType.INVALID_ITEM;
    }

    @Override
    public boolean isCheat() {
        return false;
    }
}
