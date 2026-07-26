package com.rolexnetwork.staff.commands;

import com.rolexnetwork.staff.RolexStaff;
import com.rolexnetwork.staff.managers.StaffManager;
import com.rolexnetwork.staff.utils.StaffItems;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffCommand implements CommandExecutor {

    private final StaffManager staffManager;

    public StaffCommand(StaffManager staffManager) {
        this.staffManager = staffManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "פקודה זו מיועדת לשחקנים בלבד!");
            return true;
        }

        Player player = (Player) sender;

        // הפעלת / כיבוי מצב צוות
        staffManager.toggleStaffMode(player);

        // אם נכנס למצב צוות - תן לו את הכלים
        if (staffManager.isInStaffMode(player)) {
            player.getInventory().clear(); // ניקוי זמני לקבלת כלי הניהול
            player.getInventory().setItem(0, StaffItems.getSwissKnife());
            player.getInventory().setItem(1, StaffItems.getGraveDiggerWand());
            player.getInventory().setItem(2, StaffItems.getFreezeTool());
        } else {
            player.getInventory().clear(); // ניקוי כלים בסיום
        }

        return true;
    }
}
