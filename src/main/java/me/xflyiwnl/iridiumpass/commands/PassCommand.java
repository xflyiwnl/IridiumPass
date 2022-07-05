package me.xflyiwnl.iridiumpass.commands;

import me.xflyiwnl.iridiumpass.book.InventoryManager;
import me.xflyiwnl.iridiumpass.config.Config;
import me.xflyiwnl.iridiumpass.manager.PassManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        String PREFIX = Config.getMessagesYaml().getString("messages.chat-prefix");

        if (args.length == 0) {

            for (String str : Config.getMessagesYaml().getStringList("messages.arguments")) {
                player.sendMessage(format(str.replace("{prefix}", PREFIX)));
            }

            return true;
        }

        if (args[0].equalsIgnoreCase("open")) {

            if (args.length == 1) {

                new InventoryManager(player, player);

                return true;
            }

            else if (args.length == 2) {

                if (PassManager.getPass(Bukkit.getOfflinePlayer(args[1]).getUniqueId()) == null) {
                    player.sendMessage(format(Config.getMessagesYaml().getString("messages.errors.player-not-found").replace("{prefix}", PREFIX)));
                    return true;
                }

                new InventoryManager(player, Bukkit.getPlayer(args[1]));

                return true;
            }

            else {
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.errors.not-enough-args").replace("{prefix}", PREFIX)));
                return true;
            }

        }

        else if (args[0].equalsIgnoreCase("setname")) {

            if (args.length == 2) {

                PassManager.getPass(player.getUniqueId()).setName(args[1]);
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.success.name-changed").replace("{prefix}", PREFIX)));

                return true;
            }

            else {
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.errors.not-enough-args").replace("{prefix}", PREFIX)));
                return true;
            }

        }

        else if (args[0].equalsIgnoreCase("setsurname")) {
            if (args.length == 2) {

                PassManager.getPass(player.getUniqueId()).setSurname(args[1]);
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.success.surname-changed").replace("{prefix}", PREFIX)));

                return true;
            }

            else {
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.errors.not-enough-args").replace("{prefix}", PREFIX)));
                return true;
            }
        }
        else if (args[0].equalsIgnoreCase("setvk")) {
            if (args.length == 2) {

                PassManager.getPass(player.getUniqueId()).setVk(args[1]);
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.success.vk-changed").replace("{prefix}", PREFIX)));

                return true;
            }

            else {
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.errors.not-enough-args").replace("{prefix}", PREFIX)));
                return true;
            }
        }
        else if (args[0].equalsIgnoreCase("setdiscord")) {
            if (args.length == 2) {

                Matcher matcher = Pattern.compile("^.{3,32}#[0-9]{4}").matcher(args[1]);

                if (matcher.matches() == false) {
                    player.sendMessage(format(Config.getMessagesYaml().getString("messages.errors.not-correct-discord").replace("{prefix}", PREFIX)));
                    return true;
                }

                PassManager.getPass(player.getUniqueId()).setDiscord(args[1]);
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.success.discord-changed").replace("{prefix}", PREFIX)));

                return true;
            }

            else {
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.errors.not-enough-args").replace("{prefix}", PREFIX)));
                    return true;
            }
        }
        else if (args[0].equalsIgnoreCase("reload")) {
            if (!player.hasPermission("astrapass.admin")) {
                player.sendMessage(format(Config.getMessagesYaml().getString("messages.errors.no-permission").replace("{prefix}", PREFIX)));
                return true;
            }
            new Config();
            player.sendMessage(format(Config.getMessagesYaml().getString("messages.success.reloaded").replace("{prefix}", PREFIX)));
            return true;
        }

        else {
            player.sendMessage(format(Config.getMessagesYaml().getString("messages.errors.not-enough-args").replace("{prefix}", PREFIX)));
            return true;
        }

    }

    private static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg.
                replaceAll("#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])", "&x&$1&$2&$3&$4&$5&$6"));
    }
}
