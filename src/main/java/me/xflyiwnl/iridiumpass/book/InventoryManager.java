package me.xflyiwnl.iridiumpass.book;

import me.xflyiwnl.iridiumpass.config.Config;
import me.xflyiwnl.iridiumpass.manager.PassManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private static Inventory inv;

    public InventoryManager(Player sender, Player player) {
        
        inv = Bukkit.createInventory(null, 9, format(Config.getGuiYaml().getString("gui.gui-title")));

        loadItemMeta(inv, player);

        sender.openInventory(inv);

    }

    private static void loadItemMeta(Inventory inventory, Player player) {

        ItemStack item = new ItemStack(Material.valueOf(Config.getGuiYaml().getString("gui.item-icon")), 1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(format(Config.getGuiYaml().getString("gui.item-name").replace("{player}", player.getName()).
                replace("{name}", PassManager.getPass(player.getUniqueId()).getName()).
                replace("{surname}", PassManager.getPass(player.getUniqueId()).getSurname()).
                replace("{age}", PassManager.getPass(player.getUniqueId()).getAge().toString()).
                replace("{vk}", PassManager.getPass(player.getUniqueId()).getVk()).
                replace("{discord}", PassManager.getPass(player.getUniqueId()).getDiscord())));

        List<String> itemLore = new ArrayList<>();

        for (String str : Config.getGuiYaml().getStringList("gui.item-lore")) {
            itemLore.add(format(str.replace("{player}", player.getName()).
                    replace("{name}", PassManager.getPass(player.getUniqueId()).getName()).
                    replace("{surname}", PassManager.getPass(player.getUniqueId()).getSurname()).
                    replace("{age}", PassManager.getPass(player.getUniqueId()).getAge().toString()).
                    replace("{vk}", PassManager.getPass(player.getUniqueId()).getVk()).
                    replace("{discord}", PassManager.getPass(player.getUniqueId()).getDiscord())));
        }

        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        inventory.setItem(4, item);

    }

    private static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg.
                replaceAll("#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])", "&x&$1&$2&$3&$4&$5&$6"));
    }

    public static Inventory getInv() {
        return inv;
    }

}
