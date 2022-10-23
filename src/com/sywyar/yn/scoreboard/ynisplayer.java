package com.sywyar.yn.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static com.sywyar.yn.command.startGame.getOnlinePlayerName;

public class ynisplayer extends BukkitRunnable {

    public static void text(String playername){
        Player player = Bukkit.getServer().getPlayer(playername);
        com.sywyar.yn.scoreboard.yn.text(player);
    }

    @Override
    public void run() {
        ArrayList<String> arrayList = getOnlinePlayerName();
        int i = arrayList.size();
        while (i!=0){
            text(arrayList.get(i-1));
            i=i-1;
        }
    }
}
