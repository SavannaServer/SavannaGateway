package net.ramuremo.savannagateway.data;

import org.bukkit.inventory.PlayerInventory;

import javax.annotation.Nonnull;
import java.util.UUID;

public final class InventorySync {
    private final UUID playerUniqueId;
    private PlayerInventory playerInventory;

    public InventorySync(@Nonnull UUID playerUniqueId, @Nonnull PlayerInventory playerInventory) {
        this.playerUniqueId = playerUniqueId;
        this.playerInventory = playerInventory;
    }

    public UUID getPlayerUniqueId() {
        return playerUniqueId;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }
}
