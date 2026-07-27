package com.rolexnetwork.staff.managers;

import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FreezeManager {

    private final Set<UUID> frozenPlayers = new HashSet<>();

    public boolean isFrozen(Player player) {
        return frozenPlayers.contains(player.getUniqueId());
    }

    public void toggleFreeze(Player target) {
        if (isFrozen(target)) {
            frozenPlayers.remove(target.getUniqueId());
            target.sendMessage("§a[RolexStaff] הופשרה ההקפאה שלך!");
        } else {
            frozenPlayers.add(target.getUniqueId());
            target.sendMessage("§c[RolexStaff] הוקפאת על ידי איש צוות! אל תתנתק!");
        }
    }
}
