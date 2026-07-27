package com.rolexnetwork.staff;

import com.rolexnetwork.staff.listeners.FreezeListener;
import com.rolexnetwork.staff.listeners.MenuClickListener;
import com.rolexnetwork.staff.listeners.StaffToolListener;
import com.rolexnetwork.staff.managers.FreezeManager;
import com.rolexnetwork.staff.managers.StaffManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RolexStaff extends JavaPlugin {

    private static RolexStaff instance;
    private StaffManager staffManager;
    private FreezeManager freezeManager;

    @Override
    public void onEnable() {
        instance = this;

        // אתחול ה-Managers
        this.staffManager = new StaffManager();
        this.freezeManager = new FreezeManager();

        // רישום ה-Listeners
        getServer().getPluginManager().registerEvents(new StaffToolListener(staffManager), this);
        getServer().getPluginManager().registerEvents(new FreezeListener(freezeManager), this);
        getServer().getPluginManager().registerEvents(new MenuClickListener(), this);

        getLogger().info("RolexStaff plugin enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RolexStaff plugin disabled.");
    }

    public static RolexStaff getInstance() {
        return instance;
    }

    public StaffManager getStaffManager() {
        return staffManager;
    }

    public FreezeManager getFreezeManager() {
        return freezeManager;
    }
}
