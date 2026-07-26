package com.rolexnetwork.staff.managers;

import com.rolexnetwork.staff.RolexStaff;
import com.rolexnetwork.staff.enums.StaffRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffManager {

    private final RolexStaff plugin;
    private final Set<UUID> inStaffMode = new HashSet<>();

    public StaffManager(RolexStaff plugin) {
        this.plugin = plugin;
    }

    public void toggleStaffMode(Player player) {
        StaffRank rank = StaffRank.getRank(player);

        if (rank == StaffRank.NONE) {
            player.sendMessage(ChatColor.RED + "[RolexStaff] אין לך הרשאה להשתמש במצב צוות!");
            return;
        }

        if (isInStaffMode(player)) {
            // יציאה ממצב צוות
            inStaffMode.remove(player.getUniqueId());
            player.setGameMode(GameMode.SURVIVAL);
            
            // ביטול מצב נעלם מול שאר השחקנים
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(plugin, player);
            }

            player.sendMessage(ChatColor.YELLOW + "[RolexStaff] יצאת ממצב Staff Mode.");
        } else {
            // כניסה למצב צוות
            inStaffMode.add(player.getUniqueId());
            player.setGameMode(GameMode.CREATIVE);

            // הפעלה אוטומטית של מצב נעלם (Vanish)
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.hasPermission("rolexstaff.level1")) {
                    online.hidePlayer(plugin, player);
                }
            }

            player.sendMessage(ChatColor.GREEN + "[RolexStaff] נכנסת למצב Staff Mode! רמה: " + ChatColor.GOLD + rank.getDisplayName());
            player.sendMessage(ChatColor.GRAY + "אחרים בשרת לא יכולים לראות אותך כעת.");
        }
    }

    public boolean isInStaffMode(Player player) {
        return inStaffMode.contains(player.getUniqueId());
    }
}
