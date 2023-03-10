package net.ramuremo.savannagateway.inventorysync;

import net.ramuremo.savannagateway.SavannaGateway;
import net.ramuremo.savannagateway.inventorysync.listener.SyncPlayerInventoryListener;
import net.ramuremo.savannagateway.utility.EventUtil;

public final class InventorySyncHandler {
    public InventorySyncHandler() {
        EventUtil.register(
                SavannaGateway.getPlugin(SavannaGateway.class),
                new SyncPlayerInventoryListener()
        );
    }
}
