package com.sywyar.knowwe;

import com.sywyar.knowwe.command.StartGame;
import com.sywyar.knowwe.gui.ListeningGUI;
import com.sywyar.knowwe.scoreboard.KnowWeDesignationScroreboard;
import com.sywyar.knowwe.task.KnowWeTime;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KnowWe extends JavaPlugin implements Listener {
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
        getCommand("setkw").setExecutor(new StartGame());
        getCommand("setkw").setTabCompleter(new StartGame());


        BukkitTask kwTask = new KnowWeTime().runTaskTimer(this,0,20);
        BukkitTask score = new KnowWeDesignationScroreboard().runTaskTimer(this,0,5);

        System.out.println("[KnowWe] KnowWe插件已加载");
    }

    @Override
    public void onDisable() {
        getLogger().info("[KnowWe] KnowWe插件已卸载");
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
