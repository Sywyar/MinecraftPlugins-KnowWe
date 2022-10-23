package com.sywyar.yn.task;

import com.sywyar.yn.command.startGame;
import org.bukkit.scheduler.BukkitRunnable;

public class yn extends BukkitRunnable {
    @Override
    public void run() {
        if (startGame.variable.gameStart){

            if (startGame.variable.isStartBeforeTalk){
                startGame.variable.beforeTalkTime-=1;
                if (startGame.variable.beforeTalkTime==0){
                    startGame.variable.isStartBeforeTalk=false;
                    int i = startGame.variable.players.size(),n=0;
                    while (n<i){
                        com.sywyar.yn.gui.yn.ynGUI(startGame.variable.players.get(n));
                        n++;
                    }
                }
            }
            if (startGame.variable.isStartAfterTalk){
                startGame.variable.afterTalkTime-=1;
                if (startGame.variable.afterTalkTime==0){
                    startGame.variable.isStartAfterTalk=false;
                    int i = startGame.variable.players.size(),n=0;
                    while (n<i){
                        com.sywyar.yn.gui.NumberOfVotes.nvGUI(startGame.variable.players.get(n));
                        n++;
                    }
                }
            }

        }
    }
}
