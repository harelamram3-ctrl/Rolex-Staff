package com.rolexnetwork.staff.listeners;

import com.rolexnetwork.staff.RolexStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().startsWith(ChatColor.DARK_RED + "Staff Menu: ")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            Player staff = (Player) event.getWhoClicked();
            String targetName = event.getView().getTitle().replace(ChatColor.DARK_RED + "Staff Menu: ", "");
            Player target = Bukkit.getPlayer(targetName);

            if (target == null) {
                staff.sendMessage("§cהשחקן אינו מחובר כעת.");
                staff.closeInventory();
                return;
            }

            switch (event.getCurrentItem().getType()) {
                case PACKED_ICE:
                    RolexStaff.getInstance().getFreezeManager().toggleFreeze(target);
                    staff.sendMessage("§e[RolexStaff] שינית את מצב ההקפאה של " + target.getName());
                    staff.closeInventory();
                    break;

                case CHEST:
                    staff.openInventory(target.getInventory());
                    break;

                case ENDER_PEARL:
                    staff.teleport(target.getLocation());
                    staff.sendMessage("§a[RolexStaff] השתגרת אל " + target.getName());
                    staff.closeInventory();
                    break;

                default:
                    break;
            }
        }
    }
}
