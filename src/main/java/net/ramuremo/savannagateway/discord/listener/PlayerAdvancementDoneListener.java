package net.ramuremo.savannagateway.discord.listener;

import io.papermc.paper.advancement.AdvancementDisplay;
import net.dv8tion.jda.api.EmbedBuilder;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.ramuremo.savannagateway.SavannaGateway;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.awt.*;

public final class PlayerAdvancementDoneListener implements Listener {
    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event) {
        final Player player = event.getPlayer();
        final Advancement advancement = event.getAdvancement();
        final AdvancementDisplay display = advancement.getDisplay();
        if (display == null) return;

        final String title = PlainTextComponentSerializer.plainText().serialize(display.title());
        final String iconUrl = "https://minotar.net/avatar/" + player.getName() + ".png";

        final EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(player.getName() + "が" + title + "を達成しました", iconUrl, iconUrl);
        builder.setColor(new Color(0xFFAAF1));

        SavannaGateway.getInstance().getDiscordHandler().getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
