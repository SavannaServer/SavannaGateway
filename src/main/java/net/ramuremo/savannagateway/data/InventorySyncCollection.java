package net.ramuremo.savannagateway.data;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import net.ramuremo.savannagateway.database.DatabaseHandler;
import org.bukkit.inventory.PlayerInventory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public final class InventorySyncCollection {
    private final DBCollection inventorySyncCollection;

    public InventorySyncCollection(@Nonnull DatabaseHandler databaseHandler, int channel) {
        this.inventorySyncCollection = databaseHandler.getDB().getCollection("inventory-sync-" + channel);
    }

    @Nullable
    public InventorySync find(@Nonnull UUID playerUniqueId) {
        final DBObject query = new BasicDBObject();
        query.put("playerUniqueId", playerUniqueId.toString());
        final DBObject object = inventorySyncCollection.findOne(query);
        if (object == null) return null;
        return convert(object);
    }

    public void save(@Nonnull InventorySync old, @Nonnull InventorySync latest) {
        final DBObject oldObject = convert(old), latestObject = convert(latest);
        inventorySyncCollection.update(oldObject, latestObject);
    }

    public void save(@Nonnull InventorySync inventorySync) {
        inventorySyncCollection.insert(convert(inventorySync));
    }

    public void remove(@Nonnull InventorySync inventorySync) {
        inventorySyncCollection.remove(convert(inventorySync));
    }

    private DBObject convert(@Nonnull InventorySync inventorySync) {
        final DBObject object = new BasicDBObject();
        object.put("playerUniqueId", inventorySync.getPlayerUniqueId().toString());
        object.put("playerInventory", inventorySync.getPlayerInventory());
        return object;
    }

    private InventorySync convert(@Nonnull DBObject dbObject) {
        return new InventorySync(
                UUID.fromString((String) dbObject.get("playerUniqueId")),
                (PlayerInventory) dbObject.get("playerInventory")
        );
    }
}
