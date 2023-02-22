package net.ramuremo.savannagateway.discord.listener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.ramuremo.savannagateway.SavannaGateway;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public final class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getIdLong() != SavannaGateway.getDiscordHandler().getChannelId()) return;
        if (event.getAuthor().isBot() || event.getAuthor().isSystem()) return;
        final Member member = event.getMember();
        if (member == null) return;
        final Message message = event.getMessage();

        final Component discordPrefix = Component.text("<").color(TextColor.color(0xFFFFFF))
                .append(Component.text("Discord Gateway").color(TextColor.color(0x505DDB)))
                .append(Component.text("> ").color(TextColor.color(0xFFFFFF)));
        final String name = member.getUser().getName(), nickname = member.getNickname();
        final Role role = member.getRoles().get(0);
        final Component usernameComponent;
        if (role != null && role.getColor() != null) {
            usernameComponent = Component.text("<").color(TextColor.color(0xFFFFFF))
                    .append(Component.text(nickname == null ? name : nickname).color(TextColor.color(role.getColor().getRGB())))
                    .append(Component.text("> ").color(TextColor.color(0xFFFFFF)));
        } else {
            usernameComponent = Component.text("<").color(TextColor.color(0xFFFFFF))
                    .append(Component.text(nickname == null ? name : nickname))
                    .append(Component.text("> ").color(TextColor.color(0xFFFFFF)));
        }
        final Component messageComponent = Component.text(message.getContentDisplay())
                .color(TextColor.color(0xFFFFFF));

        final List<Component> attachments = new ArrayList<>();
        for (Message.Attachment attachment : message.getAttachments()) {
            attachments.add(Component.text("§e§b<§nアップロードされた画像§b>§r ").clickEvent(ClickEvent.openUrl(attachment.getUrl())));
        }

        Component bukkitMessage = discordPrefix.append(usernameComponent).append(messageComponent);
        for (Component attachment : attachments) {
            bukkitMessage = bukkitMessage.append(attachment);
        }
        Bukkit.broadcast(bukkitMessage);
    }
}
