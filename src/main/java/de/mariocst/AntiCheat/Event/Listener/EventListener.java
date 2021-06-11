package de.mariocst.AntiCheat.Event.Listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityTeleportEvent;
import java.util.HashSet;

public class EventListener implements Listener {
    private HashSet<String> teleport = new HashSet<>();

    @EventHandler
    public void onTeleport(EntityTeleportEvent event){
        if(event.getEntity() instanceof Player && !event.isCancelled()){
            teleport.add(event.getEntity().getName());
        }
    }
}
