package com.rolexnetwork.staff;

import com.rolexnetwork.staff.commands.StaffCommand;
import com.rolexnetwork.staff.listeners.StaffToolListener;
import com.rolexnetwork.staff.managers.StaffManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RolexStaff extends JavaPlugin {

    private static RolexStaff instance;
    private StaffManager staffManager;

    @Override
    public void onEnable() {
        instance = this;

        // אתחול ה-Manager
        this.staffManager = new StaffManager(this);

        // רישום פקודות
        if (getCommand("staff") != null) {
            getCommand("staff").setExecutor(new StaffCommand(staffManager));
        }

        // רישום Listeners
        getServer().getPluginManager().registerEvents(new StaffToolListener(staffManager), this);

        getLogger().info("RolexNetWork-Staff Enabled Successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RolexNetWork-Staff Disabled.");
    }

    public static RolexStaff getInstance() {
        return instance;
    }

    public StaffManager getStaffManager() {
        return staffManager;
    }
}
