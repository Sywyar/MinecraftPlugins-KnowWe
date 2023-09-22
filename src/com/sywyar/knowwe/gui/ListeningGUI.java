package com.sywyar.knowwe.gui;

import com.sywyar.knowwe.command.StartGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class ListeningGUI implements Listener {
    @EventHandler
    public void Withdraw(InventoryClickEvent NB){
        Player p = (Player) NB.getWhoClicked();
        if (NB.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN+"请做出你的选择!")){
            NB.setCancelled(true);
            if (NB.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"我赞同!")){
                StartGame.variable.agree+=1;
                p.closeInventory();
            }
            if (NB.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED+"我反对!")){
                StartGame.variable.disAgree+=1;
                p.closeInventory();
            }
            if ((StartGame.variable.agree+ StartGame.variable.disAgree)== StartGame.variable.players.size()){
                StartGame.variable.isStartAfterTalk=true;
            }
        }
        if (NB.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN+"请猜出有几票赞同")){
            NB.setCancelled(true);
            int i = StartGame.variable.players.size()+1,n=0;
            while (n<i){
                if (NB.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA+String.valueOf(n)+"票赞同!")){
                    StartGame.variable.rcMap.put(StartGame.variable.rcMap.size(),p.getName());
                    StartGame.variable.map.put(p.getName(),n);
                    p.closeInventory();
                }
                n++;
            }
            if (StartGame.variable.rcMap.size()== StartGame.variable.players.size()){
                StartGame.variable.end=true;
                List<String> yesPlayer = new ArrayList<>();
                StringBuilder s = new StringBuilder();
                int yi= StartGame.variable.rcMap.size(),yn=0;
                while (yn<yi){
                    Bukkit.broadcastMessage(ChatColor.GREEN+ StartGame.variable.rcMap.get(yn)+"的猜测为:"+ StartGame.variable.map.get(StartGame.variable.rcMap.get(yn)));
                    if (StartGame.variable.map.get(StartGame.variable.rcMap.get(yn))== StartGame.variable.agree){
                        yesPlayer.add(StartGame.variable.rcMap.get(yn));
                        s.append(StartGame.variable.rcMap.get(yn)).append(" ");
                    }
                    yn++;
                }
                Bukkit.broadcastMessage(ChatColor.GREEN+"让我们恭喜以下"+yesPlayer.size()+"名玩家获取游戏的胜利:"+s);
                StartGame.reLoad();
            }
        }
    }
}
