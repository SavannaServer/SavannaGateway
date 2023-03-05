package net.ramuremo.savannagateway.enderchestsync;

import net.ramuremo.savannagateway.SavannaGateway;
import net.ramuremo.savannagateway.enderchestsync.listener.SyncEnderChestListener;
import tokyo.ramune.savannacore.utility.EventUtil;

public final class EnderChestSyncHandler {
    public EnderChestSyncHandler() {
        EventUtil.register(
                SavannaGateway.getInstance(),
                new SyncEnderChestListener()
        );
    }
}
