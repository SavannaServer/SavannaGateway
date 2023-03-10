package net.ramuremo.savannagateway.discord.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.ramuremo.savannagateway.SavannaGateway;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;

public final class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final String iconUrl = "https://minotar.net/avatar/" + player.getName() + ".png";

        final EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(player.getName() + "が参加しました", iconUrl, iconUrl);
        builder.setColor(Color.GREEN);

        SavannaGateway.getInstance().getDiscordHandler().getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
