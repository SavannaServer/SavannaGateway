package net.ramuremo.savannagateway.config;

import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;

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
        Path<String> DATABASE_HOST = new Path<String>() {
            @Override public String getPath() {return "database.host";}
            @Override public String getDefault() {return "localhost";}
            @Override public Class<String> getClazz() {return String.class;}
        };
        Path<Integer> DATABASE_PORT = new Path<Integer>() {
            @Override public String getPath() {return "database.port";}
            @Override public Integer getDefault() {return 27017;}
            @Override public Class<Integer> getClazz() {return Integer.class;}
        };
        Path<String> DATABASE_USERNAME = new Path<String>() {
            @Override public String getPath() {return "database.username";}
            @Override public String getDefault() {return "root";}
            @Override public Class<String> getClazz() {return String.class;}
        };
        Path<String> DATABASE_PASSWORD = new Path<String>() {
            @Override public String getPath() {return "database.password";}
            @Override public String getDefault() {return "password";}
            @Override public Class<String> getClazz() {return String.class;}
        };
        Path<String> DISCORD_TOKEN = new Path<String>() {
            @Override public String getPath() {return "discord.token";}
            @Override public String getDefault() {return "token";}
            @Override public Class<String> getClazz() {return String.class;}
        };
        Path<Long> DISCORD_CHANNEL_ID = new Path<Long>() {
            @Override public String getPath() {return "discord.channel-id";}
            @Override public Long getDefault() {return 0L;}
            @Override public Class<Long> getClazz() {return Long.class;}
        };

        Path<Boolean> INVENTORY_SYNC_ENABLE = new Path<Boolean>() {
            @Override public String getPath() {return "inventory-sync.enable";}
            @Override public Boolean getDefault() {return false;}
            @Override public Class<Boolean> getClazz() {return Boolean.class;}
        };
        Path<Integer> INVENTORY_SYNC_CHANNEL = new Path<Integer>() {
            @Override public String getPath() {return "inventory-sync.channel";}
            @Override public Integer getDefault() {return 0;}
            @Override public Class<Integer> getClazz() {return Integer.class;}
        };
        Path<Integer> INVENTORY_SYNC_OLD_CHANNEL = new Path<Integer>() {
            @Override public String getPath() {return "inventory-sync.old-channel";}
            @Override public Integer getDefault() {return -1;}
            @Override public Class<Integer> getClazz() {return Integer.class;}
        };

        Path<Boolean> ENDERCHEST_SYNC_ENABLE = new Path<Boolean>() {
            @Override public String getPath() {return "enderchest-sync.enable";}
            @Override public Boolean getDefault() {return false;}
            @Override public Class<Boolean> getClazz() {return Boolean.class;}
        };
        Path<Integer> ENDERCHEST_SYNC_CHANNEL = new Path<Integer>() {
            @Override public String getPath() {return "enderchest-sync.channel";}
            @Override public Integer getDefault() {return 0;}
            @Override public Class<Integer> getClazz() {return Integer.class;}
        };
        Path<Integer> ENDERCHEST_SYNC_OLD_CHANNEL = new Path<Integer>() {
            @Override public String getPath() {return "enderchest-sync.old-channel";}
            @Override public Integer getDefault() {return -1;}
            @Override public Class<Integer> getClazz() {return Integer.class;}
        };

        String getPath();
        T getDefault();
        Class<T> getClazz();
    }
}
