package com.rolexnetwork.staff.listeners;

import com.rolexnetwork.staff.enums.StaffRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // בדיקה שהתפריט שנלחץ הוא תפריט ה-RolexStaff
        if (event.getView().getTitle().startsWith(ChatColor.DARK_GRAY + "RolexStaff - ")) {
            event.setCancelled(true); // ביטול היכולת לקחת חפצים מהתפריט!

            if (event.getCurrentItem() == null) return;

            Player staff = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            // חילוץ שם השחקן החשוד מתוך כותרת התפריט
            String targetName = event.getView().getTitle().replace(ChatColor.DARK_GRAY + "RolexStaff - ", "");
            Player target = Bukkit.getPlayer(targetName);

            if (target == null) {
                staff.sendMessage(ChatColor.RED + "[RolexStaff] השחקן כבר אינו מחובר לשרת.");
                staff.closeInventory();
                return;
            }

            StaffRank staffRank = StaffRank.getRank(staff);

            switch (clickedItem.getType()) {
                case PACKED_ICE: // Freeze
                    staff.sendMessage(ChatColor.AQUA + "[RolexStaff] שינית את מצב ההקפאה של " + target.getName());
                    target.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "הוקפאת על ידי איש צוות!");
                    staff.closeInventory();
                    break;

                case LEATHER_BOOTS: // Kick
                    target.kickPlayer(ChatColor.RED + "נזרקת מהשרת על ידי איש צוות (RolexStaff)");
                    staff.sendMessage(ChatColor.GREEN + "[RolexStaff] נזרק מהשרת: " + target.getName());
                    staff.closeInventory();
                    break;

                case REDSTONE_BLOCK: // Ban
                    if (staffRank.getLevel() >= 2) {
                        Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(
                                target.getName(),
                                ChatColor.RED + "נחסמת מהשרת על ידי צוות RolexNetWork!",
                                null,
                                staff.getName()
                        );
                        target.kickPlayer(ChatColor.RED + "נחסמת מהשרת על ידי איש צוות!");
                        staff.sendMessage(ChatColor.DARK_RED + "[RolexStaff] השחקן " + target.getName() + " נחסם בהצלחה!");
                    } else {
                        staff.sendMessage(ChatColor.RED + "[RolexStaff] אין לך הרשאה לחסום שחקנים!");
                    }
                    staff.closeInventory();
                    break;

                default:
                    break;
            }
        }
    }
}
