package me.xflyiwnl.iridiumpass.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PassTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> argument = new ArrayList<>();
        List<String> players = new ArrayList<>();

        if (players.isEmpty()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                players.add(player.getName());
            }
        }

        if (argument.isEmpty()) {
            argument.add("open");
            argument.add("setname");
            argument.add("setsurname");
            argument.add("setvk");
            argument.add("setdiscord");
            if (sender.isOp()) {
                argument.add("reload");
            }
        }

        if (args.length == 1) {
            return argument;
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("open")) {
                return players;
            }
        }

        return null;
    }

}
