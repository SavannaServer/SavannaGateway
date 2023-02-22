package net.ramuremo.savannagateway;

import net.ramuremo.savannagateway.config.GatewayConfig;
import net.ramuremo.savannagateway.discord.DiscordHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class SavannaGateway extends JavaPlugin {
    private static SavannaGateway instance;
    private static GatewayConfig config;
    private static DiscordHandler discordHandler;

    @Override
    public void onEnable() {
        instance = this;

        config = new GatewayConfig(this);
        discordHandler = new DiscordHandler(
                this,
                config.getValue(GatewayConfig.Path.TOKEN),
                config.getValue(GatewayConfig.Path.CHANNEL_ID)
        );

        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        discordHandler.shutdown();
        getLogger().info("The plugin has been disabled.");
    }

    public static SavannaGateway getInstance() {return instance;}
    public static GatewayConfig getConfigFile() {return config;}
    public static DiscordHandler getDiscordHandler() {return discordHandler;}
}
