package com.rolexnetwork.staff.listeners;

import com.rolexnetwork.staff.managers.StaffManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StaffToolListener implements Listener {

    private final StaffManager staffManager;

    public StaffToolListener(StaffManager staffManager) {
        this.staffManager = staffManager;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player staff = event.getPlayer();

        if (!staffManager.isStaff(staff)) return;
        if (!(event.getRightClicked() instanceof Player target)) return;

        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "Staff Menu: " + target.getName());

        ItemStack freezeItem = new ItemStack(Material.PACKED_ICE);
        ItemMeta freezeMeta = freezeItem.getItemMeta();
        if (freezeMeta != null) {
            freezeMeta.setDisplayName(ChatColor.AQUA + "Quantum Freeze");
            freezeItem.setItemMeta(freezeMeta);
        }
        gui.setItem(11, freezeItem);

        ItemStack invItem = new ItemStack(Material.CHEST);
        ItemMeta invMeta = invItem.getItemMeta();
        if (invMeta != null) {
            invMeta.setDisplayName(ChatColor.GOLD + "Inspect Inventory");
            invItem.setItemMeta(invMeta);
        }
        gui.setItem(13, invItem);

        ItemStack tpItem = new ItemStack(Material.ENDER_PEARL);
        ItemMeta tpMeta = tpItem.getItemMeta();
        if (tpMeta != null) {
            tpMeta.setDisplayName(ChatColor.GREEN + "Teleport to Player");
            tpItem.setItemMeta(tpMeta);
        }
        gui.setItem(15, tpItem);

        staff.openInventory(gui);
        staff.sendMessage(ChatColor.GOLD + "[RolexStaff] " + ChatColor.YELLOW + "נפתח תפריט ניהול עבור " + target.getName());
    }
}
