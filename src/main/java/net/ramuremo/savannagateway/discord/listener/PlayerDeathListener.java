package net.ramuremo.savannagateway.discord.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.ramuremo.savannagateway.SavannaGateway;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;

public final class PlayerDeathListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        final Player player = event.getPlayer();
        final Component deathMessageComponent = event.deathMessage();
        if (deathMessageComponent == null) return;
        final String deathMessage = PlainTextComponentSerializer.plainText().serialize(deathMessageComponent);
        final String iconUrl = "https://minotar.net/avatar/" + player.getName() + ".png";

        final EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(deathMessage, iconUrl, iconUrl);
        builder.setColor(new Color(0xFF6B006B, true));

        SavannaGateway.getInstance().getDiscordHandler().getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
