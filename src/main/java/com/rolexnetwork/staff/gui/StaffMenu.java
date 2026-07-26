package com.rolexnetwork.staff.gui;

import com.rolexnetwork.staff.enums.StaffRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Collections;

public class StaffMenu {

    public static void openPlayerMenu(Player staff, Player target) {
        // יצירת חלון בגודל 27 משבצות (3 שורות)
        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "RolexStaff - " + target.getName());

        StaffRank staffRank = StaffRank.getRank(staff);

        // 1. הראש של השחקן המנוהל (מידע במרכז)
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        if (skullMeta != null) {
            skullMeta.setOwningPlayer(target);
            skullMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + target.getName());
            skullMeta.setLore(Collections.singletonList(
                ChatColor.GRAY + "חיים: " + ChatColor.GREEN + (int) target.getHealth() + "/20"
            ));
            skull.setItemMeta(skullMeta);
        }
        gui.setItem(13, skull);

        // 2. כפתור הקפאה (Freeze)
        gui.setItem(10, createGuiItem(Material.PACKED_ICE, ChatColor.AQUA + "הקפאת שחקן", ChatColor.GRAY + "לחץ כדי להקפיא/לבטל הקפאה"));

        // 3. כפתור Mute (השתקה)
        gui.setItem(11, createGuiItem(Material.PAPER, ChatColor.YELLOW + "השתקת שחקן (Mute)", ChatColor.GRAY + "מניעת דיבור בצ'אט"));

        // 4. כפתור Kick (הנפה מהשרת)
        gui.setItem(15, createGuiItem(Material.LEATHER_BOOTS, ChatColor.GOLD + "הנפת שחקן (Kick)", ChatColor.GRAY + "להוציא את השחקן מהשרת"));

        // 5. כפתור Ban (חסימה - זמין מדרגה 2 ומעלה!)
        if (staffRank.getLevel() >= 2) {
            gui.setItem(16, createGuiItem(Material.REDSTONE_BLOCK, ChatColor.RED + "חסימת שחקן (Ban)", ChatColor.GRAY + "חסימת השחקן מהשרת"));
        } else {
            gui.setItem(16, createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "חסימה (נעול)", ChatColor.RED + "אינך ברמה מספקת לחסום!"));
        }

        staff.openInventory(gui);
    }

    private static ItemStack createGuiItem(Material material, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(Collections.singletonList(lore));
            item.setItemMeta(meta);
        }
        return item;
    }
}
