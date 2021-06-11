package de.mariocst.AntiCheat.Event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;
import de.mariocst.AntiCheat.Cheat.AntiCheat;

public class CheckCheatEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private AntiCheat.CheatType cheatType;

    public CheckCheatEvent(Player player, AntiCheat.CheatType cheatType) {
        this.player = player;
        this.cheatType = cheatType;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

    public AntiCheat.CheatType getCheatType() {
        return cheatType;
    }
}
