package com.sywyar.knowwe.command;

import com.sywyar.knowwe.KnowWe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StartGame implements TabExecutor {
    static String prefix = (ChatColor.translateAlternateColorCodes('&', "&e[&aY&4N&e]"));
    public static KnowWe.variable variable = new KnowWe.variable();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        switch (strings[0]){
            case "start":Start(sender);break;
            case "stop":Stop();break;
            case "setquestion":setQuestion(sender,strings);break;
            case "setbeforetalktime":setBeforeTalkTime(sender,strings);break;
            case "setaftertalktime":setAfterTalkTime(sender,strings);break;
            case "reload":reLoad();break;
            case "skipbeforetalktime":skipBeforeTalkTime(sender);break;
            case "skipaftertalktime":skipAfterTalkTime(sender);break;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> list = new ArrayList<>();
        if (strings.length==1){
            list.add("start");
            list.add("stop");
            list.add("setquestion");
            list.add("setbeforetalktime");
            list.add("setaftertalktime");
            list.add("reload");
            list.add("skipbeforetalktime");
            list.add("skipaftertalktime");
        }
        if (strings.length==2){
            if (strings[0].equals("setquestion")){
                list.add("请输入问题");
            }
            if (strings[0].equals("setbeforetalktime") || strings[0].equals("setaftertalktime")){
                list.add("请输入时间");
            }
        }
        return list;
    }

    public static void Start(CommandSender sender){
        if (variable.question==null || variable.question.isEmpty()){
            sender.sendMessage(prefix + ChatColor.RED+"没有设置问题，无法开始游戏，请使用/setkw setquestion <问题> 来设置问题 ");
        }else {
            variable.gameStart=true;
            variable.end=false;
            variable.isStartBeforeTalk=true;
            List<String> list = getOnlinePlayerName();
            int i = list.size(),n=0;
            while (n<i){
                variable.players.add(Bukkit.getServer().getPlayer(list.get(n)));
                n++;
            }
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&a游戏开始"));
            variable.questioner = (Player) sender;
            Bukkit.broadcastMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&a投票前讨论将在&4" + variable.beforeTalkTime + "&a后开始，之后会打开GUI界面进行投票"));
            Bukkit.broadcastMessage(prefix+ChatColor.AQUA+"问题为:"+variable.question);
        }

    }

    public static void Stop(){
        Bukkit.broadcastMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&e[&aY&4N&e]") + "&a游戏结束");
        reLoad();
    }

    public static void setQuestion(CommandSender sender,String[] strings){
        if (strings[1]==null || strings[1].isEmpty()){
            sender.sendMessage(prefix + ChatColor.RED+"设置问题失败，因为没有问题");
        }else {
            variable.question = strings[1];
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&a设置问题成功：" + strings[1]));
        }
    }

    public static void setBeforeTalkTime(CommandSender sender,String[] strings){
        if (strings.length<2){
            sender.sendMessage(prefix + ChatColor.RED+"设置时间失败，因没有时间");
        }else {
            try {
                variable.beforeTalkTime = Integer.parseInt(strings[1]);
                sender.sendMessage(prefix + ChatColor.RED + "设置时间成功为:"+strings[1]);
            }catch (NumberFormatException e){
                sender.sendMessage(prefix + ChatColor.RED + "设置时间失败，因为时间只能为整数");
            }
        }
    }

    public static void setAfterTalkTime(CommandSender sender,String[] strings){
        if (strings.length<2){
            sender.sendMessage(prefix + ChatColor.RED+"设置时间失败，因没有时间");
        }else {
            try {
                variable.afterTalkTime = Integer.parseInt(strings[1]);
                sender.sendMessage(prefix + ChatColor.RED + "设置时间成功为:"+strings[1]);
            }catch (NumberFormatException e){
                sender.sendMessage(prefix + ChatColor.RED + "设置时间失败，因为时间只能为整数");
            }
        }
    }

    public static void reLoad(){
        variable = new KnowWe.variable();
    }

    public static void skipBeforeTalkTime(CommandSender sender){
        if (StartGame.variable.isStartBeforeTalk){
            StartGame.variable.beforeTalkTime=3;
        }else {
            sender.sendMessage(ChatColor.RED+"投票前讨论暂未开始");
        }
    }

    public static void skipAfterTalkTime(CommandSender sender){
        if (StartGame.variable.isStartAfterTalk){
            StartGame.variable.afterTalkTime=3;
        }else {
            sender.sendMessage(ChatColor.RED+"投票后讨论暂未开始");
        }
    }

    public static String result(String str,String start,String end){
        int strStartIndex = str.indexOf(start);
        int strEndIndex = str.indexOf(end);
        return str.substring(strStartIndex, strEndIndex).substring(start.length());
    }

    public static ArrayList<String> getOnlinePlayerName(){
        ArrayList<String> arrayList = new ArrayList<>();
        String name = String.valueOf(Bukkit.getOnlinePlayers());
        int num = Bukkit.getServer().getOnlinePlayers().size();
        if (num!=0){
            if (num==1){
                String result = result(name,"[","]");
                String playername = result(result,"name=","}");
                arrayList.add(playername);
            }else {
                String temp = result(name,"[","]");
                while (num!=0){
                    num=num-1;
                    temp = result(temp +"end","name=","end");
                    String playername = result("start" + temp,"start","}");
                    arrayList.add(playername);
                }
            }
        }
        return arrayList;
    }
}
