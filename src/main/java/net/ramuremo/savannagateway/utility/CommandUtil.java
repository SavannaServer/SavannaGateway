package net.ramuremo.savannagateway.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public final class CommandUtil {
    private final static Set<org.bukkit.command.Command> registeredCommands = new HashSet<>();

    public static void register(@Nonnull String fallbackPrefix, @Nonnull org.bukkit.command.Command... commands) {
        for (org.bukkit.command.Command command : commands) {
            Bukkit.getCommandMap().register(fallbackPrefix, command);
        }
    }

    public static void unregister(org.bukkit.command.Command... commands) {
        for (org.bukkit.command.Command command : commands) {
            command.unregister(Bukkit.getCommandMap());
        }
    }

    public static void unregisterAll() {
        for (org.bukkit.command.Command command : registeredCommands) {
            unregister(command);
        }
        registeredCommands.clear();
    }

    public static void mismatchSender(@Nonnull CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You can't execute this command from here.");
    }

    public static void success(@Nonnull CommandSender sender) {
        sender.sendMessage(ChatColor.GREEN + "Success!");
    }
}
