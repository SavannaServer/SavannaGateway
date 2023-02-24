package net.ramuremo.savannagateway.data;

import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;
import java.util.UUID;

public final class EnderChestSync {
    private final UUID playerUniqueId;
    private Inventory inventory;

    public EnderChestSync(@Nonnull UUID playerUniqueId, @Nonnull Inventory inventory) {
        this.playerUniqueId = playerUniqueId;
        this.inventory = inventory;
    }

    public UUID getPlayerUniqueId() {
        return playerUniqueId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
