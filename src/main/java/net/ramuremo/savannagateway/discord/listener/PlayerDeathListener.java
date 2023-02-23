package net.ramuremo.savannagateway.discord.listener;

import net.dv8tion.jda.api.EmbedBuilder;
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
        final String iconUrl = "https://minotar.net/avatar/" + player.getName() + ".png";

        final EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getDeathMessage(), iconUrl, iconUrl);
        builder.setColor(new Color(0xFF6B006B, true));

        SavannaGateway.getDiscordHandler().getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
