package com.rolexnetwork.staff.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.Color;

public class DiscordManager {

    private JDA jda;
    private final String logChannelId;

    public DiscordManager(String botToken, String logChannelId) {
        this.logChannelId = logChannelId;
        try {
            if (botToken != null && !botToken.equalsIgnoreCase("YOUR_BOT_TOKEN_HERE") && !botToken.isEmpty()) {
                this.jda = JDABuilder.createDefault(botToken).build();
                this.jda.awaitReady();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPunishmentLog(String staffName, String targetName, String action, String reason) {
        if (jda == null) return;

        TextChannel channel = jda.getTextChannelById(logChannelId);
        if (channel == null) return;

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("🛡️ RolexNetWork - Staff Audit Log");
        embed.setColor(action.equalsIgnoreCase("BAN") ? Color.RED : Color.ORANGE);
        
        embed.addField("👤 השחקן הנענש:", targetName, true);
        embed.addField("🛡️ איש הצוות:", staffName, true);
        embed.addField("⚖️ סוג העונש:", action, false);
        embed.addField("📝 סיבה:", reason, false);
        
        embed.setFooter("RolexNetWork-Staff System • Powered by badpanda14");

        channel.sendMessageEmbeds(embed.build()).queue();
    }

    public void shutdown() {
        if (jda != null) {
            jda.shutdown();
        }
    }
}
