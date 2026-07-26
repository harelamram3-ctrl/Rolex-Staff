package com.rolexnetwork.staff.listeners;

import com.rolexnetwork.staff.managers.FreezeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FreezeListener implements Listener {

    private final FreezeManager freezeManager;

    public FreezeListener(FreezeManager freezeManager) {
        this.freezeManager = freezeManager;
    }

    // 1. מניעת תנועה של השחקן המוקפא
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (freezeManager.isFrozen(player)) {
            // אם הוא מנסה לשנות את המיקום שלו (X או Z או Y) - מבטלים את התנועה!
            if (event.getFrom().getX() != event.getTo().getX() || 
                event.getFrom().getZ() != event.getTo().getZ() ||
                event.getFrom().getY() != event.getTo().getY()) {
                
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "[RolexStaff] אתה מוקפא! אינך יכול לזוז.");
            }
        }
    }

    // 2. מניעת שבירת בלוקים בזמן הקפאה
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (freezeManager.isFrozen(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    // 3. מניעת הנחת בלוקים בזמן הקפאה
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (freezeManager.isFrozen(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    // 4. אם השחקן המוקפא מנסה להתנתק מהשרת (Ban אוטומטי על בריחה מבדיקה!)
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (freezeManager.isFrozen(player)) {
            // חסימת השחקן מהשרת
            Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(
                    player.getName(),
                    ChatColor.RED + "נחסמת מהשרת בעקבות התנתקות בזמן בדיקת צוות (Freeze Leave)!",
                    null,
                    "Console"
            );
        }
    }
}
