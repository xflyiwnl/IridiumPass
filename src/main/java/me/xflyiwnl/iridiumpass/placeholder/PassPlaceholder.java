
package me.xflyiwnl.iridiumpass.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.xflyiwnl.iridiumpass.Main;
import me.xflyiwnl.iridiumpass.config.Config;
import me.xflyiwnl.iridiumpass.manager.PassManager;
import org.bukkit.OfflinePlayer;

public class PassPlaceholder extends PlaceholderExpansion {

    private final Main plugin;

    public PassPlaceholder(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "xflyiwnl";
    }

    @Override
    public String getIdentifier() {
        return "iridiumpass";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("name")) {
            return PassManager.getPass(player.getUniqueId()).getName();
        }

        if(params.equalsIgnoreCase("surname")) {
            return PassManager.getPass(player.getUniqueId()).getSurname();
        }

        if(params.equalsIgnoreCase("formatted")) {

            if (!PassManager.getPass(player.getUniqueId()).getName().equals("Не указано") && !PassManager.getPass(player.getUniqueId()).getSurname().equals("Не указано")) {
                return PassManager.getPass(player.getUniqueId()).getName() + " " + PassManager.getPass(player.getUniqueId()).getSurname();
            }

            else {
                return Config.getSettingsYaml().getString("settings.placeholder.no-name");
            }

        }

        return null;
    }

}
