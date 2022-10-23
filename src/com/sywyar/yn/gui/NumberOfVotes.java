package com.sywyar.yn.gui;

import com.sywyar.yn.command.startGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NumberOfVotes {
    public static void nvGUI(Player player){
        Inventory ynGUI = Bukkit.createInventory(null,27, ChatColor.GREEN+"请猜出有几票赞同");

        int i = startGame.variable.players.size()+1,n=0;
        while (n<i){
            ItemStack itemStack = new ItemStack(Material.GREEN_BANNER,1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.AQUA+String.valueOf(n)+"票赞同!");
            itemStack.setItemMeta(itemMeta);
            ynGUI.setItem(n,itemStack);
            n++;
        }
        player.openInventory(ynGUI);
    }
}
