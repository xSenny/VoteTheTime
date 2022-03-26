package me.xsenny.votethetime.Events;

import me.xsenny.votethetime.VoteTheTime;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    VoteTheTime plugin;

    public PlayerJoin(VoteTheTime plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){
        if (plugin.getPlayers().isConfigurationSection("Players."+e.getPlayer().getName())){

        }else{
            plugin.getPlayers().set("Players."+e.getPlayer().getName(), true);
            plugin.savePlayers();
        }
    }
}
