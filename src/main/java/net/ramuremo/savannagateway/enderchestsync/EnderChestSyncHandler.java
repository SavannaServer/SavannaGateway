package net.ramuremo.savannagateway.enderchestsync;

import net.ramuremo.savannagateway.SavannaGateway;
import net.ramuremo.savannagateway.enderchestsync.listener.SyncEnderChestListener;
import net.ramuremo.savannagateway.utility.EventUtil;

public final class EnderChestSyncHandler {
    public EnderChestSyncHandler() {
        EventUtil.register(
                SavannaGateway.getPlugin(SavannaGateway.class),
                new SyncEnderChestListener()
        );
    }
}
