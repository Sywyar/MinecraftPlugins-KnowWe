package com.sywyar.yn;

import com.sywyar.yn.gui.ListeningGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class yn extends JavaPlugin implements Listener {
    //设置问题
    //开始游戏
    //提出问题
    //开始讨论
    //投票
    //再次讨论
    //猜测赞同票数
    //公布结果

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);

        getServer().getPluginManager().registerEvents(new ListeningGUI(), this);
        getCommand("setyn").setExecutor(new com.sywyar.yn.command.startGame());
        getCommand("setyn").setTabCompleter(new com.sywyar.yn.command.startGame());


        BukkitTask ynTask = new com.sywyar.yn.task.yn().runTaskTimer(this,0,20);
        BukkitTask score = new com.sywyar.yn.scoreboard.ynisplayer().runTaskTimer(this,0,5);

        System.out.println("[YN] YN插件已加载");
    }

    @Override
    public void onDisable() {
        getLogger().info("[YN] YN插件已卸载");
    }

    public static class variable{
        public boolean gameStart = false;
        public boolean isStartBeforeTalk = false;//投票前讨论时间
        public boolean isStartAfterTalk = false;//投票后讨论时间
        public boolean end=true;
        public Player questioner;
        public String question="";
        public int agree=0; //赞同
        public int disAgree=0; //不赞同
        public int beforeTalkTime = 120;//投票前讨论时间
        public int afterTalkTime = 120;//投票后讨论时间
        public List<Player> players = new ArrayList<>();
        public HashMap<String,Integer> map = new HashMap<>(); //记录玩家 记录票数
        public HashMap<Integer,String> rcMap = new HashMap<>(); //记录ID 记录名字
    }
}
