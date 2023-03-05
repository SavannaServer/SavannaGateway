package net.ramuremo.savannagateway.inventorysync.listener;

import net.ramuremo.savannagateway.SavannaGateway;
import net.ramuremo.savannagateway.config.GatewayConfig;
import net.ramuremo.savannagateway.data.InventorySync;
import net.ramuremo.savannagateway.data.InventorySyncCollection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;
import tokyo.ramune.savannacore.SavannaCore;

import javax.annotation.Nonnull;

public final class SyncPlayerInventoryListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        final int channel = SavannaGateway.getConfigFile().getValue(GatewayConfig.Path.INVENTORY_SYNC_CHANNEL);
        final int oldChannel = SavannaGateway.getConfigFile().getValue(GatewayConfig.Path.INVENTORY_SYNC_OLD_CHANNEL);
        final InventorySyncCollection collection = new InventorySyncCollection(SavannaCore.getInstance().getDatabaseHandler(), channel);
        final InventorySyncCollection oldCollection = new InventorySyncCollection(SavannaCore.getInstance().getDatabaseHandler(), oldChannel);

        final Player player = event.getPlayer();
        final InventorySync inventorySync = collection.find(player.getUniqueId());
        final InventorySync oldInventorySync = oldCollection.find(player.getUniqueId());

        if (inventorySync != null) {
            setPlayerInventory(player, inventorySync.getPlayerInventory());
        }
        if (oldInventorySync != null) {
            setPlayerInventory(player, oldInventorySync.getPlayerInventory());
            oldCollection.remove(oldInventorySync);
        }
    }

    private void setPlayerInventory(@Nonnull Player player, @Nonnull PlayerInventory playerInventory) {
        final PlayerInventory currentPlayerInventory = player.getInventory();
        currentPlayerInventory.setContents(playerInventory.getContents());
        currentPlayerInventory.setArmorContents(playerInventory.getArmorContents());
        currentPlayerInventory.setExtraContents(playerInventory.getExtraContents());
        currentPlayerInventory.setStorageContents(playerInventory.getStorageContents());
        player.updateInventory();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final int channel = SavannaGateway.getConfigFile().getValue(GatewayConfig.Path.INVENTORY_SYNC_CHANNEL);
        final InventorySyncCollection collection = new InventorySyncCollection(SavannaCore.getInstance().getDatabaseHandler(), channel);
        final Player player = event.getPlayer();

        final InventorySync inventorySync = collection.find(player.getUniqueId());
        final InventorySync currentInventorySync = new InventorySync(player.getUniqueId(), player.getInventory());
        if (inventorySync == null) {
            collection.save(currentInventorySync);
        } else {
            collection.save(inventorySync, currentInventorySync);
        }
    }
}
