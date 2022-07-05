package me.xflyiwnl.iridiumpass.manager;

import com.palmergames.bukkit.towny.event.NewDayEvent;
import com.palmergames.bukkit.towny.event.statusscreen.NationStatusScreenEvent;
import com.palmergames.bukkit.towny.event.statusscreen.TownStatusScreenEvent;
import com.palmergames.bukkit.towny.object.Town;
import me.xflyiwnl.iridiumpass.book.InventoryManager;
import me.xflyiwnl.iridiumpass.config.Config;
import me.xflyiwnl.iridiumpass.pass.Pass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.UUID;

public class EventManager implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (PassManager.getPass(player.getUniqueId()) == null && Config.getDatabaseYaml().getConfigurationSection("database." + player.getUniqueId()) == null) {
            PassManager.getPassArray().add(new Pass(player.getUniqueId(), 0, "Не указано", "Не указано", "Не указано", "Не указано"));
            return;
        }

    }

    @EventHandler
    public void townStatus(TownStatusScreenEvent event) {

        Town town = event.getTown();
        UUID uuid = town.getMayor().getUUID();

        if (PassManager.getPass(town.getMayor().getUUID()) == null) {
            return;
        }

        if (!PassManager.getPass(town.getMayor().getUUID()).getName().equals("Не указано") && !PassManager.getPass(town.getMayor().getUUID()).getSurname().equals("Не указано")) {
            event.setLines(Arrays.asList(format(Config.getSettingsYaml().getString("settings.towny-status-screen.town-screen-has-name").
                    replace("{player}", town.getMayor().getName()).
                    replace("{name}", PassManager.getPass(uuid).getName()).
                    replace("{surname}", PassManager.getPass(uuid).getSurname()).
                    replace("{age}", PassManager.getPass(uuid).getAge().toString()).
                    replace("{vk}", PassManager.getPass(uuid).getVk()).
                    replace("{discord}", PassManager.getPass(uuid).getDiscord()))));        }

        else {
            event.setLines(Arrays.asList(format(Config.getSettingsYaml().getString("settings.towny-status-screen.town-screen-no-name"))));
        }

    }

    @EventHandler
    public void nationStatus(NationStatusScreenEvent event) {

        Town town = event.getNation().getCapital();
        UUID uuid = town.getMayor().getUUID();
        String formatted = null;

        if (PassManager.getPass(town.getMayor().getUUID()) == null) {
            return;
        }

        if (!PassManager.getPass(town.getMayor().getUUID()).getName().equals("Не указано") && !PassManager.getPass(town.getMayor().getUUID()).getSurname().equals("Не указано")) {
            event.setLines(Arrays.asList(format(Config.getSettingsYaml().getString("settings.towny-status-screen.nation-screen-has-name").
                    replace("{player}", town.getMayor().getName()).
                    replace("{name}", PassManager.getPass(uuid).getName()).
                    replace("{surname}", PassManager.getPass(uuid).getSurname()).
                    replace("{age}", PassManager.getPass(uuid).getAge().toString()).
                    replace("{vk}", PassManager.getPass(uuid).getVk()).
                    replace("{discord}", PassManager.getPass(uuid).getDiscord()))));        }

        else {
            event.setLines(Arrays.asList(format(Config.getSettingsYaml().getString("settings.towny-status-screen.nation-screen-no-name"))));
        }

    }

    @EventHandler
    public void onUpkeep(NewDayEvent event) {
        for (Pass pass : PassManager.getPassArray()) {
            pass.setAge(pass.getAge() + 1);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        if (inventory.equals(InventoryManager.getInv())) {
            event.setCancelled(true);
        }

    }

    private static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg.
                replaceAll("#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])", "&x&$1&$2&$3&$4&$5&$6"));
    }
}
