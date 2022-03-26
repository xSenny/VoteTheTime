package me.xsenny.votethetime.Runs;

import me.xsenny.votethetime.VoteTheTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class TreceTimpulx2 extends BukkitRunnable {

    VoteTheTime plugin;

    public TreceTimpulx2(VoteTheTime plugin) {
        this.plugin = plugin;
    }

    public static Boolean ma_depaseste = false;

    @Override
    public void run() {
        if (!(plugin.timp_ramas2 == null)){
            if (plugin.timp_ramas2>0 && plugin.timp_ramas2!=60+plugin.getConfig().getInt("TIME_ALLOWED")&& plugin.timp_ramas2!=20+plugin.getConfig().getInt("TIME_ALLOWED")&& plugin.timp_ramas2!=3+plugin.getConfig().getInt("TIME_ALLOWED")&&plugin.timp_ramas2!=0+plugin.getConfig().getInt("TIME_ALLOWED")){
                plugin.timp_ramas2 = plugin.timp_ramas2 -1;
            }else if (plugin.timp_ramas2 == 60+plugin.getConfig().getInt("TIME_ALLOWED")){
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (plugin.getPlayers().getBoolean("Players."+p.getName())){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Vote_announcement1")));
                    }else{
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("No_Vote_seted")));
                    }
                }plugin.timp_ramas2 = plugin.timp_ramas2 -1;
            }else if (plugin.timp_ramas2 == 20+plugin.getConfig().getInt("TIME_ALLOWED")){
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (plugin.getPlayers().getBoolean("Players."+p.getName())){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Vote_announcement2")));
                    }
                }plugin.timp_ramas2 = plugin.timp_ramas2 -1;
            }else if (plugin.timp_ramas2 == 3+plugin.getConfig().getInt("TIME_ALLOWED")){
                String mess = plugin.getMessages().getString("Vote_announcement3");
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (plugin.getPlayers().getBoolean("Players."+p.getName())){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', mess));
                    }
                }plugin.timp_ramas2 = plugin.timp_ramas2 -1;
            }else if (plugin.timp_ramas2 == 0 +  plugin.getConfig().getInt("TIME_ALLOWED")){
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (plugin.getPlayers().getBoolean("Players."+p.getName())){
                        plugin.Vote(p);
                    }

                }plugin.timp_ramas2 = plugin.timp_ramas2 - 1;
            }else if ((plugin.timp_ramas2 == 0)&& ma_depaseste == false){
                if (plugin.votes_day == plugin.votes_night){

                    for (int i =0; i < plugin.getConfig().getList("Worlds").size();i++){
                        String world = (String) plugin.getConfig().getList("Worlds").get(i);
                        if (Bukkit.getServer().getWorld(world) != null){
                            Random timp = new Random();
                            Integer time = timp.nextInt(20000);
                            Bukkit.getServer().getWorld(world).setTime(time);
                        }else{
                            System.out.println("This world doesn't exist, please check config.yml file.");
                        }
                    }for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Votes_Draw")));
                    }ma_depaseste = true;
                }else{
                    if (plugin.votes_day > plugin.votes_night){
                        for (int i =0; i < plugin.getConfig().getList("Worlds").size();i++){
                            String world = (String) plugin.getConfig().getList("Worlds").get(i);
                            if (Bukkit.getServer().getWorld(world) != null){
                                Bukkit.getServer().getWorld(world).setTime(plugin.getConfig().getInt("Day"));
                            }else{
                                System.out.println("This world doesn't exist, please check config.yml file.");
                            }
                        }for (Player p : Bukkit.getOnlinePlayers()){
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Votes_day")));
                        }ma_depaseste = true;
                    }else if (plugin.votes_night > plugin.votes_day){
                        for (int i =0; i < plugin.getConfig().getList("Worlds").size();i++){
                            String world = (String) plugin.getConfig().getList("Worlds").get(i);
                            if (Bukkit.getServer().getWorld(world) != null){
                                Bukkit.getServer().getWorld(world).setTime(plugin.getConfig().getInt("Night"));
                            }else{
                                System.out.println("This world doesn't exist, please check config.yml file.");
                            }
                        }for (Player p : Bukkit.getOnlinePlayers()){
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Votes_night")));
                        }
                        ma_depaseste = true;
                    }
                }
                plugin.votes_day = 0;
                plugin.votes_night = 0;
            }
        }
    }
}
