package com.sywyar.yn.scoreboard;

import com.sywyar.yn.command.startGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class yn {
    public static void text(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("text", "dummy","投币专家");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (startGame.variable.end){
            Score score = objective.getScore(ChatColor.RED+"游戏结束");
            score.setScore(0);
        }else if (startGame.variable.isStartAfterTalk){
            Score score = objective.getScore(ChatColor.GREEN+"正在进行投票后讨论");
            score.setScore(0);
            Score score1 = objective.getScore(ChatColor.RED+"剩余时间:"+startGame.variable.afterTalkTime);
            score1.setScore(1);
        }else if (startGame.variable.isStartBeforeTalk){
            Score scor = objective.getScore(ChatColor.YELLOW+"问题:"+startGame.variable.question);
            scor.setScore(-1);
            Score score = objective.getScore(ChatColor.GREEN+"正在进行投票前讨论");
            score.setScore(0);
            Score score1 = objective.getScore(ChatColor.RED+"剩余时间:"+startGame.variable.beforeTalkTime);
            score1.setScore(1);
        }else {
            Score score = objective.getScore(ChatColor.GREEN+"正在投票");
            score.setScore(0);
        }

        player.setScoreboard(scoreboard);
    }
}
