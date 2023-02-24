package net.ramuremo.savannagateway.database;

import com.mongodb.*;
import com.mongodb.internal.connection.MongoCredentialWithCache;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import java.net.InetAddress;

public final class DatabaseHandler {
    private MongoClient client;

    public DatabaseHandler() {
    }

    public void connect(@Nonnull String host, int port, @Nonnull String username, @Nonnull String password) {
        if (client != null) client.close();
        try {
            client = new MongoClient(
                    new ServerAddress(host, port),
                    MongoCredential.createCredential(username, "savanna-gateway", password.toCharArray()),
                    MongoClientOptions.builder().build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.shutdown();
        }
    }

    public MongoClient getClient() {
        return client;
    }

    public DB getDB() {
        return client.getDB("savanna-gateway");
    }
}
