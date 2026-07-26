package com.rolexnetwork.staff.listeners;

import com.rolexnetwork.staff.RolexStaff;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class GraveDiggerListener implements Listener {

    // "הפנקס": שמירת מיקום הבלוק + שם השחקן שבר אותו
    private final Map<String, String> blockHistory = new HashMap<>();

    // 1. כששחקן שובר בלוק - השרת רושם בפנקס
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        // יצירת מפתח ייחודי למיקום (X, Y, Z)
        String locationKey = block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ();
        
        // שמירת שם השחקן וסוג הבלוק שנשבר
        blockHistory.put(locationKey, player.getName() + " (בלוק: " + block.getType().name() + ")");
    }

    // 2. כשאיש צוות לוחץ עם מקל הקסם על בלוק
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player staff = event.getPlayer();

        // בדיקה שהלחיצה היא על בלוק
        if (event.getClickedBlock() == null) return;

        // בדיקה אם איש הצוות ב-Staff Mode
        boolean isInStaffMode = RolexStaff.getInstance().getStaffManager().isInStaffMode(staff);
        if (!isInStaffMode) return;

        ItemStack itemInHand = staff.getInventory().getItemInMainHand();

        // בדיקה אם ביד יש מקל Blaze Rod (מקל הבלש)
        if (itemInHand != null && itemInHand.getType() == Material.BLAZE_ROD) {
            event.setCancelled(true);

            Block block = event.getClickedBlock();
            String locationKey = block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ();

            // בדיקה אם הבלוק הזה רשום בפנקס
            if (blockHistory.containsKey(locationKey)) {
                String info = blockHistory.get(locationKey);
                staff.sendMessage(ChatColor.DARK_PURPLE + "[Grave Digger] " + ChatColor.LIGHT_PURPLE + "הבלוק הזה נהרס על ידי: " + ChatColor.WHITE + info);
            } else {
                staff.sendMessage(ChatColor.DARK_PURPLE + "[Grave Digger] " + ChatColor.GRAY + "אין מידע על הרס במיקום הזה.");
            }
        }
    }
}
