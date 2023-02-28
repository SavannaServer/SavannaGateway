package net.ramuremo.savannagateway.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.ramuremo.savannagateway.discord.listener.*;
import net.ramuremo.savannagateway.utility.EventUtil;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.awt.*;
import java.time.Duration;

public final class DiscordHandler {
    private final JDA jda;
    private final long channelId;

    public DiscordHandler(@Nonnull JavaPlugin plugin, @Nonnull String token, long channelId) {

        final JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.setActivity(Activity.streaming("Server Working!", "https://savanna.ramuremo.net"));

        this.jda = builder.build();
        this.channelId = channelId;

        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EventUtil.register(
                plugin,
                new BukkitMessageListener(),
                new PlayerJoinListener(),
                new PlayerQuitListener(),
                new PlayerDeathListener(),
                new PlayerChangedWorldListener()
        );
        jda.addEventListener(new MessageListener());

        final EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("サーバーがオンラインになりました!", "https://cdn.discordapp.com/attachments/1077933337433346128/1078011052140265492/35f.png", "https://cdn.discordapp.com/attachments/1077933337433346128/1078011052140265492/35f.png");
        embedBuilder.setColor(Color.GREEN);
        getChannel().sendMessageEmbeds(embedBuilder.build()).submit();
    }

    public void shutdown() {
        final EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("サーバーがオフラインになりました!", "https://cdn.discordapp.com/attachments/1077933337433346128/1078011052140265492/35f.png", "https://cdn.discordapp.com/attachments/1077933337433346128/1078011052140265492/35f.png");
        embedBuilder.setColor(Color.RED);
        getChannel().sendMessageEmbeds(embedBuilder.build()).submit();

        jda.shutdownNow();
        try {
            jda.awaitShutdown(Duration.ofSeconds(5));
        } catch (Exception ignored) {
        }
    }

    public JDA getJda() {return jda;}
    public long getChannelId() {return channelId;}
    public TextChannel getChannel() {return jda.getTextChannelById(channelId);}
}
