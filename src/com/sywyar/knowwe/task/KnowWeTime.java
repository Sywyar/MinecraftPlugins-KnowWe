package com.sywyar.knowwe.task;

import com.sywyar.knowwe.command.StartGame;
import com.sywyar.knowwe.gui.KnowWeShowChooseGUI;
import org.bukkit.scheduler.BukkitRunnable;

public class KnowWeTime extends BukkitRunnable {
    @Override
    public void run() {
        if (StartGame.variable.gameStart){

            if (StartGame.variable.isStartBeforeTalk){
                StartGame.variable.beforeTalkTime-=1;
                if (StartGame.variable.beforeTalkTime==0){
                    StartGame.variable.isStartBeforeTalk=false;
                    int i = StartGame.variable.players.size(),n=0;
                    while (n<i){
                        KnowWeShowChooseGUI.chooseGUI(StartGame.variable.players.get(n));
                        n++;
                    }
                }
            }
            if (StartGame.variable.isStartAfterTalk){
                StartGame.variable.afterTalkTime-=1;
                if (StartGame.variable.afterTalkTime==0){
                    StartGame.variable.isStartAfterTalk=false;
                    int i = StartGame.variable.players.size(),n=0;
                    while (n<i){
                        com.sywyar.knowwe.gui.NumberOfVotes.nvGUI(StartGame.variable.players.get(n));
                        n++;
                    }
                }
            }

        }
    }
}
