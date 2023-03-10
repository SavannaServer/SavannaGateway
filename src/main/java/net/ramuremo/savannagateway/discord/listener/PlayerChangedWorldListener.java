package net.ramuremo.savannagateway.discord.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.ramuremo.savannagateway.SavannaGateway;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.awt.*;

public final class PlayerChangedWorldListener implements Listener {
    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        final Player player = event.getPlayer();
        final World from = event.getFrom(), to = player.getWorld();

        final EmbedBuilder builder = new EmbedBuilder();
        final String iconUrl = "https://minotar.net/avatar/" + player.getName() + ".png";
        builder.setAuthor(player.getName() + "ワールド移動 " + from.getName() + " → " + to.getName(), iconUrl, iconUrl);
        builder.setColor(new Color(0xFF6B006B, true));

        SavannaGateway.getInstance().getDiscordHandler().getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
