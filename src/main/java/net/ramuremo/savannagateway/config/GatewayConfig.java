package net.ramuremo.savannagateway.config;

import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.lang.reflect.ParameterizedType;

public final class GatewayConfig extends ConfigFile {
    public GatewayConfig(Plugin plugin) {
        super(plugin, "config.yml");

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public <T> T getValue(@Nonnull Path<T> path) {
        return getConfig().getObject("config." + path.getPath(), path.getClazz(), path.getDefault());
    }

    public <T> void setValue(@Nonnull Path<T> path, T value) {
        getConfig().set(path.getPath(), value);
    }

    public interface Path<T> {
        Path<String> TOKEN = new Path<String>() {
            @Override
            public String getPath() {
                return "discord.token";
            }

            @Override
            public String getDefault() {
                return "token";
            }

            @Override
            public Class<String> getClazz() {
                return String.class;
            }
        };
        Path<Long> CHANNEL_ID = new Path<Long>() {
            @Override
            public String getPath() {
                return "discord.channel-id";
            }

            @Override
            public Long getDefault() {
                return 0L;
            }

            @Override
            public Class<Long> getClazz() {
                return Long.class;
            }
        };

        String getPath();
        T getDefault();
        Class<T> getClazz();
    }
}
