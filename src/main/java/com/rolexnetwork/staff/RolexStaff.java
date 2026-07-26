package com.rolexnetwork.staff;

import com.rolexnetwork.staff.commands.StaffCommand;
import com.rolexnetwork.staff.discord.DiscordManager;
import com.rolexnetwork.staff.listeners.FreezeListener;
import com.rolexnetwork.staff.listeners.GraveDiggerListener;
import com.rolexnetwork.staff.listeners.MenuClickListener;
import com.rolexnetwork.staff.listeners.MindReadListener;
import com.rolexnetwork.staff.listeners.StaffToolListener;
import com.rolexnetwork.staff.managers.FreezeManager;
import com.rolexnetwork.staff.managers.StaffManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RolexStaff extends JavaPlugin {

    private static RolexStaff instance;
    private StaffManager staffManager;
    private FreezeManager freezeManager;
    private DiscordManager discordManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        this.staffManager = new StaffManager(this);
        this.freezeManager = new FreezeManager();
        
        String botToken = getConfig().getString("discord.bot-token");
        String channelId = getConfig().getString("discord.log-channel-id");
        this.discordManager = new DiscordManager(botToken, channelId);

        if (getCommand("staff") != null) {
            getCommand("staff").setExecutor(new StaffCommand(staffManager));
        }

        getServer().getPluginManager().registerEvents(new StaffToolListener(staffManager), this);
        getServer().getPluginManager().registerEvents(new MenuClickListener(), this);
        getServer().getPluginManager().registerEvents(new MindReadListener(), this);
        getServer().getPluginManager().registerEvents(new GraveDiggerListener(), this);
        getServer().getPluginManager().registerEvents(new FreezeListener(freezeManager), this);

        getLogger().info("RolexNetWork-Staff Enabled Successfully!");
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

    public FreezeManager getFreezeManager() {
        return freezeManager;
    }

    public DiscordManager getDiscordManager() {
        return discordManager;
    }
}
