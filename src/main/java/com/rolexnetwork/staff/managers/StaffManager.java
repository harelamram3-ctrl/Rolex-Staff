package com.rolexnetwork.staff.managers;

import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffManager {

    private final Set<UUID> staffModePlayers = new HashSet<>();

    public boolean isStaff(Player player) {
        return staffModePlayers.contains(player.getUniqueId()) || player.hasPermission("rolexstaff.use");
    }

    public boolean isInStaffMode(Player player) {
        return staffModePlayers.contains(player.getUniqueId());
    }

    public void toggleStaffMode(Player player) {
        if (isInStaffMode(player)) {
            staffModePlayers.remove(player.getUniqueId());
            player.sendMessage("§c[RolexStaff] יצאת ממצב Staff Mode.");
        } else {
            staffModePlayers.add(player.getUniqueId());
            player.sendMessage("§a[RolexStaff] נכנסת למצב Staff Mode!");
        }
    }
}
