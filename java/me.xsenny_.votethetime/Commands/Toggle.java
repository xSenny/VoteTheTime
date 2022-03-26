package me.xsenny.votethetime.Commands;

import me.xsenny.votethetime.VoteTheTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Toggle implements CommandExecutor {

    VoteTheTime plugin;

    public Toggle(VoteTheTime plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){
                if (p.hasPermission("votetime.toggle")){
                    if (plugin.getPlayers().getBoolean("Players."+p.getName())){
                        plugin.getPlayers().set("Players."+p.getName(), false);
                        plugin.savePlayers();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Toggle_false")));
                    }else{
                        plugin.getPlayers().set("Players."+p.getName(), true);
                        plugin.savePlayers();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Toggle_true")));
                    }
                }else{
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("No_permission")));
                }
            }else if (args.length == 1){
                if (p.hasPermission("votetime.toggle.*")){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target instanceof Player){
                        if (plugin.getPlayers().getBoolean("Players."+target.getName())){
                            plugin.getPlayers().set("Players."+target.getName(), false);
                            plugin.savePlayers();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Toggle_false_admin")));
                        }else{
                            plugin.getPlayers().set("Players."+target.getName(), true);
                            plugin.savePlayers();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Toggle_true_admin")));
                        }
                    }else{
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Not_a_player"));
                    }

                }else{
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("No_permission")));

                }
            }
        }else{
            System.out.println(plugin.getMessages().getString("No_console"));
        }

        return true;
    }
}
