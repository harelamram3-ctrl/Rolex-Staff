package com.rolexnetwork.staff.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class StaffItems {

    // 1. אולר שוויצרי - ניהול מהיר
    public static ItemStack getSwissKnife() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Rolex Swiss Knife");
            meta.setLore(Arrays.asList(
                ChatColor.GRAY + "קליק ימני על שחקן: פתיחת תפריט עונשים/ניהול",
                ChatColor.GRAY + "קליק שמאלי: שיגור 3 בלוקים מאחוריו"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }

    // 2. מקל חקירה - Grave Digger (שחזור גריף)
    public static ItemStack getGraveDiggerWand() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Grave Digger Wand");
            meta.setLore(Arrays.asList(
                ChatColor.GRAY + "קליק ימני על בלוק: הצגת הולוגרמות שחזור",
                ChatColor.GRAY + "של מי שהיה באזור ב-10 הדקות האחרונות"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }

    // 3. כלי הקפאה מהיר - Quantum Freeze
    public static ItemStack getFreezeTool() {
        ItemStack item = new ItemStack(Material.ICE);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Quantum Freeze Tool");
            meta.setLore(Arrays.asList(
                ChatColor.GRAY + "קליק ימני על שחקן: הקפאה מיידית בכלוב לייזר"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }
}
