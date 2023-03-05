package net.ramuremo.savannagateway.enderchestsync.listener;

import net.ramuremo.savannagateway.SavannaGateway;
import net.ramuremo.savannagateway.config.GatewayConfig;
import net.ramuremo.savannagateway.data.EnderChestSync;
import net.ramuremo.savannagateway.data.EnderChestSyncCollection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.savannacore.SavannaCore;

import javax.annotation.Nonnull;

public final class SyncEnderChestListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        final int channel = SavannaGateway.getConfigFile().getValue(GatewayConfig.Path.ENDERCHEST_SYNC_CHANNEL);
        final int oldChannel = SavannaGateway.getConfigFile().getValue(GatewayConfig.Path.ENDERCHEST_SYNC_OLD_CHANNEL);
        final EnderChestSyncCollection collection = new EnderChestSyncCollection(SavannaCore.getInstance().getDatabaseHandler(), channel);
        final EnderChestSyncCollection oldCollection = new EnderChestSyncCollection(SavannaCore.getInstance().getDatabaseHandler(), oldChannel);

        final Player player = event.getPlayer();
        final EnderChestSync enderChestSync = collection.find(player.getUniqueId());
        final EnderChestSync oldEnderChestSync = oldCollection.find(player.getUniqueId());

        if (enderChestSync != null) {
            setPlayerInventory(player, enderChestSync.getInventory());
        }
        if (oldEnderChestSync != null) {
            setPlayerInventory(player, oldEnderChestSync.getInventory());
            oldCollection.remove(oldEnderChestSync);
        }
    }

    private void setPlayerInventory(@Nonnull Player player, @Nonnull Inventory inventory) {
        final Inventory currentInventory = player.getEnderChest();
        currentInventory.setContents(inventory.getContents());
        player.updateInventory();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final int channel = SavannaGateway.getConfigFile().getValue(GatewayConfig.Path.ENDERCHEST_SYNC_CHANNEL);
        final EnderChestSyncCollection collection = new EnderChestSyncCollection(SavannaCore.getInstance().getDatabaseHandler(), channel);
        final Player player = event.getPlayer();

        final EnderChestSync enderChestSync = collection.find(player.getUniqueId());
        final EnderChestSync currentEnderChestSync = new EnderChestSync(player.getUniqueId(), player.getEnderChest());
        if (enderChestSync == null) {
            collection.save(currentEnderChestSync);
        } else {
            collection.save(enderChestSync, currentEnderChestSync);
        }
    }
}
