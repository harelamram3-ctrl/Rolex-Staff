package com.rolexnetwork.staff.listeners;

import com.rolexnetwork.staff.RolexStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MindReadListener implements Listener {

    // 1. השרת מקשיב לכל הודעה שנכתבת בצ'אט
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();
        String message = event.getMessage();

        // 2. השרת עובר על כל השחקנים שמחוברים כרגע
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            
            // 3. השרת בודק: האם השחקן הזה הוא איש צוות במצב Staff Mode?
            boolean isInStaffMode = RolexStaff.getInstance().getStaffManager().isInStaffMode(onlinePlayer);

            if (isInStaffMode) {
                // 4. אם כן, השרת שולח לאיש הצוות הודעה מוצפנת בטורקיז עם מה שנכתב!
                onlinePlayer.sendMessage(ChatColor.DARK_AQUA + "[Mind Read] " 
                        + ChatColor.GRAY + sender.getName() + ": " 
                        + ChatColor.WHITE + message);
            }
        }
    }
}
