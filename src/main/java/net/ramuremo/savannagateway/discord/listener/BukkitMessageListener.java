package net.ramuremo.savannagateway.discord.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.ramuremo.savannagateway.SavannaGateway;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.awt.*;

public final class BukkitMessageListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onAsyncChat(AsyncChatEvent event) {
        final Player player = event.getPlayer();
        final String message = PlainTextComponentSerializer.plainText().serialize(event.message());
        final String playerDisplayName = PlainTextComponentSerializer.plainText().serialize(player.displayName());
        final String iconUrl = "https://minotar.net/avatar/" + player.getName() + ".png";
        final EmbedBuilder builder = new EmbedBuilder();

        builder.setAuthor("<" + playerDisplayName + "> " + message, iconUrl, iconUrl);
        builder.setColor(new Color(255, 144, 183));

        SavannaGateway.getInstance().getDiscordHandler().getChannel()
                .sendMessageEmbeds(builder.build())
                .queue();
    }
}
