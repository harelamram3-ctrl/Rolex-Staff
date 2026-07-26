package com.rolexnetwork.staff.managers;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FreezeManager {

    // רשימה של כל ה-UUIDs של השחקנים שמוקפאים כרגע
    private final Set<UUID> frozenPlayers = new HashSet<>();

    // האם השחקן מוקפא?
    public boolean isFrozen(Player player) {
        return frozenPlayers.contains(player.getUniqueId());
    }

    // הקפאה או ביטול הקפאה (Toggle)
    public boolean toggleFreeze(Player player) {
        if (isFrozen(player)) {
            frozenPlayers.remove(player.getUniqueId());
            return false; // מוקפא לבאטל (חופשי)
        } else {
            frozenPlayers.add(player.getUniqueId());
            return true; // כעת מוקפא
        }
    }
}
