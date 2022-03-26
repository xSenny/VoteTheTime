package me.xsenny.votethetime.Commands;

import me.xsenny.votethetime.VoteTheTime;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteTimeAdmin implements CommandExecutor {
    VoteTheTime plugin;

    public VoteTimeAdmin(VoteTheTime plugin) {
        this.plugin = plugin;
    }
    public static Boolean vote_forced = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player)  sender;
            if (p.hasPermission("timevote.admin")){
                if (args.length == 0){
                    for (int i = 0; i < plugin.getMessages().getList("AdminHelp").size(); i++){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) plugin.getMessages().getList("AdminHelp").get(i)));
                    }
                }else if (args.length == 1&&"reload".equals(args[0])){
                    plugin.reloadPlayers();
                    plugin.reloadMessages();
                    plugin.reloadConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Reload")));
                }else if (args.length == 1&&"force".equals(args[0])){
                    plugin.timp_ramas2 = 60+plugin.getConfig().getInt("TIME_ALLOWED");
                    vote_forced = true;
                }else{
                    for (int i = 0; i < plugin.getMessages().getList("AdminHelp").size(); i++){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) plugin.getMessages().getList("AdminHelp").get(i)));
                    }
                }
            }else{
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("No_permission")));
            }
        }else{
            if (args.length == 0){
                for (int i = 0; i < plugin.getMessages().getList("AdminHelp").size(); i++){
                    System.out.println(ChatColor.translateAlternateColorCodes('&', (String) plugin.getMessages().getList("AdminHelp").get(i)));
                }
            }else if (args.length == 1&&"reload".equals(args[0])){
                plugin.reloadPlayers();
                plugin.reloadMessages();
                plugin.reloadConfig();
                System.out.println(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Reload")));
            }else if (args.length == 1&&"force".equals(args[0])){
                plugin.timp_ramas2 = 60+plugin.getConfig().getInt("TIME_ALLOWED");
            }else{
                for (int i = 0; i < plugin.getMessages().getList("AdminHelp").size(); i++){
                    System.out.println(ChatColor.translateAlternateColorCodes('&', (String) plugin.getMessages().getList("AdminHelp").get(i)));
                }
            }
        }

        return true;
    }
}
