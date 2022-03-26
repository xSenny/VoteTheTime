package me.xsenny.votethetime.MenuListener;

import me.xsenny.votethetime.VoteTheTime;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    VoteTheTime plugin;

    public MenuListener(VoteTheTime plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Click(InventoryClickEvent e){
        if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Gui.name")))){
            Player p = (Player) e.getWhoClicked();
            switch (e.getCurrentItem().getType()){
                case RED_WOOL:
                    plugin.votes_night = plugin.votes_night + 1;
                    p.closeInventory();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Vote_night")));
                    break;
                case GREEN_WOOL:
                    plugin.votes_day = plugin.votes_day + 1;
                    p.closeInventory();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Vote_day")));
                    break;
                case BARRIER:
                    p.closeInventory();
                    break;
            }

            e.setCancelled(true);
        }
    }
}
