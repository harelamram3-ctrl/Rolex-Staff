package com.rolexnetwork.staff;

import com.rolexnetwork.staff.commands.StaffCommand;
import com.rolexnetwork.staff.discord.DiscordManager;
import com.rolexnetwork.staff.listeners.MenuClickListener;
import com.rolexnetwork.staff.listeners.StaffToolListener;
import com.rolexnetwork.staff.managers.StaffManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RolexStaff extends JavaPlugin {

    private static RolexStaff instance;
    private StaffManager staffManager;
    private DiscordManager discordManager;

    @Override
    public void onEnable() {
        instance = this;

        // שמירת config.yml דיפולטיבי אם לא קיים
        saveDefaultConfig();

        // אתחול Managers
        this.staffManager = new StaffManager(this);
        
        String botToken = getConfig().getString("discord.bot-token");
        String channelId = getConfig().getString("discord.log-channel-id");
        this.discordManager = new DiscordManager(botToken, channelId);

        // רישום פקודות
        if (getCommand("staff") != null) {
            getCommand("staff").setExecutor(new StaffCommand(staffManager));
        }

        // רישום Listeners
        getServer().getPluginManager().registerEvents(new StaffToolListener(staffManager), this);
        getServer().getPluginManager().registerEvents(new MenuClickListener(), this);

        getLogger().info("RolexNetWork-Staff Enabled Successfully! Author: RolexNetWork-badpanda14");
    }

    @Override
    public void onDisable() {
        if (discordManager != null) {
            discordManager.shutdown();
        }
        getLogger().info("RolexNetWork-Staff Disabled.");
    }

    public static RolexStaff getInstance() {
        return instance;
    }

    public StaffManager getStaffManager() {
        return staffManager;
    }

    public DiscordManager getDiscordManager() {
        return discordManager;
    }
}
