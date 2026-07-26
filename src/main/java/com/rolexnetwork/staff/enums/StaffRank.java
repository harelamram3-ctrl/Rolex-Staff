package com.rolexnetwork.staff.enums;

import org.bukkit.entity.Player;

public enum StaffRank {
    LEVEL_1("Helper", "rolexstaff.level1", 1),
    LEVEL_2("Moderator", "rolexstaff.level2", 2),
    LEVEL_3("Admin/Owner", "rolexstaff.level3", 3),
    NONE("Player", "", 0);

    private final String displayName;
    private final String permission;
    private final int level;

    StaffRank(String displayName, String permission, int level) {
        this.displayName = displayName;
        this.permission = permission;
        this.level = level;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPermission() {
        return permission;
    }

    public int getLevel() {
        return level;
    }

    // מתודה שבודקת מה הרמה הגבוהה ביותר של השחקן לפי הרשאות
    public static StaffRank getRank(Player player) {
        if (player.hasPermission(LEVEL_3.getPermission()) || player.isOp()) {
            return LEVEL_3;
        } else if (player.hasPermission(LEVEL_2.getPermission())) {
            return LEVEL_2;
        } else if (player.hasPermission(LEVEL_1.getPermission())) {
            return LEVEL_1;
        }
        return NONE;
    }
}
