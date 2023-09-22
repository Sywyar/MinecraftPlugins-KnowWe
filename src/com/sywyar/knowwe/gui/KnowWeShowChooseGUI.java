package com.sywyar.knowwe.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KnowWeShowChooseGUI {
    public static void chooseGUI(Player p) {
        Inventory ynGUI = Bukkit.createInventory(null,27, ChatColor.GREEN+"请做出你的选择!");

        ItemStack ENCHANTED_GOLDEN_APPLE = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE,1);
        ItemMeta ENCHANTED_GOLDEN_APPLE_NAME = ENCHANTED_GOLDEN_APPLE.getItemMeta();
        ENCHANTED_GOLDEN_APPLE_NAME.setDisplayName(ChatColor.GREEN+"我赞同!");
        ENCHANTED_GOLDEN_APPLE.setItemMeta(ENCHANTED_GOLDEN_APPLE_NAME);

        ItemStack BARRIER = new ItemStack(Material.BARRIER,1);
        ItemMeta BARRIER_NAME = BARRIER.getItemMeta();
        BARRIER_NAME.setDisplayName(ChatColor.RED+"我反对!");
        BARRIER.setItemMeta(BARRIER_NAME);

        ynGUI.setItem(12,ENCHANTED_GOLDEN_APPLE);
        ynGUI.setItem(14,BARRIER);

        p.openInventory(ynGUI);
    }
}
