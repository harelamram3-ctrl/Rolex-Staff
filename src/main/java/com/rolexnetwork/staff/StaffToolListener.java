package com.rolexnetwork.staff.listeners;

import com.rolexnetwork.staff.gui.StaffMenu;
import com.rolexnetwork.staff.managers.StaffManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class StaffToolListener implements Listener {

    private final StaffManager staffManager;

    public StaffToolListener(StaffManager staffManager) {
        this.staffManager = staffManager;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player staff = event.getPlayer();

        // בדיקה אם איש הצוות נמצא ב-Staff Mode
        if (!staffManager.isInStaffMode(staff)) {
            return;
        }

        // בדיקה שהאינטראקציה היא מול שחקן אחר
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }

        Player target = (Player) event.getRightClicked();
        ItemStack handItem = staff.getInventory().getItemInMainHand();

        if (handItem == null || handItem.getType() == Material.AIR) {
            return;
        }

        // 1. אולר שוויצרי (Nether Star) - פתיחת תפריט ניהול מהיר
        if (handItem.getType() == Material.NETHER_STAR) {
            event.setCancelled(true);
            StaffMenu.openPlayerMenu(staff, target);
        }

        // 2. כלי הקפאה (Ice) - הקפאת שחקן במקום
        if (handItem.getType() == Material.ICE) {
            event.setCancelled(true);
            staff.sendMessage(ChatColor.AQUA + "[RolexStaff] הקפאת את השחקן: " + ChatColor.WHITE + target.getName());
            target.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "הוקפאת על ידי איש צוות! אל תתנתק מהשרת!");
        }
    }
}
