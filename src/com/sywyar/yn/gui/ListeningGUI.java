package com.sywyar.yn.gui;

import com.sywyar.yn.command.startGame;
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
                startGame.variable.agree+=1;
                p.closeInventory();
            }
            if (NB.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED+"我反对!")){
                startGame.variable.disAgree+=1;
                p.closeInventory();
            }
            if ((startGame.variable.agree+startGame.variable.disAgree)==startGame.variable.players.size()){
                startGame.variable.isStartAfterTalk=true;
            }
        }
        if (NB.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN+"请猜出有几票赞同")){
            NB.setCancelled(true);
            int i = startGame.variable.players.size()+1,n=0;
            while (n<i){
                if (NB.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA+String.valueOf(n)+"票赞同!")){
                    startGame.variable.rcMap.put(startGame.variable.rcMap.size(),p.getName());
                    startGame.variable.map.put(p.getName(),n);
                    p.closeInventory();
                }
                n++;
            }
            if (startGame.variable.rcMap.size()==startGame.variable.players.size()){
                startGame.variable.end=true;
                List<String> yesPlayer = new ArrayList<>();
                StringBuilder s = new StringBuilder();
                int yi=startGame.variable.rcMap.size(),yn=0;
                while (yn<yi){
                    Bukkit.broadcastMessage(ChatColor.GREEN+startGame.variable.rcMap.get(yn)+"的猜测为:"+startGame.variable.map.get(startGame.variable.rcMap.get(yn)));
                    if (startGame.variable.map.get(startGame.variable.rcMap.get(yn))==startGame.variable.agree){
                        yesPlayer.add(startGame.variable.rcMap.get(yn));
                        s.append(startGame.variable.rcMap.get(yn)).append(" ");
                    }
                    yn++;
                }
                Bukkit.broadcastMessage(ChatColor.GREEN+"让我们恭喜以下"+yesPlayer.size()+"名玩家获取游戏的胜利:"+s);
                startGame.reLoad();
            }
        }
    }
}
