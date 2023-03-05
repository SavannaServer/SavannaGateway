package net.ramuremo.savannagateway.data;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.savannacore.database.DatabaseHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public final class EnderChestSyncCollection {
    private final DBCollection inventorySyncCollection;

    public EnderChestSyncCollection(@Nonnull DatabaseHandler databaseHandler, int channel) {
        this.inventorySyncCollection = databaseHandler.getClient().getDB("savanna-gateway").getCollection("enderchest-sync-" + channel);
    }

    @Nullable
    public EnderChestSync find(@Nonnull UUID playerUniqueId) {
        final DBObject query = new BasicDBObject();
        query.put("playerUniqueId", playerUniqueId.toString());
        final DBObject object = inventorySyncCollection.findOne(query);
        if (object == null) return null;
        return convert(object);
    }

    public void save(@Nonnull EnderChestSync old, @Nonnull EnderChestSync latest) {
        final DBObject oldObject = convert(old), latestObject = convert(latest);
        inventorySyncCollection.update(oldObject, latestObject);
    }

    public void save(@Nonnull EnderChestSync inventorySync) {
        inventorySyncCollection.insert(convert(inventorySync));
    }

    public void remove(@Nonnull EnderChestSync inventorySync) {
        inventorySyncCollection.remove(convert(inventorySync));
    }

    private DBObject convert(@Nonnull EnderChestSync inventorySync) {
        final DBObject object = new BasicDBObject();
        object.put("playerUniqueId", inventorySync.getPlayerUniqueId().toString());
        object.put("inventory", inventorySync.getInventory());
        return object;
    }

    private EnderChestSync convert(@Nonnull DBObject dbObject) {
        return new EnderChestSync(
                UUID.fromString((String) dbObject.get("playerUniqueId")),
                (Inventory) dbObject.get("inventory")
        );
    }
}
