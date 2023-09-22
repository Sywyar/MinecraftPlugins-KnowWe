package com.sywyar.knowwe.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static com.sywyar.knowwe.command.StartGame.getOnlinePlayerName;

public class KnowWeDesignationScroreboard extends BukkitRunnable {

    public static void text(String playerName){
        Player player = Bukkit.getServer().getPlayer(playerName);
        KnowWeScoreboard.text(player);
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
