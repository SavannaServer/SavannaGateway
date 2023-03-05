package net.ramuremo.savannagateway.inventorysync;

import net.ramuremo.savannagateway.SavannaGateway;
import net.ramuremo.savannagateway.inventorysync.listener.SyncPlayerInventoryListener;
import tokyo.ramune.savannacore.utility.EventUtil;

public final class InventorySyncHandler {
    public InventorySyncHandler() {
        EventUtil.register(
                SavannaGateway.getInstance(),
                new SyncPlayerInventoryListener()
        );
    }
}
