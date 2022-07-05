package me.xflyiwnl.iridiumpass;

import me.xflyiwnl.iridiumpass.commands.PassCommand;
import me.xflyiwnl.iridiumpass.commands.PassTabCompleter;
import me.xflyiwnl.iridiumpass.config.Config;
import me.xflyiwnl.iridiumpass.manager.EventManager;
import me.xflyiwnl.iridiumpass.placeholder.PassPlaceholder;
import me.xflyiwnl.iridiumpass.util.PassUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main main;

    @Override
    public void onEnable() {
        main = this;

        new Config();
        Bukkit.getPluginCommand("pass").setExecutor(new PassCommand());
        Bukkit.getPluginCommand("pass").setTabCompleter(new PassTabCompleter());
        Bukkit.getPluginManager().registerEvents(new EventManager(), this);

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PassPlaceholder(this).register();
        }

        PassUtil.loadPass();

    }

    @Override
    public void onDisable() {
        PassUtil.savePass();
    }

    public static Main getMain() {
        return main;
    }

}
