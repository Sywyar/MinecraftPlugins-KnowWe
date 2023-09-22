package com.sywyar.knowwe.scoreboard;

import com.sywyar.knowwe.command.StartGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class KnowWeScoreboard {
    public static void text(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("text", "dummy","投币专家");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (StartGame.variable.end){
            Score score = objective.getScore(ChatColor.RED+"游戏结束");
            score.setScore(0);
        }else if (StartGame.variable.isStartAfterTalk){
            Score score = objective.getScore(ChatColor.GREEN+"正在进行投票后讨论");
            score.setScore(0);
            Score score1 = objective.getScore(ChatColor.RED+"剩余时间:"+ StartGame.variable.afterTalkTime);
            score1.setScore(1);
        }else if (StartGame.variable.isStartBeforeTalk){
            Score scor = objective.getScore(ChatColor.YELLOW+"问题:"+ StartGame.variable.question);
            scor.setScore(-1);
            Score score = objective.getScore(ChatColor.GREEN+"正在进行投票前讨论");
            score.setScore(0);
            Score score1 = objective.getScore(ChatColor.RED+"剩余时间:"+ StartGame.variable.beforeTalkTime);
            score1.setScore(1);
        }else {
            Score score = objective.getScore(ChatColor.GREEN+"正在投票");
            score.setScore(0);
        }

        player.setScoreboard(scoreboard);
    }
}
