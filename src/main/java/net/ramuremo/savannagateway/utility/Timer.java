package net.ramuremo.savannagateway.utility;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nonnull;

public final class Timer {
    private final JavaPlugin plugin;
    private final int time;
    private int currentTime;
    private Runnable onUpdate = null, onEnd = null;

    private BukkitTask timerTask;


    public Timer(@Nonnull JavaPlugin plugin, int time) {
        this.plugin = plugin;
        this.time = time;
        currentTime = time;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setOnUpdate(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }

    public void setOnEnd(Runnable onEnd) {
        this.onEnd = onEnd;
    }

    public void start() {
        currentTime = time;
        if (timerTask != null && !timerTask.isCancelled()) timerTask.cancel();

        timerTask = new BukkitRunnable() {
            @Override
            public void run() {
                onUpdate.run();
                currentTime--;
                if (currentTime <= 0) {
                    this.cancel();
                    onEnd.run();
                }
            }
        }.runTaskTimer(plugin, 20, 20);
    }

    public void stop() {
        currentTime = 0;
    }
}
