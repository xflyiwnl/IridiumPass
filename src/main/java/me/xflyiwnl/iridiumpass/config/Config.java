package me.xflyiwnl.iridiumpass.config;

import me.xflyiwnl.iridiumpass.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    private static File databaseFile;
    private static FileConfiguration databaseYaml;

    private static File guiFile;
    private static FileConfiguration guiYaml;

    private static File messagesFile;
    private static FileConfiguration messagesYaml;

    private static File settingsFile;
    private static FileConfiguration settingsYaml;

    public Config() {

        databaseFile = new File(Main.getMain().getDataFolder(), "database.yml");
        if (!databaseFile.exists()) {
            Main.getMain().saveResource("database.yml", true);
        }
        databaseYaml = YamlConfiguration.loadConfiguration(databaseFile);

        guiFile = new File(Main.getMain().getDataFolder(), "gui.yml");
        if (!guiFile.exists()) {
            Main.getMain().saveResource("gui.yml", true);
        }
        guiYaml = YamlConfiguration.loadConfiguration(guiFile);

        messagesFile = new File(Main.getMain().getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            Main.getMain().saveResource("messages.yml", true);
        }
        messagesYaml = YamlConfiguration.loadConfiguration(messagesFile);

        settingsFile = new File(Main.getMain().getDataFolder(), "settings.yml");
        if (!settingsFile.exists()) {
            Main.getMain().saveResource("settings.yml", true);
        }
        settingsYaml = YamlConfiguration.loadConfiguration(settingsFile);

    }

    public static File getSettingsFile() {
        return settingsFile;
    }

    public static FileConfiguration getSettingsYaml() {
        return settingsYaml;
    }

    public static File getMessagesFile() {
        return messagesFile;
    }

    public static FileConfiguration getMessagesYaml() {
        return messagesYaml;
    }

    public static File getGuiFile() {
        return guiFile;
    }

    public static FileConfiguration getGuiYaml() {
        return guiYaml;
    }

    public static File getDatabaseFile() {
        return databaseFile;
    }

    public static FileConfiguration getDatabaseYaml() {
        return databaseYaml;
    }
}
