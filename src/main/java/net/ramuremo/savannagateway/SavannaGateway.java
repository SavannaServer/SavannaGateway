package net.ramuremo.savannagateway;

import net.ramuremo.savannagateway.config.GatewayConfig;
import net.ramuremo.savannagateway.database.DatabaseHandler;
import net.ramuremo.savannagateway.discord.DiscordHandler;
import net.ramuremo.savannagateway.enderchestsync.EnderChestSyncHandler;
import net.ramuremo.savannagateway.inventorysync.InventorySyncHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class SavannaGateway extends JavaPlugin {
    private static SavannaGateway instance;
    private static GatewayConfig config;
    private static DiscordHandler discordHandler;
    private static DatabaseHandler databaseHandler;
    private static InventorySyncHandler inventorySyncHandler;
    private static EnderChestSyncHandler enderChestSyncHandler;

    @Override
    public void onEnable() {
        instance = this;

        config = new GatewayConfig(this);
        //databaseHandler = new DatabaseHandler();
        //databaseHandler.connect(
        //        config.getValue(GatewayConfig.Path.DATABASE_HOST),
        //        config.getValue(GatewayConfig.Path.DATABASE_PORT),
        //        config.getValue(GatewayConfig.Path.DATABASE_USERNAME),
        //        config.getValue(GatewayConfig.Path.DATABASE_PASSWORD)
        //);
        if (config.getValue(GatewayConfig.Path.DISCORD_ENABLE)) {
            discordHandler = new DiscordHandler(
                    this,
                    config.getValue(GatewayConfig.Path.DISCORD_TOKEN),
                    config.getValue(GatewayConfig.Path.DISCORD_CHANNEL_ID)
            );
        }
        if (config.getValue(GatewayConfig.Path.INVENTORY_SYNC_ENABLE)) {
            inventorySyncHandler = new InventorySyncHandler();
        }
        if (config.getValue(GatewayConfig.Path.ENDERCHEST_SYNC_ENABLE)) {
            enderChestSyncHandler = new EnderChestSyncHandler();
        }

        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        if (config.getValue(GatewayConfig.Path.DISCORD_ENABLE)) {
            discordHandler.shutdown();
        }
        getLogger().info("The plugin has been disabled.");
    }

    public static SavannaGateway getInstance() {return instance;}
    public static GatewayConfig getConfigFile() {return config;}
    public static DatabaseHandler getDatabaseHandler() {return databaseHandler;}
    public static DiscordHandler getDiscordHandler() {return discordHandler;}
    public static InventorySyncHandler getInventorySyncHandler() {return inventorySyncHandler;}
    public static EnderChestSyncHandler getEnderChestSyncHandler() {return enderChestSyncHandler;}
}
