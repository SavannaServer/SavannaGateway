package net.ramuremo.savannagateway;

import net.kyori.adventure.text.Component;
import net.ramuremo.savannagateway.config.GatewayConfig;
import net.ramuremo.savannagateway.database.DatabaseHandler;
import net.ramuremo.savannagateway.discord.DiscordHandler;
import net.ramuremo.savannagateway.enderchestsync.EnderChestSyncHandler;
import net.ramuremo.savannagateway.inventorysync.InventorySyncHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class SavannaGateway extends JavaPlugin {
    private static SavannaGateway instance;
    private GatewayConfig config;
    private DatabaseHandler databaseHandler;
    private DiscordHandler discordHandler;
    private InventorySyncHandler inventorySyncHandler;
    private EnderChestSyncHandler enderChestSyncHandler;

    public static SavannaGateway getInstance() {
        return instance;
    }

    public GatewayConfig getConfigFile() {
        return config;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public DiscordHandler getDiscordHandler() {
        return discordHandler;
    }

    public InventorySyncHandler getInventorySyncHandler() {
        return inventorySyncHandler;
    }

    public EnderChestSyncHandler getEnderChestSyncHandler() {
        return enderChestSyncHandler;
    }

    @Override
    public void onEnable() {
        instance = this;

        config = new GatewayConfig(this);
        databaseHandler = new DatabaseHandler();
        databaseHandler.connect(
                config.getValue(GatewayConfig.Path.DATABASE_HOST),
                config.getValue(GatewayConfig.Path.DATABASE_PORT)
        );
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
}
